package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.UploadFileDTO;
import org.zerock.b01.dto.UploadResultDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@Log4j2
public class UpDownController {
    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @Operation(summary = "파일 업로드", description = "[POST]방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO>  upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles()!=null){
            // 서버 응답 값
            final List<UploadResultDTO> list = new ArrayList<>();
            uploadFileDTO.getFiles().forEach(multipartFile -> {
                // 서버 응답 값
                boolean image = false;
                String orginName = multipartFile.getOriginalFilename();
                log.info(orginName);

                String uuid = UUID.randomUUID().toString();
                Path savePath = Paths.get(uploadPath, uuid + "_" +  orginName);

                try {
                    multipartFile.transferTo(savePath); // 파일업로드

                    // 이미지파일 섬네일 처리
                    // 이미지 파일 종류라면
                    if(Files.probeContentType(savePath).startsWith("image"))
                    {
                        File thumbFile = new File(uploadPath, "s_" + uuid + "_" +  orginName);
                        // 썸네일로 바꾸기 width : 200px, height : 200px 인듯
                        Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 200, 200);

                        // 서버 응답 값
                        image = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 서버 응답 값
                list.add(UploadResultDTO.builder()
                                .uuid(uuid)
                                .fileName(orginName)
                                .img(image)
                                .build());

            });

            return list;
        }

        return null;
    }

    @Operation(summary = "파일 조회", description = "[GET]방식으로 파일 조회")
    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName)
    {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        //String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @Operation(summary = "파일 삭제", description = "[DELETE]방식으로 파일 삭제")
    @DeleteMapping(value = "/view/{fileName}")
    public Map<String, Boolean> removeFile(@PathVariable String fileName)
    {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        String resourceName = resource.getFilename();
        Map<String, Boolean> resultMap = new HashMap<>();
        boolean removed = false;

        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            // 파일 삭제
            removed = resource.getFile().delete();

            // 섬네일 존재여부
            if(contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                // 섬네일 삭제
                thumbnailFile.delete();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        resultMap.put("removed", removed);

        return resultMap;
    }
}


package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.UploadFileDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {
    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @Operation(summary = "댓글 등록", description = "[POST]방식으로 댓글 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String  upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);

        if(uploadFileDTO.getFiles()!=null){
            uploadFileDTO.getFiles().forEach(multipartFile -> {
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
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return null;
    }
}

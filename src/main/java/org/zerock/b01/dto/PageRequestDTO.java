package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO
{
    @Builder.Default
    private int page  = 1;
    @Builder.Default
    private int size  = 10;
    private String type;    // 검색의 종류 t,c,w,tc,tw,twc
    private String keyword;
    private String link;

    public String[] getTypes(){
        if(type==null || type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    // 페이징 처리
    public Pageable getPageable(String...props){
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    public String getLink(){
        if(link==null){
            StringBuilder builder = new StringBuilder();
            builder.append("page="+ this.page);
            builder.append("&size=" + this.size);

            if(keyword!=null && !keyword.isEmpty()){
                builder.append("&type=" + this.type);
                builder.append("&keyword=" + keyword);
            }
            link = builder.toString();
        }
        return link;
    }
}



package org.zerock.b01.domain;

import lombok.*;

import jakarta.persistence.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false) //컬럼의 길이와 null허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private Set<BoardImage> imageSet = new HashSet<>();

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }


}
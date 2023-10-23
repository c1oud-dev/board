package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data //lombok
public class Board {
    @Id //primary key를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //게시글 번호 (필드 지정)

    private String title; //제목

    private String content; //내용
}

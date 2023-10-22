package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    @GetMapping("/board/write") //어떤 url로 접근할거냐에 대한 걸 지정
    public String boardWriteForm() {
        return "boardwrite"; //어떤 html 파일로(뷰 파일) 보내줄거냐에 대한 걸 입력
    }
}

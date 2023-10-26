package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //어떤 url로 접근할거냐에 대한 걸 지정
    public String boardWriteForm() {
        return "boardwrite"; //어떤 html 파일로(뷰 파일) 보내줄거냐에 대한 걸 입력
    }

    @PostMapping("/board/writedo")
    public String boardWriteDo(Board board) {

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        model.addAttribute("List", boardService.boardList());
        return "boardList";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1 (get 방식이라고 한다.)
    public String boardView(Model model, Integer id) {
        model.addAttribute("article", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.BoardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("article", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board) {
        Board boardTemp = boardService.boardView(id); //기존에 있던 객체 받아오기
        boardTemp.setTitle(board.getTitle()); //새로 작성한 내용을 덮어씌움, 수정할 때 넘어온 데이터들 처리
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp); //이걸 해줘야 수정된 내용이 적용됨
        return "redirect:/board/list";
    }
}

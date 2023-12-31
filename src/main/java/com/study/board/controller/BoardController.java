package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String boardWriteDo(Board board, Model model) {
        boardService.write(board);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort ="id",
                                                    direction = Sort.Direction.DESC) Pageable pageable) {
        //추가
        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1; //현재 페이지
        int startPage = nowPage / pageable.getPageSize() * pageable.getPageSize() + 1;
        int endPage = startPage + pageable.getPageSize() - 1;

        //int startPage = Math.max(nowPage - 4, 1); //블럭에서 보여줄 시작 페이지
        //int endPage = Math.min(nowPage + 5, list.getTotalPages()); //블럭에서 보이는 마지막 페이지

        //수정
        model.addAttribute("List", list);

        //추가
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model) {
        Board boardTemp = boardService.boardView(id); //기존에 있던 객체 받아오기
        boardTemp.setTitle(board.getTitle()); //새로 작성한 내용을 덮어씌움, 수정할 때 넘어온 데이터들 처리
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp); //이걸 해줘야 수정된 내용이 적용됨

        model.addAttribute("message", "수정되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }
}

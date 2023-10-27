package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //서비스는 다시 컨트롤러에서 이용
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    //글 작성 처리
    public void write(Board board) { //이름 통일성을 위해 후에 이름 수정
        boardRepository.save(board);
    }

    //게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    //특정 게시물 삭제하기
    public void BoardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

}

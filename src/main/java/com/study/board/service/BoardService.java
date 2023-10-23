package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //서비스는 다시 컨트롤러에서 이용
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }
}

package com.example.blogproject.api;

import com.example.blogproject.dto.ResponseDto;
import com.example.blogproject.model.Board;
import com.example.blogproject.model.Reply;
import com.example.blogproject.model.User;
import com.example.blogproject.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpSession session;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board){
        boardService.글쓰기(board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply){
        User user= (User) session.getAttribute("principal");

        log.info("user : {} ", user);

        boardService.댓글쓰기(user, boardId, reply);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}



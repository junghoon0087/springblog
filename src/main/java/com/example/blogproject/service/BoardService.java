package com.example.blogproject.service;

import com.example.blogproject.model.Board;
import com.example.blogproject.model.Reply;
import com.example.blogproject.model.User;
import com.example.blogproject.repository.BoardRepository;
import com.example.blogproject.repository.ReplyRepository;
import com.example.blogproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session;

    private List<Reply> replies;

    public BoardService() {
    }

    @Transactional
    public void 글쓰기(Board board){
        User user=(User) session.getAttribute("principal");
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id){
        Board board=boardRepository.findById(id).orElse(null);
        return board;
    }
    @Transactional
    public void 글삭제하기(int id) {
        System.out.println("글 삭제하기 : " + id);
        boardRepository.deleteById(id);
        // void형임 optional이 아니다.
    }
    @Transactional
    public void 글수정하기(int id, Board requestboard) {
        // 영속화 시킨다.
        Board board=boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
        }); // 영속화 완료
        System.out.println("영속화후: " + board);

        board.setTitle(requestboard.getTitle());
        board.setContent(requestboard.getContent());

        System.out.println("영속화후2: " + board);
        // boardRepository.save(board);
        // 해당함수 종료시 (서비스가 종료될때) 트랜잭션이 종료된다. 이때 더티체킹-자동업데이트가된다.
        // db flush된다. 즉 commit이 된다.
    }
    @Transactional
    public void 댓글쓰기(User user, int boardId, Reply requestReply) {
        Board board=boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패: 게시글아이디를 찾을 수 없습니다.");
        }); // 영속화 완료

        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }
    @Transactional
    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }
}

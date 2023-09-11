package com.example.blogproject.controller;

import com.example.blogproject.model.Board;
import com.example.blogproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {
//    @GetMapping({"","/"})
//    public String index(){
//        return "index";
//    }
    @Autowired
    private BoardService boardService;
//    @GetMapping({"","/"})
//    public String index(Model model @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)
//            Pageable pageable) {
//        Page<Board> boards=boardService.글목록(pageable);
//        model.addAttribute( "boards", boards);
//        return "index";
//    }
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index";
    }

    @GetMapping("/board/writeForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }
}

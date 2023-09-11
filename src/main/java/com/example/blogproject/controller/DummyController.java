package com.example.blogproject.controller;

import com.example.blogproject.test.MyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.ResponseCache;

@Slf4j
@Controller
public class DummyController {
    @GetMapping("/join")
    public String join(){
        return "join";   //views -> join.jsp
    }
    @PostMapping("/user/join")
    //@ResponseBody
    public ResponseEntity<MyData> join2(@RequestBody String user){
        log.info(user);

        MyData data = new MyData();
        data.setMsg("OK");
        // data.setMsg("회원가입성공");
        return ResponseEntity.status(HttpStatus.OK).body(data);
        //return ResponseEntity.ok(data);
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

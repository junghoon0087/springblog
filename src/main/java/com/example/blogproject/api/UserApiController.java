package com.example.blogproject.api;

import com.example.blogproject.dto.ResponseDto;
import com.example.blogproject.model.RoleType;
import com.example.blogproject.model.User;
import com.example.blogproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;
//    @Autowired
//    private ResponseDto dto;


    @PostMapping("api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save 호출됨");
        user.setRole(RoleType.USER);
        Integer result=userService.회원가입(user);
        if(result==0){
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(), 0);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    //로그인
    @PostMapping("api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        log.info("User API login 호출됨");

        User principal=userService.로그인(user); // principal 접근주체

        if(principal != null){
            session.setAttribute("principal",principal);
        }else{
            return new ResponseDto<Integer>(HttpStatus.FORBIDDEN.value(),0);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//        dto.setData(1);
//        dto.setStatus(HttpStatus.OK.value());
        //dto.builder().data(1).status(HttpStatus.OK.value()).build();
        //return dto;
    }
    @GetMapping("/api/user/{username}")
    public ResponseEntity<String> check(@PathVariable String username){

        int result=userService.중복확인(username);
        log.info("result :  {} ", result);
        if(result==1){  // null
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("NO");
        }
    }
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.회원수정(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}

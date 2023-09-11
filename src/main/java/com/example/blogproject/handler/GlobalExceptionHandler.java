package com.example.blogproject.handler;

import com.example.blogproject.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  //예외처리를 중앙에서 관리한다.
@RestController
public class GlobalExceptionHandler {
//    @ExceptionHandler(value = NullPointerException.class)
//    public String handleArgumentException(IllegalArgumentException e){
//        return "<h1>"+e.getMessage()+"</h1>";
//    }
//
//    @ExceptionHandler(value = IllegalArgumentException.class)
//    public String handleArgumentException3(IllegalArgumentException e){
//        return "<h1> 아규먼트 예외발생 : "+e.getMessage()+"</h1>";
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public String handleArgumentException2(IllegalArgumentException e){
//        return "<h1> Exception : "+e.getMessage()+"</h1>";
//    }
    @ExceptionHandler(value= Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}

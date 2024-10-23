package com.oh.rep0531.controller;

import com.oh.rep0531.Dto.ResponseDTO;
import com.oh.rep0531.Dto.TestRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String testController(){
        return "Hello world!";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath(){
        return "Hello world testGetMapping";
    }

    //required=false 는 매개변수가 꼭 필요한건 아니란 뜻
    @GetMapping("/{id}")
    public String testControllerWithPathVariable(@PathVariable(required = false) int id){
        return "Hello world Id : " + id;
    }

    @GetMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(required = false) int id){
        return "Hello world ID : " + id;
    }

    @GetMapping("/testRequestBody")
    public String testRequestBody(@RequestBody TestRequestDTO testRequestDTO){
        return "Hello world ID : " + testRequestDTO.getId()
                +" Message : " + testRequestDTO.getMessage();
    }

    @GetMapping("/testResponseBody")
    public ResponseDTO<String> testControllerResponseBody(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm responseDTO");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return response;
    }

    //Spring Framework에서 제공하는 클래스 중 HttpEntity라는 클래스가 존재한다.
    // 이것은 HTTP 요청(Request) 또는 응답(Response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스이다.
    @GetMapping("/testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity(){
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400!");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(response);
    }

}

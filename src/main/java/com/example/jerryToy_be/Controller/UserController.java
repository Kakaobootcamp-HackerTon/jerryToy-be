package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.UserRegisterDTO;
import com.example.jerryToy_be.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public String register(@RequestBody UserRegisterDTO userRegisterDTO){
        if(userRegisterDTO.getAge()<0 || userRegisterDTO.getAge()>100){
            log.info("age check invalid");
            // 나이 제대로 입력해주세요 메시지
            return "age check invalid";
        }
        log.info("register processing");
        try{
            userService.register(userRegisterDTO);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        } finally {
            log.info("register success");
        }
        return "redirect:/main";
    }

    @PostMapping("/register")
    public ResponseEntity registUser(@RequestBody UserRegisterDTO userRegisterDTO){ //회원가입
        System.out.println("회원 가입");
        userService.register(userRegisterDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}

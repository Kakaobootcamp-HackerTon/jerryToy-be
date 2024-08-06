package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.UserRequestDTO;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity login(@RequestBody UserRequestDTO userRequestDTO){
        String message;
        try{
            message = userService.login(userRequestDTO);
        } catch(RuntimeException e){
            log.error(e.getMessage());
        } finally{
            log.info("login process end");
        }
        return ResponseEntity.ok().body("logged in success");
    }
}

package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.UserRequestDTO;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRequestDTO> login(@RequestParam String username, @RequestParam String password){
        User user = userService;
    }
}

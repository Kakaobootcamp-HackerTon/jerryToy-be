package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.UserPatchRequestDTO;
import com.example.jerryToy_be.DTO.UserRequestDTO;
import com.example.jerryToy_be.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void edit(UserPatchRequestDTO userPatchDTO){

    }

    public String login(UserRequestDTO userRequestDTO){
        if(userRepository.findByUsernameAndPassword(
                userRequestDTO.getUsername(), userRequestDTO.getPassword())
                .isValid()){
            return "redirect:/main";
        }

    }
}

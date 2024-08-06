package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.UserPatchRequestDTO;
import com.example.jerryToy_be.DTO.UserRegisterDTO;
import com.example.jerryToy_be.DTO.UserRequestDTO;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String login(UserRequestDTO userRequestDTO) throws RuntimeException{
        User temp = userRepository.findByUsername(userRequestDTO.getUsername());
        if(temp==null){
            return "cannot find user";
        } else if(!temp.getPassword().equals(userRequestDTO.getPassword())){
            return "wrong password";
        } else if(!temp.isValid()){
            return "invalid user";
        } else {
            return "logged in successfully";
        }
    }

    // 회원가입 로직
    @Transactional
    public void register(UserRegisterDTO userRegisterDTO) throws RuntimeException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = User.builder()
                .username(userRegisterDTO.getUsername())
                .password(userRegisterDTO.getPassword())
                .nickname(userRegisterDTO.getNickname())
                .age(userRegisterDTO.getAge())
                .mbti(userRegisterDTO.getMbti())
                .gender(userRegisterDTO.getGender())
                .profile_image(userRegisterDTO.getProfile_image())
                .date(sdf.format(new Date()))
                .count(0)
                .role("USER")
                .degree(36.5)
                .isValid(true)
                .build();
        userRepository.save(user);
        log.info("register service complete");
    }
}

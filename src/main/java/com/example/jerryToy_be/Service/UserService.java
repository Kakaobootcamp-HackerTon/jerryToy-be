package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.UserPatchRequestDTO;
import com.example.jerryToy_be.DTO.UserRegisterDTO;
import com.example.jerryToy_be.DTO.UserRequestDTO;
import com.example.jerryToy_be.DTO.UserResponseDTO;
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
                .regDate(sdf.format(new Date()))
                .count(0)
                .role("USER")
                .degree(36.5)
                .isValid(true)
                .build();
        userRepository.save(user);
        log.info("register service complete");
    }

    // 유저정보 조회
    @Transactional
    public UserResponseDTO getUserInfo(String token, Long userId) throws RuntimeException{
        User user = userRepository.findByUserId(userId);
        if(user==null){
            throw new RuntimeException("user not found");
        }
        return UserResponseDTO.builder()
                .nickname(user.getNickname())
                .profile_image(user.getProfile_image())
                .age(user.getAge())
                .mbti(user.getMbti())
                .gender(user.getGender())
                .date(user.getRegDate())
                .count(user.getCount())
                .recent_match(user.getRecent_match())
                .degree(user.getDegree())
                .build();
    }

    // 유저정보 수정 - count++, recent_match 최신화
    @Transactional
    public void userAfterMatch(Long userId) throws RuntimeException{
        User user = userRepository.findByUserId(userId);
        if(user==null){
            throw new RuntimeException("user not found");
        }

    }
}

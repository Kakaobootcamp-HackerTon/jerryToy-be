package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.UserPatchRequestDTO;
import com.example.jerryToy_be.DTO.UserRegisterDTO;
import com.example.jerryToy_be.DTO.UserRequestDTO;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // 회원가입 로직
    @Transactional
    public void register(UserRegisterDTO userRegisterDTO) throws RuntimeException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        User user = User.builder()
                .username(userRegisterDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()))
                .nickname(userRegisterDTO.getNickname())
                .age(userRegisterDTO.getAge())
                .mbti(userRegisterDTO.getMbti())
                .gender(userRegisterDTO.getGender())
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

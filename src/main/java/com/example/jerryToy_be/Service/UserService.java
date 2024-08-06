package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.UserRegisterDTO;
import com.example.jerryToy_be.DTO.UserResponseDTO;
import com.example.jerryToy_be.Entity.User;
import com.example.jerryToy_be.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
    public ResponseEntity<UserResponseDTO> getUserInfo(Long userId) throws RuntimeException{
        try{
            Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId));
            if(user.isPresent()){
                if(user.get().isValid()){
                    UserResponseDTO userResponseDTO = UserResponseDTO
                            .builder()
                            .userId(user.get().getUserId())
                            .nickname(user.get().getNickname())
                            .age(user.get().getAge())
                            .mbti(user.get().getMbti())
                            .gender(user.get().getGender())
                            .regDate(user.get().getRegDate())
                            .recent_match(user.get().getRecent_match())
                            .count(user.get().getCount())
                            .degree(user.get().getDegree())
                            .build();
                    return ResponseEntity
                            .ok()
                            .body(userResponseDTO);
                } else{
                    return ResponseEntity.status(401).build();
                }
            } else{
                return ResponseEntity.notFound().build();
            }
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }

//    // 유저정보 수정 - count++, recent_match 최신화
//    @Transactional
//    public void userAfterMatch(Long userId) throws RuntimeException{
//        User user = userRepository.findByUserId(userId);
//        if(user==null){
//            throw new RuntimeException("user not found");
//        }
//
//    }
}

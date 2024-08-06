package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserResponseDTO {
    private Long userId;
    private String nickname;
    private Integer age;
    private String gender;
    private String mbti;
    private Double degree;
    private Integer count;
    private String regDate;
    private String recent_match;

    public UserResponseDTO byEntity(User user) {
        return UserResponseDTO
                .builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .age(user.getAge())
                .gender(user.getGender())
                .mbti(user.getMbti())
                .degree(user.getDegree())
                .count(user.getCount())
                .regDate(user.getRegDate())
                .recent_match(user.getRecent_match())
                .build();
    }
}

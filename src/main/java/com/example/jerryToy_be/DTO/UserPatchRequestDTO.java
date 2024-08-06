package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPatchRequestDTO {
    private Long userId;
    private String password;
    private String nickname;
    private Integer age;
    private String gender;
    private String mbti;
    private String profile_image;
    public User toEntity(){
        return User.builder()..build();
    }
}
//@ModelAttribute
//@PatchMapping

package com.example.jerryToy_be.DTO;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private int user_id;
    private String nickname;
    private Integer age;
    private String gender;
    private String mbti;
    private String profile_image;
    private Double degree;
    private Integer count;
    private boolean isValid;

}

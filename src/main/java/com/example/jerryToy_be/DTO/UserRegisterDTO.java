package com.example.jerryToy_be.DTO;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDTO {
    private String username;
    private String password;
    private String nickname;
    private Integer age;
    private String gender;
    private String mbti;
   // private String profile_image;
    private Date date;
    //count=0; isValid=true; recent_match=null; degree=36.5; role=user


}

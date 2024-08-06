package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.Destination;
import com.example.jerryToy_be.Entity.Post;
import com.example.jerryToy_be.Entity.User;
import lombok.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSubmitDTO {
    private String title;
    private String content;
    private String destName;
    private String tag;

    public Post toEntity(User user, Destination dest){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Post.builder()
                .title(title)
                .content(content)
                .dest(dest)
                .user(user)
                .tag(tag)
                .likes(0)
                .views(0)
                .postDate(sdf.format(new Date()))
                .people(0)
                .isValid(true)
                .build();
    }
}

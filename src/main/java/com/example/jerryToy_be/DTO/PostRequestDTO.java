package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.Destination;
import com.example.jerryToy_be.Entity.Post;
import com.example.jerryToy_be.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
    private String destName;
    private String tag;
    private Integer people;

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
                .people(people)
                .isValid(true)
                .build();
    }
}

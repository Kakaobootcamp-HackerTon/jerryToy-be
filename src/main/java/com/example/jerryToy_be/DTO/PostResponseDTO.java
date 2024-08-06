package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.Post;
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
public class PostResponseDTO {
    private Long postId;
    private String title;
    private String content;
    private String tag;
    private DestResponseDTO destDTO;
    private UserResponseDTO userDTO;
    private Integer likes;
    private Integer views;
    private Integer people;
    private String postDate;
    public PostResponseDTO byEntity(Post post){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DestResponseDTO destDTO = new DestResponseDTO();
        UserResponseDTO userDTO = new UserResponseDTO();
        return PostResponseDTO
                .builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .tag(post.getTag())
                .destDTO(destDTO.byEntity(post.getDest()))
                .userDTO(userDTO.byEntity(post.getUser()))
                .likes(post.getLikes())
                .views(post.getViews())
                .postDate(sdf.format(new Date()))
                .people(post.getPeople())
                .build();
    }
}

package com.example.jerryToy_be.Entity;

import com.example.jerryToy_be.DTO.PostRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Destination dest;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String postDate;

    @Column
    private String matchDate;

    @Lob
    @Column
    private String tag;

    @Column
    private Integer likes;

    @Column
    private Integer views;

    @Column
    private Integer people;

    @Column
    private boolean isValid;

    public void updatePost(final PostRequestDTO postRequestDTO, final Destination dest){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.title = postRequestDTO.getTitle();
        this.content = postRequestDTO.getContent();
        this.dest = dest;
        this.tag = postRequestDTO.getTag();
        this.people = postRequestDTO.getPeople();
        this.matchDate = sdf.format(new Date());
    }
}

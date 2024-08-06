package com.example.jerryToy_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date date;

    @Lob
    @Column(name="TAG", length=512)
    private String tag;

    @Column
    private Integer likes;

    @Column
    private Integer views;
}

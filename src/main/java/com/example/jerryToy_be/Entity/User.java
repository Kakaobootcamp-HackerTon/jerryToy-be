package com.example.jerryToy_be.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private Integer age;

    @Column
    private String gender;

    @Column
    private String mbti;

    @Column
    private String profile_image;

    @Column
    private String role;

    @Column
    private Double degree;

    @Column
    private Integer count;

    @Column
    private boolean isValid;
}

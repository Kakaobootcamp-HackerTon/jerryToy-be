package com.example.jerryToy_be.Entity;


import jakarta.persistence.*;
import lombok.*;


import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
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
    private String role;

    @Column
    private Double degree;

    @Column
    private Integer count;

    @Column
    private String regDate;

    @Column
    private String recent_match;

    @Column
    private boolean isValid;

    public void matchUser(User user){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.recent_match = sdf.format(new Date());
        this.count = user.getCount()+1;
        this.degree = user.getDegree()+1;
    }
}

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
public class Matching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="match_id")
    private Long matchId;

    @Column(name="user_id")
    private Long userId;

    @Column
    private String matchDate;

    @Column
    private String location;

    @Column
    private String status;
}

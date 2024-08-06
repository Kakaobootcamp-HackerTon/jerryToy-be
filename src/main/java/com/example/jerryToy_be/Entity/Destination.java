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
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dest_id")
    private Long destId;

    @Column(name="dest_name", nullable = false)
    private String destName;

    @Column
    private String label;

    @Column
    private String address;

    @Column
    private String roadaddress;

    @Lob
    @Column(length=512)
    private String tag;

    @Lob
    @Column(length=512)
    private String introduction;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String postcode;

    @Column
    private String phoneno;

    @Column
    private String region1;

    @Column
    private String region2;
}

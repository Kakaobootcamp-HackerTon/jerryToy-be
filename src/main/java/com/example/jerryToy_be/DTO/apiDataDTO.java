package com.example.jerryToy_be.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class apiDataDTO {
    private String alltag;
    private String contentsid;

    private String value;
    private String label;
    private String refId;

    private String title;
    private String region1cd;
    private String region2cd;
    private String address;
    private String roadaddress;
    private String tag;
    private String introduction;
    private Double latitude;
    private Double longitude;
    private String postcode;
    private String phoneno;

    private String descseo;
    private String photoid;
    private String imgpath;
    private String thumbnailpath;
}

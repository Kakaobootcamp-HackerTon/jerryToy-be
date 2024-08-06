package com.example.jerryToy_be.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestPosReqDTO {
    private Double latitude;
    private Double longitude;
}

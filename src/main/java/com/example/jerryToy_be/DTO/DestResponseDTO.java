package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DestResponseDTO {
    private Long destId;
    private String destName;
    private String label;
    private String address;
    private String roadaddress;
    private String introduction;
    private Double latitude;
    private Double longitude;
    private String[] tagList;

    private String[] destTagListGenerator(String str) {
        // 태그 리스트 만드는 로직
        String[] tagList = str.split(",");
        for(String tagStr : tagList){
            tagStr = tagStr.trim();
        }
        return tagList;
    }

    public DestResponseDTO byEntity(Destination dest){
        return DestResponseDTO
                .builder()
                .destId(dest.getDestId())
                .destName(dest.getDestName())
                .label(dest.getLabel())
                .address(dest.getAddress())
                .roadaddress(dest.getRoadaddress())
                .introduction(dest.getIntroduction())
                .latitude(dest.getLatitude())
                .longitude(dest.getLongitude())
                .tagList(destTagListGenerator(dest.getTag()))
                .build();
    }
}

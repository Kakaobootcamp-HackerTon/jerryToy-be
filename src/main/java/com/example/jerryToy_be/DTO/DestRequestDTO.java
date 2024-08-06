package com.example.jerryToy_be.DTO;

import com.example.jerryToy_be.Entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestRequestDTO {
    private String destName;
    private String label;
    private Double latitude;
    private Double longitude;
    private String[] tagList;
    private String introduction;

    private String destTagGenerator(String[] tagList) {
        // 태그 스트링 만드는 로직
        return String.join(",", tagList);
    }

    public Destination toEntity(){
        return Destination.builder()
                .destName(destName)
                .label(label)
                .latitude(latitude)
                .longitude(longitude)
                .tag(destTagGenerator(tagList))
                .introduction(introduction)
                .build();
    }

}

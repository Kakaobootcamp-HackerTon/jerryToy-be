package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.apiDataDTO;
import com.example.jerryToy_be.Entity.Destination;
import com.example.jerryToy_be.Entity.Tag;
import com.example.jerryToy_be.Repository.DestRepository;
import com.example.jerryToy_be.Repository.TagRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Builder
@AllArgsConstructor
@Service
public class ApiDataService {
    private final TagRepository tagRepository;
    private final DestRepository destRepository;

    public void parseAndSave(apiDataDTO apiDataDTO) throws RuntimeException {
        Destination dest = Destination.builder()
                .destName(apiDataDTO.getTitle())
                .label(apiDataDTO.getLabel())
                .postcode(apiDataDTO.getPostcode())
                .address(apiDataDTO.getAddress())
                .roadaddress(apiDataDTO.getRoadaddress())
                .tag(apiDataDTO.getTag())
                .introduction(apiDataDTO.getIntroduction())
                .latitude(apiDataDTO.getLatitude())
                .longitude(apiDataDTO.getLongitude())
                .phoneno(apiDataDTO.getPhoneno())
                .region1(apiDataDTO.getRegion1cd())
                .region2(apiDataDTO.getRegion2cd())
                .build();
        destRepository.save(dest);
        String tagList = apiDataDTO.getTag();
        String[] splitTags = tagList.split("[.,]");
        for (String tag : splitTags) {
            if(tagRepository.findByTagName(tag.trim())==null){
                tagRepository.save(Tag.builder().tagName(tag.trim()).build());
            }
        }
    }

}

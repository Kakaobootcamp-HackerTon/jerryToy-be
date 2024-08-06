package com.example.jerryToy_be.Service;

import com.example.jerryToy_be.DTO.DestResponseDTO;
import com.example.jerryToy_be.Entity.Destination;
import com.example.jerryToy_be.Repository.DestRepository;
import com.example.jerryToy_be.DTO.DestRequestDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public class DestService {
    private final DestRepository destRepository;
    private final DestResponseDTO destResponseDTO;
    public ResponseEntity<DestResponseDTO> getDestById(Long destId){
        try{
            Optional<Destination> dest = destRepository.findById(destId);
            if(dest.isPresent()){
                return ResponseEntity.ok().body(destResponseDTO.byEntity(dest.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<DestResponseDTO>> getDestsByLabel(String label){
        try{
            Optional<List<Destination>> destList = Optional.ofNullable(destRepository.findAllByLabel(label));
            if(destList.isPresent()){
                List<DestResponseDTO> destResponseDTOs = new ArrayList<>();
                for(Destination dest : destList.get()){
                    destResponseDTOs.add(destResponseDTO.byEntity(dest));
                }
                return ResponseEntity.ok().body(destResponseDTOs);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity createDest(DestRequestDTO destRequestDTO){
        try{
            destRepository.save(destRequestDTO.toEntity());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}

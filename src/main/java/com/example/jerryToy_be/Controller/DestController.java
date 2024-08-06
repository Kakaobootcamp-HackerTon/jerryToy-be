package com.example.jerryToy_be.Controller;

import com.example.jerryToy_be.DTO.DestPosReqDTO;
import com.example.jerryToy_be.DTO.DestRequestDTO;
import com.example.jerryToy_be.DTO.DestResponseDTO;
import com.example.jerryToy_be.Service.DestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dest")
public class DestController {
    private final DestService destService;
    @PostMapping
    public ResponseEntity<List<DestResponseDTO>> findDestsByLabel(String[] label) {
        System.out.println(label);
        try{
            return destService.getDestsByLabel(label);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/{destId}")
    public ResponseEntity<DestResponseDTO> findDestById(@PathVariable Long destId) {
        System.out.println(destId);
        try{
            return destService.getDestById(destId);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/new")
    public ResponseEntity createDest(@RequestBody DestRequestDTO destRequestDTO) {
        try{
            return destService.createDest(destRequestDTO);
        } catch(RuntimeException e){
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/find")
    public ResponseEntity<DestResponseDTO> findDestByPosition(@RequestBody DestPosReqDTO destPosReqDTO) {
        try{
            return destService.searchByPosition(destPosReqDTO.getLatitude(), destPosReqDTO.getLongitude());
        } catch(RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

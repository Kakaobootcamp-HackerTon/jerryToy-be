package com.example.jerryToy_be.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequestDTO {
    private String username;
    private String password;
}

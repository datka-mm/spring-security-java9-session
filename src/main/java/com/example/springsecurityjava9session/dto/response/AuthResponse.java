package com.example.springsecurityjava9session.dto.response;

import com.example.springsecurityjava9session.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private Long id;
    private String token;
    private String email;
    private Role role;
}

package com.example.springsecurityjava9session.dto.request;

import com.example.springsecurityjava9session.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}

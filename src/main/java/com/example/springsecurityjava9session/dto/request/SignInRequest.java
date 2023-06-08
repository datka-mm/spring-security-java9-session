package com.example.springsecurityjava9session.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequest {

    private String email;
    private String password;
}

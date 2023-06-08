package com.example.springsecurityjava9session.api;

import com.example.springsecurityjava9session.dto.request.SignInRequest;
import com.example.springsecurityjava9session.dto.request.SignUpRequest;
import com.example.springsecurityjava9session.dto.response.AuthResponse;
import com.example.springsecurityjava9session.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthApi {

    private final UserService userService;

    @PostMapping("/singUp")
    AuthResponse signUp(@RequestBody SignUpRequest request) {
        return userService.signUp(request);
    }

    @PostMapping("/signIn")
    AuthResponse signIn(@RequestBody SignInRequest request) {
        return userService.signIn(request);
    }
}

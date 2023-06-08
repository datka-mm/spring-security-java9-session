package com.example.springsecurityjava9session.service;

import com.example.springsecurityjava9session.config.JwtService;
import com.example.springsecurityjava9session.dto.request.SignInRequest;
import com.example.springsecurityjava9session.dto.request.SignUpRequest;
import com.example.springsecurityjava9session.dto.response.AuthResponse;
import com.example.springsecurityjava9session.entity.User;
import com.example.springsecurityjava9session.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityExistsException(
                    "User with email: " + request.getEmail() + " already exists!"
            );
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .id(user.getId())
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }


    public AuthResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "USer with email: " + signInRequest.getEmail() + " not found"
                ));

        if(signInRequest.getEmail().isBlank()){
            throw new BadCredentialsException("Email doesn't exist!");
        }

        if(!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Incorrect password!");
        }

        String jwtToken=jwtService.generateToken(user);

        return AuthResponse
                .builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }
}

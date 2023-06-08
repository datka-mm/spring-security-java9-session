package com.example.springsecurityjava9session.dto.response;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record BoardResponse(
        Long id,
        String name,
        String imageLink,
        ZonedDateTime createdAt
) {
}

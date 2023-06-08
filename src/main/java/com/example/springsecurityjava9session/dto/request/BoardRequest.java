package com.example.springsecurityjava9session.dto.request;

import lombok.Builder;

@Builder
public record BoardRequest(
        String name,
        String imageLink
) {
}

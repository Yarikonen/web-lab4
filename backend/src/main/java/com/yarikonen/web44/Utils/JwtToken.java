package com.yarikonen.web44.Utils;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtToken {
    private String accessToken;
    private String refreshToken;
}
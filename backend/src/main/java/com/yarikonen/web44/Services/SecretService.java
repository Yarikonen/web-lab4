package com.yarikonen.web44.Services;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yarikonen.web44.Utils.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
public class SecretService {
    private final Algorithm jwtAccessSecret;
    private final Algorithm jwtRefreshSecret;
    private final MessageDigest encoder;
    private final static String pepper ="4MOgu5";

    public SecretService(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
            ) throws NoSuchAlgorithmException {
        this.jwtAccessSecret = Algorithm.HMAC256(jwtAccessSecret);
        this.jwtRefreshSecret = Algorithm.HMAC256(jwtRefreshSecret);
        this.encoder = MessageDigest.getInstance("SHA-256");
    }
    public synchronized String createHash(String password) {
        password = pepper +password;
        encoder.update(password.getBytes());
        return new String(encoder.digest());
    }
    public JwtToken generateToken(String login){
        String refreshToken = generateRefreshToken(login);
        String accessToken = generateAccessToken(login);
        return new JwtToken(accessToken, refreshToken);
    }
    public JwtToken refreshJwtToken(String login, String refreshToken){
        try {
            if((validateRefreshToken(refreshToken))){
                return generateToken(login);
            }
        }
        catch(JWTVerificationException exp){
            throw new JWTVerificationException(exp.getMessage());
        }
        return null;
    }
    private String generateAccessToken(String login){
        return JWT.create()
                .withClaim("login", login)
                .withExpiresAt(Instant.now().plus(5, ChronoUnit.MINUTES))
                .sign(jwtAccessSecret);
    }
    private String generateRefreshToken(String login){
        return JWT.create()
                .withClaim("login", login)
                .withExpiresAt(Instant.now().plus(30, ChronoUnit.DAYS))
                .sign(jwtRefreshSecret);
    }
    public boolean validateRefreshToken(String RefreshToken){
        return validateToken(RefreshToken,jwtRefreshSecret);
    }
    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken,jwtAccessSecret);
    }
    private boolean validateToken(String token, Algorithm secret){
        if (token==null){
            return false;
        }
        JWTVerifier verifier = JWT.require(secret).build();
        verifier.verify(token);

        return true;
    }
}




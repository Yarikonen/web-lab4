package com.yarikonen.web4.Controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yarikonen.web4.Annotations.LogEntryExit;
import com.yarikonen.web4.Exceptions.AuthException;
import com.yarikonen.web4.Services.AuthService;
import com.yarikonen.web4.Services.SecretService;
import com.yarikonen.web4.Utils.JwtToken;
import com.yarikonen.web4.Utils.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RequestMapping("/api/")
@RestController
public class AuthController {
    AuthService authService;
    SecretService secretService;
    @Autowired
    AuthController(AuthService service, SecretService secretService){
        this.authService=service;
        this.secretService=secretService;
    }


    @LogEntryExit
    @PostMapping("login")
    public ResponseEntity<JwtToken> login(@RequestBody LoginForm loginForm){
        try {
            JwtToken jwtToken = authService.login(loginForm);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        catch(AuthException exp) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,exp.getMessage());
        }
    }
    @PostMapping("register")
    public ResponseEntity<JwtToken> register(@RequestBody LoginForm loginForm){
        try {
            JwtToken jwtToken = authService.register(loginForm);
            return new ResponseEntity<>(jwtToken,HttpStatus.OK);
        }
        catch(AuthException exp) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exp.getMessage());
        }
    }
    @GetMapping("validateAccessToken")
    public boolean validateAccessToken(@RequestParam String accessToken){
        try {
            return secretService.validateAccessToken(accessToken);
        }
        catch(JWTVerificationException exp){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exp.getMessage());

        }
    }

    @GetMapping("refreshToken")
    public ResponseEntity<JwtToken> refreshAccessToken(@RequestParam String refreshToken, @RequestParam String login){
        try {
            return new ResponseEntity<>(secretService.refreshJwtToken(login, refreshToken), HttpStatus.OK);
        }
        catch(TokenExpiredException exp){
            return new ResponseEntity<>(null, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);

        }

    }

    //TODO @ExceptionHandler

}

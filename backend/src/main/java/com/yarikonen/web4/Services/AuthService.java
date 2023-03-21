package com.yarikonen.web4.Services;


import com.yarikonen.web4.Data.User;
import com.yarikonen.web4.Exceptions.AuthException;
import com.yarikonen.web4.Utils.JwtToken;
import com.yarikonen.web4.Utils.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserService userService;
    private SecretService secretService;
    @Autowired
    public AuthService(UserService userService, SecretService secretService){
        this.userService=userService;
        this.secretService=secretService;
    }


    public JwtToken login(LoginForm loginForm) throws AuthException{
        if(!userService.existsByLogin(loginForm.getLogin())){
            throw new AuthException("No such user.");
        }
        User user = userService.getByLogin(loginForm.getLogin());
        if(!user.getPassword().equals(secretService.createHash(loginForm.getPassword()))){
            throw new AuthException("Wrong password.");
        }
        return secretService.generateToken(loginForm.getLogin());
    }
    public JwtToken register(LoginForm loginForm){
        if(loginForm.getLogin()==null || loginForm.getLogin().isEmpty()){
            throw new AuthException("Empty username");
        }
        if(loginForm.getPassword()==null || loginForm.getPassword().isEmpty()){
            throw new AuthException("Empty password");
        }
        if(userService.existsByLogin(loginForm.getLogin())){
            throw new AuthException("Username taken.");
        }

        userService.addUser(new User(loginForm.getLogin(), secretService.createHash(loginForm.getPassword())));
        return secretService.generateToken(loginForm.getLogin());

    }



}

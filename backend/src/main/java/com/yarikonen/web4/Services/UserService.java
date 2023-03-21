package com.yarikonen.web4.Services;

import com.yarikonen.web4.Annotations.LogEntryExit;
import com.yarikonen.web4.Data.User;
import com.yarikonen.web4.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository ){
        this.userRepository=userRepository;
    }
    @LogEntryExit
    public User addUser(User user){
        return(userRepository.save(user));
    }

    @LogEntryExit
    public boolean existsByLogin(String login) { return userRepository.existsByLogin(login);}

    @LogEntryExit
    public User getByLogin(String login){
        return(userRepository.findUserByLogin(login));
    }
}

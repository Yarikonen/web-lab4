package com.yarikonen.web4.Repositories;

import com.yarikonen.web4.Data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByLogin(String login);
    boolean existsByLogin(String login);
}

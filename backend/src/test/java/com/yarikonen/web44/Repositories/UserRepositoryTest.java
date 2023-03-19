package com.yarikonen.web44.Repositories;

import com.yarikonen.web44.Data.User;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository tested;
    @Test
    void canUserByLogin(){
        User user = new User("aboba", "aboba");
        tested.save(user);

        assertThat(user).isEqualTo(tested.findUserByLogin("aboba"));

    }
    @Test
    void cantFindUserByLogin(){
        assertThat(tested.findUserByLogin("aboba")).isNull();
    }
    @Test
    void canExistByLogin(){
        User user = new User("aboba", "aboba");
        tested.save(user);

        assertThat(tested.existsByLogin("aboba"));

    }
    @Test
    void notExistUserByLogin(){
        assertThat(!tested.existsByLogin("aboba"));
    }

}
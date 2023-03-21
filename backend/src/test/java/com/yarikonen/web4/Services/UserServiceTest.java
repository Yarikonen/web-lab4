package com.yarikonen.web4.Services;

import com.yarikonen.web4.Data.User;
import com.yarikonen.web4.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService underTest;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void initRepository(){
        this.underTest=new UserService(repository);
    }

    @Test
    void addUser() {
        User user = new User("123","123");
        underTest.addUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(repository).save(userArgumentCaptor.capture());

        assertThat(userArgumentCaptor.getValue()).isEqualTo(user);

    }

    @Test
    void existsByLogin() {
        String login = "asd";
        underTest.existsByLogin(login);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(repository).existsByLogin(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo(login);

    }

    @Test
    void getByLogin() {
        String login = "asd";
        underTest.getByLogin(login);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(repository).findUserByLogin(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualTo(login);
    }
}
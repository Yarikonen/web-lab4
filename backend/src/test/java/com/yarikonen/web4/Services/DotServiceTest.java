package com.yarikonen.web4.Services;

import com.yarikonen.web4.Data.Dot;
import com.yarikonen.web4.Repositories.DotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DotServiceTest {
    private DotService underTest;
    private CheckHitService checkHitService;
    @Mock
    private DotRepository repository;

    @BeforeEach
    void initUseCase(){
        checkHitService=new CheckHitService();
        underTest = new DotService(repository, checkHitService);

    }


    @Test
    void canGetAllDots() {
        //when
        underTest.getDots();
        //then
        verify(repository).findAll();

    }

    @Test
    void canAddDot() {
        //given
        Dot dot = new Dot(1L,1.3,1.9,1.0, LocalTime.now(),"yes",1,"f");
        //when
        underTest.addDot(dot);
        //then
        ArgumentCaptor<Dot> dotArgumentCaptor=ArgumentCaptor.forClass(Dot.class);
        verify(repository).save(dotArgumentCaptor.capture());

        Dot captured = dotArgumentCaptor.getValue();

        assertThat(captured).isEqualTo(dot);
    }

    @Test
    void birthTimeChanged(){
        //given
        Dot dot = new Dot(1L,1.3,1.9,1.0, LocalTime.now(),"yes",1,"f");
        //when
        when(underTest.addDot(dot)).then(returnsFirstArg());
        dot = underTest.addDot(dot);
        //then
        assertThat(dot.getBirthTime()).isNotNull();
    }


}
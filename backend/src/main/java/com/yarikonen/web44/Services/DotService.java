package com.yarikonen.web44.Services;

import com.yarikonen.web44.Annotations.LogEntryExit;
import com.yarikonen.web44.Data.Dot;
import com.yarikonen.web44.Repositories.DotRepository;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class DotService {
    private final DotRepository repository;
    private final CheckHitService checkHitService;
    @Autowired
    public DotService(DotRepository dotRepository, CheckHitService checkHitService){
        this.repository=dotRepository;
        this.checkHitService=checkHitService;

    }
    @LogEntryExit
    public List<Dot> getDots(){
        return repository.findAll();
    }

    @LogEntryExit
    public Dot addDot(Dot dot) throws  PropertyValueException ,NullPointerException{
        dot.setBirthTime(LocalTime.now());
        checkHitService.checkZone(dot);
        return(repository.save(dot));




    }



    public void cleanDots(String login) {
        repository.deleteAllByUserName(login);
    }
}

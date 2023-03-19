package com.yarikonen.web44.Controllers;


import com.yarikonen.web44.Annotations.LogEntryExit;
import com.yarikonen.web44.Data.Dot;
import com.yarikonen.web44.Services.DotService;
import com.yarikonen.web44.Utils.DotRequest;
import jakarta.transaction.Transactional;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@RequestMapping("/api")
@RestController
public class DotController {
    DotService dotService;
    @Autowired
    DotController(DotService dotService){
        this.dotService = dotService;

    }
    @LogEntryExit(showResult = true)
    @GetMapping("/dots")
    public List<Dot> getDots(){
        return dotService.getDots();
    }
    @LogEntryExit(showArgs = true)
    @PostMapping("/dots/newDot")
    public ResponseEntity<String> addDot(@RequestBody DotRequest requestDot){
        try {
            Dot dot = new Dot(requestDot.getX(), requestDot.getY(), requestDot.getR(), requestDot.getUserName());
            dotService.addDot(dot);
            return ResponseEntity.ok("Dot is valid");
        }catch(PropertyValueException exp){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exp.getMessage());
        }
    }
    @Transactional
    @PostMapping("dots/clear")
    public ResponseEntity<String> cleanDots(@RequestBody String login){
        System.out.println(login);
        dotService.cleanDots(login);
        return ResponseEntity.ok("Successfull");
    }
}

package com.yarikonen.web44.Services;


import com.yarikonen.web44.Data.Dot;
import org.springframework.stereotype.Service;

@Service
public class CheckHitService {
    public void checkZone(Dot dot) {
        long exTime=System.nanoTime();
        double x=dot.getX();
        double y =dot.getY();
        double r = dot.getR();
        if (x>=0 &&y>=0 && y<=r && x<=r/2) dot.setHit("hit");
        if (x>0 && y<=0 && x*x+y*y<=r*r/4) dot.setHit("hit");
        if (x<0 && y<=0 && y>=-x-r) dot.setHit("hit");
        exTime = -exTime + System.nanoTime();
        dot.setExTime(exTime);

    }
}

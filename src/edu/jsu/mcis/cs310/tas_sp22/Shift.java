package edu.jsu.mcis.cs310.tas_sp22;

import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.*;

public class Shift {
    private String description;
    private LocalTime shiftstart, shiftstop, lunchstart, lunchstop;
    private int interval, period, penalty;

    public Shift(LinkedHashMap<String,String> param ) {
        this.shiftstart = LocalTime.parse(param.get("shiftstart"));
        this.shiftstop = LocalTime.parse(param.get("shiftstop"));
        this.lunchstart = LocalTime.parse(param.get("lunchstart"));
        this.lunchstop = LocalTime.parse(param.get("lunchstop"));
        this.description = param.get("description");
        this.period = Integer.parseInt(param.get("graceperiod"));
        this.interval = Integer.parseInt(param.get("roundinterval"));
        this.penalty = Integer.parseInt(param.get("dockpenalty"));
    }

    public int getInterval() {
        return interval;
    }

    public int getPeriod() {
        return period;
    }

    public int getPenalty() {
        return penalty;
    }
    
    private long getDuration(LocalTime Time1, LocalTime Time2){
        long amount = Time1.until(Time2, MINUTES);
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getShiftstart() {
        return shiftstart;
    }

    public LocalTime getShiftstop() {
        return shiftstop;
    }

    public LocalTime getLunchstart() {
        return lunchstart;
    }

    public LocalTime getLunchstop() {
        return lunchstop;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append(description).append(": ");
        s.append(shiftstart).append(" - ");
        s.append(shiftstop).append(" ").append("(");
        s.append(getDuration(shiftstart,shiftstop)).append(" minutes").append(")");
        s.append("; ").append("Lunch").append(": ");
        s.append(lunchstart).append(" - ");
        s.append(lunchstop).append(" ").append("(");
        s.append(getDuration(lunchstart,lunchstop)).append(" minutes").append(")");
        
        return s.toString();
    }
    
    
}

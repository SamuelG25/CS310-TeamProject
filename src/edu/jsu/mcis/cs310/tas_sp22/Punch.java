package edu.jsu.mcis.cs310.tas_sp22;

import java.security.Timestamp;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;


public class Punch {
    private String badgeID;
    private int terminalID;
    private PunchType eventType;
    private int ID;
    private LocalDateTime originalTimeStamp;
    private LocalTime adjustedTimeStamp;
    

    public Punch(LinkedHashMap<String,String> param) {
        this.badgeID = param.get("badgeid");
        this.terminalID = Integer.parseInt(param.get("terminalid"));
        this.eventType = PunchType.values()[Integer.parseInt(param.get("eventtypeid"))];
        this.originalTimeStamp = LocalDateTime.parse(param.get("timestamp"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.adjustedTimeStamp = null;
        this.ID = 0;
        
    }
    
    public void adjust(Shift s){
        LocalTime shiftstart = s.getShiftstart();
        LocalTime shiftstop = s.getShiftstop();
        LocalTime lunchstart = s.getLunchstart();
        LocalTime lunchstop = s.getLunchstop();
        
        
        LocalTime intervalTime = shiftstart.minusHours(s.getInterval());
        LocalTime periodTime = shiftstart.plusHours(s.getPeriod());
        LocalTime penaltyTime = shiftstart.plusHours(s.getPenalty());
        
        LocalTime ogtimestamp = LocalTime.of(this.originalTimeStamp.getHour(), this.originalTimeStamp.getMinute());
        
        if (ogtimestamp.isAfter(intervalTime) && ogtimestamp.isBefore(shiftstart)){
            adjustedTimeStamp = shiftstart;
        }
        else if (ogtimestamp.isAfter(shiftstart) && ogtimestamp.isBefore(periodTime)){
            adjustedTimeStamp = shiftstart;
        }
        else if (ogtimestamp.isAfter(shiftstart) && ogtimestamp.isBefore(penaltyTime)){
            adjustedTimeStamp = penaltyTime;
        }
        
    }
    
    
    
    
    public String getBadgeID() {
        return badgeID;
    }

    public int getTerminalID() {
        return terminalID;
    }

    public PunchType getEventType() {
        return eventType;
    }
    
    public int ID(){
        return ID;
    }

    public LocalDateTime getOriginalTimeStamp() {
        return originalTimeStamp;
    }

    public LocalTime getAdjustedTimeStamp() {
        return adjustedTimeStamp;
    }
    
    
    public String printOriginal() {
        
        // "#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07"
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badgeID);
        s.append(" ").append(eventType);
        s.append(": ").append(dtf.format(originalTimeStamp));
        

        return s.toString().toUpperCase();
    }
    
  public String printAdjusted() {
        
        // "#D2C39273 CLOCK IN: WED 09/05/2018 07:00:07 "
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badgeID);
        s.append(" ").append(eventType);
        s.append(": ").append(dtf.format(originalTimeStamp));
        s.append("( ").append(adjustedTimeStamp);
        

        return s.toString().toUpperCase();
    }
}

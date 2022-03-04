package edu.jsu.mcis.cs310.tas_sp22;

import java.security.Timestamp;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Punch {
    private String badgeID;
    private int terminalID;
    private PunchType eventType;
    private int ID;
    private LocalDateTime originalTimeStamp;
    private LocalTime adjustedTimeStamp;

    public Punch(LinkedHashMap<String,String> param) {
        this.badgeID = ("badgeid");
        this.terminalID = Integer.parseInt("terminalid");
        this.eventType = PunchType.values()[Integer.parseInt("eventtypeid")];
        this.originalTimeStamp = LocalDateTime.parse(("timestamp"),DateTimeFormatter.ofPattern("EEE MM-dd-yyyy HH:mm:ss"));
        this.adjustedTimeStamp = null;
        this.ID = 0;
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
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(badgeID);
        s.append(" ").append(eventType);
        s.append(": ").append(originalTimeStamp);

        return s.toString();
    }
    
}

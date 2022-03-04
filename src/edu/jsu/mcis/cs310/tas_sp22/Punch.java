
package edu.jsu.mcis.cs310.tas_sp22;

import java.security.Timestamp;
import java.util.*;
import java.time.*;


public class Punch {
    private String badgeID;
    private int terminalID;
    private int eventType;
    private LocalTime originalTimeStamp;
    private LocalTime adjustedTimeStamp;

    public Punch(LinkedHashMap<String,String> param) {
        this.badgeID = ("badgeid");
        this.terminalID = Integer.parseInt("terminalid");
        this.eventType = Integer.parseInt("eventtypeid");
        this.originalTimeStamp = LocalTime.parse("timestamp");
    }
    
    enum PunchType {
    
    CLOCK_OUT,
    CLOCK_IN,
    TIME_OUT;
    }

    public String getBadgeID() {
        return badgeID;
    }

    public int getTerminalID() {
        return terminalID;
    }

    public int getEventType() {
        return eventType;
    }

    public LocalTime getOriginalTimeStamp() {
        return originalTimeStamp;
    }

    public LocalTime getAdjustedTimeStamp() {
        return adjustedTimeStamp;
    }
    
    
    
}

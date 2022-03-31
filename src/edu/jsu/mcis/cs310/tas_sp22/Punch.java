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
    private LocalDateTime adjustedTimeStamp;
    private String adjustMessage;

    public Punch(LinkedHashMap<String,String> param) {
        this.badgeID = param.get("badgeid");
        this.terminalID = Integer.parseInt(param.get("terminalid"));
        this.eventType = PunchType.values()[Integer.parseInt(param.get("eventtypeid"))];
        this.originalTimeStamp = LocalDateTime.parse(param.get("timestamp"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.adjustedTimeStamp = null;
        this.ID = 0;
        
    }
    
    public void adjust(Shift s){
        
        
        LocalDateTime shiftStart = originalTimeStamp.withHour(s.getShiftstart().getHour())
                .withMinute(s.getShiftstart().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime shiftStop = originalTimeStamp.withHour(s.getShiftstop().getHour())
                .withMinute(s.getShiftstop().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime lunchStart = originalTimeStamp.withHour(s.getLunchstart().getHour())
                .withMinute(s.getLunchstart().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime lunchStop = originalTimeStamp.withHour(s.getLunchstop().getHour())
                .withMinute(s.getLunchstop().getMinute()).withSecond(0).withNano(0);
        
        LocalDateTime shiftStartInterval = shiftStart.minusMinutes(s.getInterval());
        LocalDateTime shiftStartGrace = shiftStart.plusMinutes(s.getPeriod());
        LocalDateTime shiftStartDock = shiftStart.plusMinutes(s.getPenalty());
        
        LocalDateTime shiftStopInterval = shiftStop.plusMinutes(s.getInterval());
        LocalDateTime shiftStopGrace = shiftStop.minusMinutes(s.getPeriod());
        LocalDateTime shiftStopDock = shiftStop.minusMinutes(s.getPenalty());
        
        DayOfWeek day = originalTimeStamp.getDayOfWeek();
        
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            
            int minute = originalTimeStamp.getMinute();
            int interval = s.getInterval();
            int adjustedMinute = 0;
                
                if(minute % interval !=0){
                    if ((minute % interval) < (interval/2)){
                        adjustedMinute = Math.round(( minute/interval)* interval);
                        adjustedTimeStamp = originalTimeStamp.withMinute(adjustedMinute).withSecond(0);
                        adjustMessage = "Interval Round";
                    }
                    
                    else{
                    adjustedMinute = Math.round(((minute/interval) *interval) + interval);
                    adjustedTimeStamp = originalTimeStamp.withMinute(adjustedMinute);
                    adjustMessage = "Interval Round";
                    }
                }
                
                else if (minute % interval == 0){
                    adjustedTimeStamp = originalTimeStamp.withSecond(0);
                    adjustMessage = "None";
                }
        }
        
        else if (eventType == PunchType.CLOCK_IN) {
        
            if (originalTimeStamp.isAfter(shiftStartInterval) && originalTimeStamp.isBefore(shiftStart)){
                adjustedTimeStamp = shiftStart;
                adjustMessage = "Shift Start";
            }
            else if (originalTimeStamp.isAfter(shiftStart) && originalTimeStamp.isBefore(shiftStartGrace)){
                adjustedTimeStamp = shiftStart;
                adjustMessage = "Shift Start";
            }
            else if (originalTimeStamp.isAfter(shiftStart) && originalTimeStamp.isBefore(shiftStartDock) 
                    || originalTimeStamp.equals(shiftStartDock)){
                adjustedTimeStamp = shiftStartDock;
                adjustMessage = "Shift Dock";
            }
            else if (originalTimeStamp.isAfter(lunchStart) && originalTimeStamp.isBefore(lunchStop)){
                adjustedTimeStamp = lunchStop;
                adjustMessage = "Lunch Stop";
            }
            else if (originalTimeStamp.withSecond(0) == shiftStart){
              originalTimeStamp = shiftStart;
              adjustMessage = "None";
            }
            
            else {
                
                int minute = originalTimeStamp.getMinute();
                int interval = s.getInterval();
                int adjustedMinute = 0;
                
                if(minute % interval !=0){
                    if ((minute % interval) < (interval/2)){
                        adjustedMinute = Math.round(( minute/interval)* interval);
                        adjustedTimeStamp = originalTimeStamp.withMinute(adjustedMinute).withSecond(0);
                        adjustMessage = "Interval Round";
                    }
                    else{
                    
                    adjustedMinute = Math.round(((minute/interval) *interval) + interval);
                    adjustedTimeStamp = originalTimeStamp.withMinute(adjustedMinute).withSecond(0);
                    adjustMessage = "Interval Round";
                    }
                }
                else if (minute % interval == 0){
                    adjustedTimeStamp = originalTimeStamp.withSecond(0);
                    adjustMessage = "None";
                }
            }
        }
            
            
        
        else if (eventType == PunchType.CLOCK_OUT){
            
            
            if (originalTimeStamp.isAfter(lunchStart) && originalTimeStamp.isBefore(lunchStop) ){
                adjustedTimeStamp = lunchStart;
                adjustMessage = "Lunch Start";
            }
            else if (originalTimeStamp.isAfter(shiftStopDock) && originalTimeStamp.isBefore(shiftStopGrace)
                    || originalTimeStamp.equals(shiftStartDock)){
                 adjustedTimeStamp = shiftStopDock;
                 adjustMessage = "Shift Dock";
            }
            else if (originalTimeStamp.isAfter(shiftStopGrace) && originalTimeStamp.isBefore(shiftStop)
                    ||originalTimeStamp.equals(shiftStop)){
                 adjustedTimeStamp = shiftStop;
                 adjustMessage = "Shift Stop";
            }
            else if (originalTimeStamp.isAfter(shiftStop) && originalTimeStamp.isBefore(shiftStopInterval)){
                adjustedTimeStamp = shiftStop;
                adjustMessage = "Shift Stop";
            }
            
            else {
                
                int minute = originalTimeStamp.getMinute();
                int interval = s.getInterval();
                int adjustedMinute = 0;
                
                if(minute % interval !=0){
                    if ((minute % interval) < (interval/2)){
                        adjustedMinute = Math.round(( minute/interval)* interval);
                        adjustedTimeStamp = originalTimeStamp.withMinute(adjustedMinute).withSecond(0);
                        adjustMessage = "Interval Round";
                    }
                    else{
                    
                    adjustedMinute = Math.round(((minute/interval) *interval) + interval);
                    adjustedTimeStamp = originalTimeStamp.withMinute(adjustedMinute).withSecond(0);
                    adjustMessage = "Interval Round";
                    }
                }
                else if (minute % interval == 0){
                    adjustedTimeStamp = originalTimeStamp.withSecond(0);
                    adjustMessage = "None";
                }
            }    
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

    public LocalDateTime getAdjustedTimeStamp() {
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
        s.append(": ").append(dtf.format(adjustedTimeStamp).toUpperCase());
        s.append(" (").append(adjustMessage).append(")");
        

        return s.toString();
    }
}

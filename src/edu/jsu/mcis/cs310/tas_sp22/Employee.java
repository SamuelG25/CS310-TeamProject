package edu.jsu.mcis.cs310.tas_sp22;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
        
public class Employee {
    
    private String badgeid = null;
    private String firstname = null;
    private String middlename = null;
    private String lastname = null;
    private int employeetypeid, departmentid, id;
    private String shiftid;
    private LocalDateTime active, inactive;
    private String inactiveString, activeString;
    
    public Employee(LinkedHashMap<String, String> params)
    {
        
        this.badgeid = params.get("badgeid");
        this.firstname = params.get("firstname");
        this.middlename = params.get("middlename");
        this.lastname = params.get("lastname");
        this.employeetypeid = Integer.parseInt(params.get("employeetypeid"));
        this.departmentid = Integer.parseInt(params.get("departmentid"));
        this.id = Integer.parseInt(params.get("id"));
        this.shiftid = params.get("shiftid");
        
        if ((params.get("active")) != null) {
            this.active = LocalDateTime.parse(params.get("active"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            this.inactiveString = "none";
        }
        else {
            this.inactive = LocalDateTime.parse(params.get("inactive"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } 
            
        this.activeString = "none";
    }
    
    private LocalDate getLocalDate(LocalDateTime active) {
        LocalDate date = active.toLocalDate();
        return date;
    }
    
    public String getBadgeID() {
        return badgeid;
    }

    public String getFname() {
        return firstname;
    }

    public String getMname() {
        return middlename;
    }

    public String getLname() {
        return lastname;
    }

    public int getEmpType() {
        return employeetypeid;
    }

    public int getDepType() {
        return departmentid;
    }

    public String getShiftid() {
        return shiftid;
    }

    public LocalDateTime getActive() {
        return active;
    }

    public LocalDateTime getInactive() {
        return inactive;
    }
            
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();       
        
        s.append('#').append(badgeid).append(" ");
        s.append('(').append(lastname).append(',');
        s.append(' ').append(firstname).append(' ');
        s.append("").append(middlename).append(')');
        s.append(':').append(" ").append("employeetypeid").append(':');
        s.append(' ').append(employeetypeid).append(",");
        s.append(' ').append("departmentid").append(':');
        s.append(' ').append(departmentid).append(",");
        s.append(' ').append("shiftid").append(':');
        s.append(' ').append(shiftid).append(",");
        if(inactive == null){
            s.append(' ').append("active").append(':');
            s.append(' ').append(active.toLocalDate()).append(",");
            s.append(' ').append("inactive").append(':');
            s.append(' ').append(inactiveString).append("");
        }
        else{
            s.append(' ').append("active").append(':');
            s.append(' ').append(activeString).append(",");
            s.append(' ').append("inactive").append(':');
            s.append(' ').append(inactive.toLocalDate()).append("");    
        }
        
        return s.toString();
    }
    
    
}

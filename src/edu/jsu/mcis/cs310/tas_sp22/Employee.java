package edu.jsu.mcis.cs310.tas_sp22;
import java.time.LocalDateTime;
import java.util.*;
        
public class Employee {
    
    private String badgeid;
    private String firstname;
    private String middlename;
    private String lastname;
    private int employeetypeid, departmentid, id;
    private String shiftid;
    private LocalDateTime active, inactive;
    
    public Employee(LinkedHashMap<String, String> params)
    {
        
        this.badgeid = params.get("badgeID");
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.employeetypeid = Integer.parseInt(params.get("employeetypeid"));
        this.departmentid = Integer.parseInt(params.get("departmentid"));
        this.id = Integer.parseInt(params.get("id"));
        this.shiftid = shiftid;
        this.active = LocalDateTime.parse(params.get("active"));
        this.inactive = LocalDateTime.parse(params.get("inactive"));
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

    public String getEmpType() {
        return employeetypeid;
    }

    public String getDepType() {
        return departmentid;
    }

    public String getShiftid() {
        return shiftid;
    }

    public String getActive() {
        return active;
    }

    public String getInactive() {
        return inactive;
    }
            
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();       
        
        s.append('#').append(badgeid).append(" ");
        s.append('(').append(lastname).append(',');
        s.append('(').append(firstname).append(' ');
        s.append('(').append(middlename).append(')');
        s.append(':').append(employeetypeid).append(':');
        s.append(',')
        
        return s.toString();
    }
    
    
}

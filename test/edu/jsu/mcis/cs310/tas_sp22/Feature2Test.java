package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.*;
import static org.junit.Assert.*;
        
public class Feature2Test {
   
    private TASDatabase db;
    
    @Before
    public void setup() {
    
    db = new TASDatabase();
    
    }
    
    @Test
    public void testDepartment(){
        
       Department d10 = db.getDepartment(10);
       Department d9 = db.getDepartment(9);
       Department d8 = db.getDepartment(8);
       Department d7 = db.getDepartment(7);
       Department d6 = db.getDepartment(6);
       Department d5 = db.getDepartment(5);
       Department d4 = db.getDepartment(4);
       Department d3 = db.getDepartment(3);
       Department d2 = db.getDepartment(2);
       Department d1 = db.getDepartment(1);
       
       assertEquals("id: 4; de")
       assertEquals("id: 3; description: (Warehouse); terminalid: 103", d3.toString());
       assertEquals("id: 2; description: (Cleaning); terminalid: 107 ", d2.toString());
       assertEquals("id: 1; decription: (Assembly); terminalid: 103", d1.toString());
    }
    
    @Test
    public void testInsertCheckNewPunch1(){
       
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       
       Punch p1 = new Punch (105, db.getBadge("398B1563"), 1);
       
       LocalDateTime ots, rts;
       
       // Gets punch properties
       String badgeid = p1.getBadgeID();
       ots = p1.getOriginalTimestamp();
       int terminalid = p1.getTerminalID();
       PunchType punchtype = p1.getEventType();
       
       // Insert punch into database.
       int punchid = db.insertPunch(p1);
       
       // Retrieve New Punch         
        Punch p2 = db.getPunch(punchid);
		
        // Compare Punches 
        assertEquals(badgeid, p2.getBadgeID());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalID());
        assertEquals(punchtype, p2.getEventType());
        assertEquals(ots.format(dtf), rts.format(dtf));               
       
   }
    
    @Test
    public void TestInsertCheckNewPunch2(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Punch p1 = new Punch (104, db.getBadge("67637925"), 1);
        
        LocalDateTime ots, rts; 
        
        String badgeid = p1.getBadgeID();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalID();
        PunchType punchtype = p1.getEventType();
        
        int punchid = db.insertPunch(p1);
        
        Punch p2 = db.getPunch(punchid);
        
        assertEquals(badgeid, p2.getBadgeID());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalID());
        assertEquals(punchtype, p2.getEventType());
        assertEquals(ots.format(dtf), rts.format(dtf));
        
    }
    
    @Test
    public void TestInsertCheckNewPunch3(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Punch p1 = new Punch(105, db.getBadge("D4F37E6F"), 1);
        
        LocalDateTime ots, rts;
        
        String badgeid = p1.getBadgeID();
        ots = p1.getOriginalTimestamp();
        int terminalid = p1.getTerminalID();
        PunchType punchtype = p1.getEventType();
        
        int punchid = db.insertPunch(p1);
        
        Punch p2 = db.getPunch(punchid);
        
        assertEquals(badgeid, p2.getBadgeID());
        
        rts = p2.getOriginalTimestamp();
        
        assertEquals(terminalid, p2.getTerminalID());
        assertEquals(punchtype, p2.getEventType());
        assertEquals(ots.format(dtf), rts.format(dtf));
        
    }
}

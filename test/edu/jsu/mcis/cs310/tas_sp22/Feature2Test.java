package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.*;
import static org.junit.Assert.*;
        
public class Feature2Test {
   
    private TASDatabase db;
    
    @Before
    public void setup() {
    
    db = new TASDatabase();
    
    }
    
    @Test
    public void testInsertCheckNewPunch1()
   {
       
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       
       Punch p1 = new Punch (105, db.getBadge("398B1563"),)
       
   }
    
}

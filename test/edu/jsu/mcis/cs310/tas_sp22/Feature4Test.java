package edu.jsu.mcis.cs310.tas_sp22;

import org.junit.*;
import static org.junit.Assert.*;


public class Feature4Test {

    private TASDatabase db;
    
    @Before
    public void setup(){
        db = new TASDatabase();
    }
    
    @Test
    public void testAdjustPunchesShift1WeekdayClockOut(){
        
        Shift s1 = db.getShift(1);
        
        Punch p1 = db.getPunch(3489);
        Punch p2 = db.getPunch(2849);
        Punch p3 = db.getPunch(3095);
        
        p1.adjust(s1);
        p2.adjust(s1);
        p3.adjust(s1);
        
        
        assertEquals("#B5A117E9 CLOCK OUT: WED 09/05/2018 17:33:18", p1.printOriginal());
        assertEquals("#B5A117E9 CLOCK OUT: WED 09/05/2018 17:30:00 (Interval Round)", p1.printAdjusted());
        
        assertEquals("#99F0C0FA CLOCK OUT: TUE 08/28/2018 12:02:15", p2.printOriginal());
        assertEquals("#99F0C0FA CLOCK OUT: TUE 08/28/2018 12:00:00 (Lunch Start)", p2.printAdjusted());
        
        assertEquals("#2986FF85 CLOCK OUT: THU 08/30/2018 14:36:35", p3.printOriginal());
        assertEquals("#2986FF85 CLOCK OUT: THU 08/30/2018 14:30:00 (Interval Round)", p3.printAdjusted());
        
    }
    
    @Test
    
    public void testAdjustPunchesShift1WeekendClockOut(){
        Shift s1 = db.getShift(1);
        
        
        Punch p1 = db.getPunch(3869);
        Punch p2 = db.getPunch(3865);
        
        p1.adjust(s1);
        p2.adjust(s1);
        
        assertEquals("#4E6E296E CLOCK OUT: SAT 09/08/2018 12:07:09", p1.printOriginal());
        assertEquals("#4E6E296E CLOCK OUT: SAT 09/08/2018 12:15:09 (Interval Round)", p1.printAdjusted());
        
        
        assertEquals("#2A7F5D99 CLOCK OUT: SAT 09/08/2018 12:05:32", p2.printOriginal());
        assertEquals("#2A7F5D99 CLOCK OUT: SAT 09/08/2018 12:00:00 (Interval Round)", p2.printAdjusted());    
    }
    
        @Test
    
    public void testPunchesShiftWeekday(){
        Shift s1 = db.getShift(1);
        
        Punch p1 = db.getPunch(3790);
        Punch p2 = db.getPunch(3791);
        
        p1.adjust(s1);
        p2.adjust(s1);
        
        assertEquals("#ADD650A8 CLOCK IN: FRI 09/07/2018 07:00:00", p1.printOriginal());
        assertEquals("#ADD650A8 CLOCK IN: FRI 09/07/2018 07:00:00 (Shift Start)", p1.printAdjusted());
        
        assertEquals("#ADD650A8 CLOCK OUT: FRI 09/07/2018 15:30:00", p2.printOriginal());
        assertEquals("#ADD650A8 CLOCK OUT: FRI 09/07/2018 15:30:00 (Shift Stop)", p2.printAdjusted());
       
    }

}
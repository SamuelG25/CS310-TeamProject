//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.Month;


public class TAS {

    
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase();
        
        if (db.isConnected())System.out.println("Connected");
        Badge b1 = db.getBadge("4E6E296E");
        Punch p1 = db.getPunch(3953);
        Shift s2 = db.getShift(1);
        
        System.out.println(s2.getInterval());
        
        p1.adjust(s2);
        System.out.println(p1.printAdjusted());
        
        
    }
    
}

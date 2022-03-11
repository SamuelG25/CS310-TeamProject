//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.Month;


public class TAS {

    
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase();
        
        if (db.isConnected())System.out.println("Connected");
        Badge b1 = db.getBadge("4E6E296E");
        Punch p1 = db.getPunch(3433);
        LocalDate ts = LocalDate.of(2018, Month.SEPTEMBER, 10);
        
        db.getDailyPunchList(b1, ts);
        
        
    }
    
}

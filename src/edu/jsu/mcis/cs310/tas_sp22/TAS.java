//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.Month;


public class TAS {

    
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase();
        
        if (db.isConnected())System.out.println("Connected");
        Badge b1 = db.getBadge("4E6E296E");
        Punch p1 = db.getPunch(1358);
        Shift s2 = db.getShift(1);
        Department d1 = db.getDepartment(1);
        
        System.out.println(d1.getId());
        
        
        
    }
    
}

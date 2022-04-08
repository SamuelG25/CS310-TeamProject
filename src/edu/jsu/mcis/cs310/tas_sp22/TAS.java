//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.Month;


public class TAS {

    
    public static void main(String[] args) {
        
        TASDatabase db = new TASDatabase();
        
        Punch p2 = db.getPunch(6898);
        
        System.err.println(p2.printOriginal());
        
        /*if (db.isConnected())System.out.println("Connected");
        Badge b1 = db.getBadge("4E6E296E");
        Punch p1 = db.getPunch(954);
        Shift s2 = db.getShift(1);
        Department d1 = db.getDepartment(1);
        db.insertPunch(p1);*/
        
        //System.out.println(db.insertPunch(p1));
        
        
        
    }
    
}

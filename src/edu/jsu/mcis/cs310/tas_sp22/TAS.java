//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

package edu.jsu.mcis.cs310.tas_sp22;


public class TAS {

    
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase();
        
        if (db.isConnected())System.out.println("Connected");
        Badge b1 = db.getBadge("12565C60");
        Punch p1 = db.getPunch(3433);
        
        
        
        
    }
    
}

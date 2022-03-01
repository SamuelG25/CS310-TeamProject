//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

package edu.jsu.mcis.cs310.tas_sp22;


public class TAS {

    
    public static void main(String[] args) {
        TASDatabase db = new TASDatabase();
        
        if (db.isConnected()){
            System.out.println("Connected");
        }
        db.GetEmployee(2);
    }
    
}

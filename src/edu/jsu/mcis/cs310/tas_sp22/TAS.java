package edu.jsu.mcis.cs310.tas_sp22;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONValue;

//Ruri Figueroa, Samuel Goldthreate, Bless Vincent

public class TAS {

    
    public static void main(String[] args) {
        
        TASDatabase db = new TASDatabase();
        
        Punch p2 = db.getPunch(4943);
        
        System.out.println(p2.getID());
        
        
        /*if (db.isConnected())System.out.println("Connected");
        Badge b1 = db.getBadge("4E6E296E");
        Punch p1 = db.getPunch(954);
        Shift s2 = db.getShift(1);
        Department d1 = db.getDepartment(1);
        db.insertPunch(p1);*/
        
        //System.out.println(db.insertPunch(p1));
        
        
        
    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
        
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>(); 
        
        for (int i = 0; i < dailypunchlist.size(); i++){
            
            Punch punch = dailypunchlist.get(i);
            HashMap<String, String> punchData = new HashMap<>();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
            
            punchData.put("id", String.valueOf(punch.getID())); 
            punchData.put("badgeid", String.valueOf(punch.getBadgeID()));
            punchData.put("terminalid", String.valueOf(punch.getTerminalID()));
            punchData.put("adjustmenttype", String.valueOf(punch.getAdjustmessage()));
            punchData.put("originaltimestamp", String.valueOf(dtf.format(punch.getOriginalTimestamp()).toUpperCase()));
            punchData.put("adjustedtimestamp", String.valueOf(dtf.format(punch.getAdjustedTimeStamp()).toUpperCase()));
            punchData.put("punchtype", String.valueOf(punch.getEventType().toString()));
            
            jsonData.add(punchData);
        }
        
        
        String json = JSONValue.toJSONString(jsonData);
        return json;
    }
}

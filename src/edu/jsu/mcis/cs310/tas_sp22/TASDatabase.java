
package edu.jsu.mcis.cs310.tas_sp22;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.*;

public class TASDatabase {
    
    private final String username;
    private final String password;
    private final String address;
    private final Connection connection;
    
    public TASDatabase() {
        this.address = "localhost";
        this.username = "tasuser";
        this.password = "WarRoomF";
        this.connection = openConnection (username,password, address);
        
    }
    
    private Connection openConnection(String u, String p, String a) {
        
        Connection c = null;
        
        if (a.equals("") || u.equals("") || p.equals(""))
            
            System.err.println("*** ERROR: MUST SPECIFY ADDRESS/USERNAME/PASSWORD BEFORE OPENING DATABASE CONNECTION ***");
        
        else {
        
            try {

                String url = "jdbc:mysql://" + a + "/tas_sp22_v1?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=EXCEPTION&serverTimezone=America/Chicago";
                

                c = DriverManager.getConnection(url, u, p);

            }
            catch (Exception e) { e.printStackTrace(); }
        
        }
        
        return c;
        
    }
    
    public boolean isConnected() {

        boolean result = false;
        
        try {
            
            if ( !(connection == null) )
                
                result = !(connection.isClosed());
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return result;
     
    }
    
    public Badge getBadge(String badgeid){
        
        ResultSet resultset;
        String id;
        String desc = null;
        
        try {
            String query = "SELECT * FROM badge WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, badgeid);
            
            boolean hasresults = pstmt.execute();
            
                if ( hasresults ){
                    resultset = pstmt.getResultSet();
                    resultset.next();
                    desc = resultset.getString("description");
            }
            
        } catch (SQLException ex) {}
        
    Badge b1 = new Badge(badgeid,desc);
    
    return b1;        
    }
    
}
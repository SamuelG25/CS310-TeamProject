
package edu.jsu.mcis.cs310.tas_sp22;

import java.util.*;
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
        
        
        String desc = null;
        
            try {
                String query = "SELECT * FROM badge WHERE id=?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, badgeid);
            
                boolean hasresults = pstmt.execute();
            
                    if ( hasresults ){
                        ResultSet resultset = pstmt.getResultSet();
                        resultset.next();
                        desc = resultset.getString("description");
                        resultset.close( );
                    } 
                
                pstmt.close( );
                connection.close( );
            } catch (SQLException ex) {};
    
        Badge b1 = new Badge(badgeid,desc);
        return b1;        
    }
    
    public Employee GetEmployee(int Id){
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM employee WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, Id);
            
            boolean hasresults = pstmt.execute();
            
                if ( hasresults ){
                    ResultSet resultset = pstmt.getResultSet();
                    resultset.next();
                    String id = resultset.getString("badgeid");
                    String Fname = resultset.getString("firstname");
                    String Mname = resultset.getString("middlename");
                    String Lname = resultset.getString("lastname");
                    String EmpType = resultset.getString("employeetypeid");
                    String DepType = resultset.getString("departmentid");
                    String Shiftid = resultset.getString("shiftid");
                    String active = resultset.getString("active");
                    String inactive = resultset.getString("inactive");
                    
                    results.put("badgeid",id);
                    results.put("firstname",Fname);
                    results.put("middlename",Mname);
                    results.put("lastname",Lname);
                    results.put("employeetypeid",EmpType);
                    results.put("departmentid",DepType);
                    results.put("shiftid",Shiftid);
                    results.put("active",active);
                    results.put("inactive",inactive);
                    
                    resultset.close();    
                    System.out.println(results);
                }
                
            pstmt.close( );
            connection.close( );
        } catch (SQLException ex) {};
        
        Employee Emp = new Employee();
        return Emp;
    }
    
    public Employee GetEmployee(String Id){
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM employee WHERE badgeid=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString (1, Id);
            
            boolean hasresults = pstmt.execute();
            
                if ( hasresults ){
                    ResultSet resultset = pstmt.getResultSet();
                    resultset.next();
                    String ID = resultset.getString("badgeid");
                    String Fname = resultset.getString("firstname");
                    String Mname = resultset.getString("middlename");
                    String Lname = resultset.getString("lastname");
                    String EmpType = resultset.getString("employeetypeid");
                    String DepType = resultset.getString("departmentid");
                    String Shiftid = resultset.getString("shiftid");
                    String active = resultset.getString("active");
                    String inactive = resultset.getString("inactive");
                    
                    results.put("badgeid",ID);
                    results.put("firstname",Fname);
                    results.put("middlename",Mname);
                    results.put("lastname",Lname);
                    results.put("employeetypeid",EmpType);
                    results.put("departmentid",DepType);
                    results.put("shiftid",Shiftid);
                    results.put("active",active);
                    results.put("inactive",inactive);
                    
                    resultset.close();
                    System.out.println(results);
                }
                
            pstmt.close( );
            connection.close( );
        } catch (SQLException ex) {};
        
        Employee Emp = new Employee();
        return Emp;
    }
}
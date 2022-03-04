
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
                
               
            } catch (SQLException ex) {};
    
        Badge b1 = new Badge(badgeid,desc);
        return b1;        
    }
    
    public Employee getEmployee(int Id){
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        
        
        try {
            String query = "SELECT * FROM employee WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, Id);
            
            boolean hasresults = pstmt.execute();
            
                if (hasresults){
                    ResultSet resultset = pstmt.getResultSet();
                    resultset.next();
                    
                    String ID = resultset.getString("id");
                    String badgeid = resultset.getString("badgeid");
                    String Fname = resultset.getString("firstname");
                    String Mname = resultset.getString("middlename");
                    String Lname = resultset.getString("lastname");
                    String Emptype = resultset.getString("employeetypeid");
                    String DepType = resultset.getString("departmentid");
                    String Shiftid = resultset.getString("shiftid");
                    String active = resultset.getString("active");
                    String inactive = resultset.getString("inactive");
                    
                    results.put("badgeid",badgeid);
                    results.put("firstname",Fname);
                    results.put("middlename",Mname);
                    results.put("lastname",Lname);
                    results.put("employeetypeid",Emptype);
                    results.put("departmentid",DepType);
                    results.put("shiftid",Shiftid);
                    results.put("active",active);
                    results.put("inactive",inactive);
                    results.put("id", ID);
                    
                    resultset.close();
                }    
        } catch (SQLException ex) {}
    
        
    Employee Emp = new Employee(results);
    return Emp;        
    }
    
    
    public Employee getEmployee(Badge b1){
        
        String Id = b1.getBadgeId();
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM employee WHERE badgeid=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString (1, Id);
            
            boolean hasresults = pstmt.execute();
            
                if ( hasresults ){
                    ResultSet resultset = pstmt.getResultSet();
                    resultset.next();
                    
                    String ID = resultset.getString("id");
                    String badgeID = resultset.getString("badgeid");
                    String Fname = resultset.getString("firstname");
                    String Mname = resultset.getString("middlename");
                    String Lname = resultset.getString("lastname");
                    String EmpType = resultset.getString("employeetypeid");
                    String DepType = resultset.getString("departmentid");
                    String Shiftid = resultset.getString("shiftid");
                    String active = resultset.getString("active");
                    String inactive = resultset.getString("inactive");
                    
                    results.put("badgeid",badgeID);
                    results.put("firstname",Fname);
                    results.put("middlename",Mname);
                    results.put("lastname",Lname);
                    results.put("employeetypeid",EmpType);
                    results.put("departmentid",DepType);
                    results.put("shiftid",Shiftid);
                    results.put("active",active);
                    results.put("inactive",inactive);
                    results.put("id", ID);
                    
                    resultset.close();
                }    
        } catch (SQLException ex) {}
        
        Employee Emp = new Employee(results);
        return Emp;
    }
    
    public Shift getShift(int id){
        
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM shift WHERE id=? ";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt (1, id);
            
            
             boolean hasresults = pstmt.execute();
            
                if ( hasresults ){
                    ResultSet resultset = pstmt.getResultSet();
                    resultset.next();
                    
                    String desc = resultset.getString("description");
                    String shiftstart = resultset.getString("shiftstart");
                    String shiftstop = resultset.getString("shiftstop");
                    String roundinterval = resultset.getString("roundinterval");
                    String graceperiod = resultset.getString("graceperiod");
                    String dockpenalty = resultset.getString("dockpenalty");
                    String lunchstart = resultset.getString("lunchstart");
                    String lunchstop = resultset.getString("lunchstop");
                    String lunchthreshold = resultset.getString("lunchthreshold");
                    
                    results.put("description",desc);
                    results.put("shiftstart",shiftstart);
                    results.put("shiftstop",shiftstop);
                    results.put("roundinterval",roundinterval);
                    results.put("graceperiod",graceperiod);
                    results.put("dockpenalty",dockpenalty);
                    results.put("lunchstart",lunchstart);
                    results.put("lunchstop",lunchstop);
                    results.put("lunchthreshold",lunchthreshold);
                    
                    resultset.close();
                }
        }catch (SQLException ex) {}
        
        
        
        Shift s1 = new Shift(results);
        return s1;
    }
    
    public Shift getShift(Badge b1){
        int shiftid = 0;
        String Id = b1.getBadgeId();
        
        try {
                String query = "SELECT * FROM employee WHERE badgeid=?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, Id);
            
                boolean hasresults = pstmt.execute();
            
                    if ( hasresults ){
                        ResultSet resultset = pstmt.getResultSet();
                        resultset.next();
                        shiftid = resultset.getInt("shiftid");
                        resultset.close( );
                    } 
                
               
            } catch (SQLException ex) {}
        return getShift(shiftid);
    }
    
    public Punch getPunch(int punch){
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
                String query = "SELECT * FROM event WHERE id=?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, punch);
                
                 boolean hasresults = pstmt.execute();
            
                    if ( hasresults ){
                        ResultSet resultset = pstmt.getResultSet();
                        resultset.next();
                        
                        String badge = resultset.getString("badgeid");
                        String terminal = resultset.getString("terminalid");
                        String time = resultset.getString("timestamp");
                        String event = resultset.getString("eventtypeid");
                        
                        results.put("badgeid",badge);
                        results.put("terminalid",terminal);
                        results.put("timestamp",time);
                        results.put("eventtypeid",event);
                        
                        resultset.close();
                    }
                
                
        } catch (SQLException ex) {}
        
        Punch p1 = new Punch(results);
        return p1;
    }
    
}
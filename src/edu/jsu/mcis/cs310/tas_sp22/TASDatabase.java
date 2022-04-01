
package edu.jsu.mcis.cs310.tas_sp22;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
                        resultset.close();
                    } 
                
               
            } catch (Exception e) { e.printStackTrace(); }
    
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
                    
                    results.put("badgeid",resultset.getString("badgeid"));
                    results.put("firstname",resultset.getString("firstname"));
                    results.put("middlename",resultset.getString("middlename"));
                    results.put("lastname",resultset.getString("lastname"));
                    results.put("employeetypeid",resultset.getString("employeetypeid"));
                    results.put("departmentid",resultset.getString("departmentid"));
                    results.put("shiftid",resultset.getString("shiftid"));
                    results.put("active",resultset.getString("active"));
                    results.put("inactive",resultset.getString("inactive"));
                    results.put("id", resultset.getString("id"));
                    
                    resultset.close();
                }
                
        } catch (Exception e) { e.printStackTrace(); }
    
        
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
                    
                    results.put("badgeid",resultset.getString("badgeid"));
                    results.put("firstname",resultset.getString("firstname"));
                    results.put("middlename",resultset.getString("middlename"));
                    results.put("lastname",resultset.getString("lastname"));
                    results.put("employeetypeid",resultset.getString("employeetypeid"));
                    results.put("departmentid",resultset.getString("departmentid"));
                    results.put("shiftid",resultset.getString("shiftid"));
                    results.put("active",resultset.getString("active"));
                    results.put("inactive",resultset.getString("inactive"));
                    results.put("id", resultset.getString("id"));
                    
                    resultset.close();
                }    
        } catch (Exception e) { e.printStackTrace(); }
        
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
                    
                        results.put("description",resultset.getString("description"));
                        results.put("shiftstart",resultset.getString("shiftstart"));
                        results.put("shiftstop",resultset.getString("shiftstop"));
                        results.put("roundinterval",resultset.getString("roundinterval"));
                        results.put("graceperiod",resultset.getString("graceperiod"));
                        results.put("dockpenalty",resultset.getString("dockpenalty"));
                        results.put("lunchstart",resultset.getString("lunchstart"));
                        results.put("lunchstop",resultset.getString("lunchstop"));
                        results.put("lunchthreshold",resultset.getString("lunchthreshold"));
                    
                        resultset.close();
                    }
            }catch (Exception e) { e.printStackTrace(); }
        
        
        
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
                
               
            } catch (Exception e) { e.printStackTrace(); }
        return getShift(shiftid);
    }
    
    public Punch  getPunch(int punch){
        
        LinkedHashMap <String, String > result = new LinkedHashMap<>();
        
        try {
                String query = "SELECT * FROM event WHERE id=?";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, punch);
                
                 boolean hasresults = pstmt.execute();
            
                    if ( hasresults ){
                        ResultSet resultset = pstmt.getResultSet();
                        resultset.next();
                        
                        result.put("badgeid", resultset.getString("badgeid"));
                        result.put("terminalid", resultset.getString("terminalid"));
                        result.put("timestamp", resultset.getString("timestamp"));
                        result.put("eventtypeid", resultset.getString("eventtypeid"));
                        
                        resultset.close();
                    }
                
                
        } catch (Exception e) { e.printStackTrace(); }
        
        
    Punch p1 = new Punch(result);
    return p1;
    }
    
    public Department getDepartment(int id){
        String description = null;
        String terminalid = null;
        
        try {
            
            String query = "SELECT * FROM department WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
            boolean hasresults = pstmt.execute();
            
            if ( hasresults ){
                ResultSet resultset = pstmt.getResultSet();
                resultset.next();
                
                description = resultset.getString("description");
                terminalid = resultset.getString("terminalid");
            }
            
            
        }catch (Exception e) { e.printStackTrace(); }
        
    Department d1 = new Department(description,terminalid,);
    return d1;    
    }
    
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, LocalDate date){
        
        ArrayList<Punch> punches = new ArrayList();
        
        try{
            String query = "SELECT *, DATE(`timestamp`) AS tsdate FROM event "
                    + "WHERE badgeid =? HAVING tsdate =? ORDER BY `timestamp`;";
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, badge.getBadgeId());
            pstmt.setDate(2,java.sql.Date.valueOf(date));
            
            boolean hasresults = pstmt.execute(); 
            
                if ( hasresults ){
                
                    ResultSet resultset = pstmt.getResultSet();
                    resultset.next();
                
                        do{
                    
                            LinkedHashMap<String,String> param = new LinkedHashMap();
                            param.put("badgeid", resultset.getString("badgeid"));
                            param.put("terminalid", resultset.getString("terminalid"));
                            param.put("timestamp", resultset.getString("timestamp"));
                            param.put("eventtypeid", resultset.getString("eventtypeid"));
                   
                            punches.add(new Punch(param));
                            
                        }while(resultset.next());
                }
                
            }catch (Exception ex) {ex.printStackTrace();}
        
    return punches;
    }
}
package edu.jsu.mcis.cs310.tas_sp22;

import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    
    public Employee getEmployee(int id){
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM employee WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
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
    
        
        return new Employee(results);
       
    }
    
    
    public Employee getEmployee(Badge b1){
        
        String Id = b1.getBadgeId();
        
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM employee WHERE badgeid=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, Id);
            
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
        
        return new Employee(results);

    }
    
    public Shift getShift(int id){
       
        LinkedHashMap <String, String > results = new LinkedHashMap<>();
        
        try {
            String query = "SELECT * FROM shift WHERE id=? ";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt (1, id);

            boolean hasresults = pstmt.execute();

                if ( hasresults ) {
                    
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
        
        return new Shift(results);
    
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
    
    public Punch getPunch(int punchid) {
        
        Punch punch = null;
        
        try {
            
            String query = "SELECT * FROM event WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, punchid);

            boolean hasresults = pstmt.execute();

            if ( hasresults ) {
                
                ResultSet resultset = pstmt.getResultSet();
                
                if (resultset.next()) {
        
                    LinkedHashMap<String, String> result = new LinkedHashMap<>();

                    result.put("id", resultset.getString("id"));
                    result.put("badgeid", resultset.getString("badgeid"));
                    result.put("terminalid", resultset.getString("terminalid"));
                    result.put("timestamp", resultset.getString("timestamp"));
                    result.put("eventtypeid", resultset.getString("eventtypeid"));
                    
                    punch = new Punch(result);

                    resultset.close();
                }
            }   
        } catch (Exception e) { e.printStackTrace(); }
        
        return punch;
        
    }
    
    public Department getDepartment(int id){
        
        String description = null;
        String terminalid = null;
        String keys = null;
        
        try {
            
            String query = "SELECT * FROM department WHERE id=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            
            boolean hasresults = pstmt.execute();
            
            if ( hasresults ){
                
                ResultSet resultset = pstmt.getResultSet();
                resultset.next();
                
                keys = resultset.getString("id");
                description = resultset.getString("description");
                terminalid = resultset.getString("terminalid");
                resultset.close();
            }
            
            pstmt.close();
        
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return new Department(description, terminalid, keys);
   
    }
    
    public int insertPunch(Punch p){
        
        int id = 0;
        
        Badge b1 = getBadge(p.getBadgeID());
        Employee e1 = getEmployee(b1);
        Department d1 = getDepartment(e1.getDepType());
        
        if (p.getTerminalID() == d1.getTerminalid() || p.getTerminalID() == 0){
            
            try {
                
                String query = "INSERT INTO event (badgeid, timestamp, terminalid, eventtypeid) VALUES (?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                
                pstmt.setString(1, p.getBadgeID());
                pstmt.setTimestamp(2, Timestamp.valueOf(p.getOriginalTimestamp()));
                pstmt.setInt(3, p.getTerminalID());
                pstmt.setInt(4, p.getEventType().ordinal());

                int rows = pstmt.executeUpdate();
                
                if ( rows == 1 ) {
                    
                    ResultSet resultset = pstmt.getGeneratedKeys();
                    if (resultset.next()) {id = resultset.getInt(1);}
                    
                }
            }catch (Exception e) { e.printStackTrace(); }
        }     
        
        return id;
        
    }
    
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, LocalDate date) {
        
        ArrayList<Punch> punches = new ArrayList();
        
        try{
            String query = "SELECT *, DATE(`timestamp`) AS tsdate FROM event "
                    + "WHERE badgeid = ? HAVING tsdate = ? ORDER BY `timestamp`";
            
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, badge.getBadgeId());
            pstmt.setDate(2,java.sql.Date.valueOf(date));
            
            boolean hasresults = pstmt.execute(); 
            
            if ( hasresults ) {

                ResultSet resultset = pstmt.getResultSet();
                resultset.next();

                do {

                    LinkedHashMap<String,String> param = new LinkedHashMap();
                    param.put("id", resultset.getString("id"));
                    param.put("badgeid", resultset.getString("badgeid"));
                    param.put("terminalid", resultset.getString("terminalid"));
                    param.put("timestamp", resultset.getString("timestamp"));
                    param.put("eventtypeid", resultset.getString("eventtypeid"));

                    punches.add(new Punch(param));

                } while(resultset.next());
                    
            }

        }
        catch (Exception ex) {ex.printStackTrace();}
        
        return punches;
        
    }
  
}
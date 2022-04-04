package edu.jsu.mcis.cs310.tas_sp22;

public class Department {
    String description;
    int Terminalid;
    int id;
    
    public Department(String des, String Termid, String id){
        
        this.description = des;
        this.Terminalid = Integer.parseInt(Termid);
        this.id = Integer.parseInt(id);
    }

    public String getDescription() {
        return description;
    }

    public int getTerminalid() {
        return Terminalid;
    }
    
    @Override
    public String toString() {
        
        //"#1 (Assembly): terminalid: 103"
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(id).append(" ");
        s.append("(").append(description).append(")");
        s.append(": ").append("terminalid: ").append(Terminalid);
        

        return s.toString();
    }

    public int getId() {
        return id;
    }
    
}

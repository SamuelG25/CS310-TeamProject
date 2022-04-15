package edu.jsu.mcis.cs310.tas_sp22;

public class Department {
    private String description;
    private int terminalid;
    private int id;
    
    public Department(String des, String termid, String id){
        
        this.description = des;
        this.terminalid = Integer.parseInt(termid);
        this.id = Integer.parseInt(id);
    }

    public String getDescription() {
        return description;
    }

    public int getTerminalid() {
        return terminalid;
    }
    
    @Override
    public String toString() {
        
        //"#1 (Assembly): terminalid: 103"
        
        StringBuilder s = new StringBuilder();
        
        s.append('#').append(id).append(" ");
        s.append("(").append(description).append(")");
        s.append(": ").append("terminalid: ").append(terminalid);
        

        return s.toString();
    }

    public int getId() {
        return id;
    }
    
}

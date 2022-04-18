zpackage edu.jsu.mcis.cs310.tas_sp22;

public class Badge {
    private String description, badgeId;
    
    public Badge(String badgeId, String description)
    {
        this.badgeId = badgeId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getBadgeId() {
        return badgeId;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();       
        
        s.append('#').append(badgeId).append(" ");
        s.append('(').append(description).append(')');
        
        return s.toString();
    }
    
}

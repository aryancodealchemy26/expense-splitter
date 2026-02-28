package models;
import java.util.List;

public class Expense {
    public String eid;    
    public String desc;   
    public double amt;    
    public User paidBy;   
    public List<User> pList; 

    public Expense(String eid, String desc, double amt, User paidBy, List<User> pList) {
        this.eid = eid;
        this.desc = desc;
        this.amt = amt;
        this.paidBy = paidBy;
        this.pList = pList;
    }
}
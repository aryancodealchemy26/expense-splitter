package models;
import java.util.List;

public class Expense {
    public String eid;    // expense id
    public String desc;   // description
    public double amt;    // amount
    public User paidBy;   // who paid
    public List<User> pList; // participants

    public Expense(String eid, String desc, double amt, User paidBy, List<User> pList) {
        this.eid = eid;
        this.desc = desc;
        this.amt = amt;
        this.paidBy = paidBy;
        this.pList = pList;
    }
}
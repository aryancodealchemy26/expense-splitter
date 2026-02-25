package service;
import models.*;
import java.util.*;

public class SplitService {
    List<Expense> hist = new ArrayList<>();
    Map<String, Double> bal = new HashMap<>();

    public void add(String eid, String desc, double amt, User paidBy, List<User> pList) {
        Expense e = new Expense(eid, desc, amt, paidBy, pList);
        hist.add(e);

        double s = amt / pList.size();

        bal.put(paidBy.uid, bal.getOrDefault(paidBy.uid, 0.0) + amt);

        for(User u : pList) {
            bal.put(u.uid, bal.getOrDefault(u.uid, 0.0) - s);
        }
        System.out.println(">> Expense Added Successfully!");
    }

    public void showExp() {
        System.out.println("\n--- Expense History ---");
        if(hist.isEmpty()) System.out.println("No expenses yet.");
        for(Expense e : hist) {
            System.out.println(e.desc + " | Rs." + e.amt + " | Paid by: " + e.paidBy.nm);
        }
    }

    public void showBal(Map<String, User> uMap) {
        System.out.println("\n--- Who Owes Whom ---");
        
        Map<String, Double> tempBal = new HashMap<>(bal);

        List<String> plus = new ArrayList<>();
        List<String> minus = new ArrayList<>();

        for(String id : tempBal.keySet()) {
            if(tempBal.get(id) > 0.01) plus.add(id);
            if(tempBal.get(id) < -0.01) minus.add(id);
        }

        if(plus.isEmpty() && minus.isEmpty()) {
            System.out.println("All settled up! No one owes anything.");
            return;
        }

        int i = 0; 
        int j = 0; 

        while (i < plus.size() && j < minus.size()) {
            String pId = plus.get(i);
            String mId = minus.get(j);

            double give = tempBal.get(pId);
            double take = -tempBal.get(mId);

            double deal = Math.min(give, take);
            
            System.out.println(uMap.get(mId).nm + " owes " + uMap.get(pId).nm + " : " + Math.round(deal * 100.0) / 100.0);

            tempBal.put(pId, give - deal);
            tempBal.put(mId, -(take - deal));

            if(tempBal.get(pId) <= 0.01) i++;
            if(Math.abs(tempBal.get(mId)) <= 0.01) j++;
        }
    }
}
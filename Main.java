import models.*;
import service.SplitService;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SplitService s = new SplitService();
        Map<String, User> uMap = new HashMap<>(); // Store users here
        
        System.out.println("=== Expense Splitter App ===");

        while(true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. View Expenses");
            System.out.println("4. Show Balances (Who Owes Whom)");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");
            
            int choice = sc.nextInt();
            
            switch(choice) {
                case 1:
                    System.out.print("Enter User ID (e.g., u1): ");
                    String uid = sc.next();
                    System.out.print("Enter Name: ");
                    String nm = sc.next();
                    User u = new User(uid, nm);
                    uMap.put(uid, u);
                    System.out.println("User " + nm + " added!");
                    break;

                case 2:
                    if(uMap.isEmpty()) {
                        System.out.println("Please add users first!");
                        break;
                    }
                    System.out.print("Enter Expense ID: ");
                    String eid = sc.next();
                    sc.nextLine(); // consume buffer
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amt = sc.nextDouble();
                    
                    System.out.print("Who Paid? (Enter User ID): ");
                    String pid = sc.next();
                    User paidBy = uMap.get(pid);
                    
                    if(paidBy == null) {
                        System.out.println("User not found!");
                        break;
                    }

                    System.out.print("Enter Participant IDs (comma separated, e.g., u1,u2,u3): ");
                    String pStr = sc.next();
                    String[] pIds = pStr.split(",");
                    List<User> pList = new ArrayList<>();
                    
                    for(String id : pIds) {
                        if(uMap.containsKey(id)) {
                            pList.add(uMap.get(id));
                        }
                    }

                    s.add(eid, desc, amt, paidBy, pList);
                    break;

                case 3:
                    s.showExp();
                    break;

                case 4:
                    s.showBal(uMap);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
} ///////
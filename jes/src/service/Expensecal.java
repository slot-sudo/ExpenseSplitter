package src.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.model.Users;

public class Expensecal {
   private HashMap<String, Users> usermap; 

    // Constructor to initialize the user map
   public Expensecal(){
        usermap=new HashMap<>();
   }

    // Method to add a user with their name and amount paid
   public void addUser(String name,double amountPaid){
        Users user=new Users(name, amountPaid);
        usermap.put(name,user);
   }

    // Method to get the total expense paid by all users
   public double getTotalExpense(){
        int total=0;
        for(Users user:usermap.values()){
            total+=user.getAmount();
        }
        return total;
   }

    // Method to calculate the share of each user and divide the total expense by the number of users
   // Returns the share amount
   public double Share(){
        if (usermap.size()==0) return 0;
        return getTotalExpense()/usermap.size();
   }

    // Method to print the balance sheet of each user
   public void printBalance(){
        System.out.println("===Balance sheet===");
        double share=Share();
        for(Users user:usermap.values()){   
            double balance=user.getAmount()-share;
            if(balance>0){
                System.out.println(user.getName()+" should recieve "+balance);
            } else if(balance<0){
                System.out.println(user.getName()+" should pay "+ Math.abs(balance));
            } else{
                System.out.println(user.getName()+" is settled");
            }
        }
   }

    // Method to print detailed transactions showing who owes whom and how much
   // It calculates the balances and determines the debtors and creditors
   public void printTransactions(){
    System.out.println("//==Detailed Transactions==//");
        double share=Share();

        Map<String,Double> Balances=new HashMap<>();
        for(Users u:usermap.values()){
            double bal=u.getAmount()-share;
            Balances.put(u.getName(),bal);
        }

        List<String> debtors=new ArrayList<>();
        List<String> creditors=new ArrayList<>();

        for(String i:Balances.keySet()){
            double d=Balances.get(i);
            if (d<0) debtors.add(i);
            if (d>0) creditors.add(i);
        }

        //settlement loop
        int i=0,j=0;
        while(i<debtors.size() && j<creditors.size()){
            String debtor=debtors.get(i);
            String creditor=creditors.get(j);

            double owe=Math.abs(Balances.get(debtor));
            double recieve=Balances.get(creditor);

            if(Math.abs(owe-recieve)<0.0001){
                System.out.printf("%s owes %s %.2f\n", debtor, creditor, owe);
                i++;
                j++;
            } else if(owe<recieve){
                System.out.printf("%s owes %s %.2f\n", debtor, creditor, owe);
                Balances.put(creditor, recieve-owe);
                i++;
            }else{
                System.out.printf("%s owes %s %.2f\n", debtor, creditor, recieve);
                Balances.put(debtor, -(owe-recieve));
                j++;
            }
        }
   
    }
}

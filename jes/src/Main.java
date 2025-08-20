package src;

import java.util.Scanner;
import src.service.Expensecal;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Expensecal manag = new Expensecal();

        // takes input from user
        System.out.println("//--Expense Splitter--//");
        System.out.print("Enter the number of Persons: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter each person's name and amount paid (e.g., Nischal Reddy 1000):");
        int count = 0;
        while (count<n) {
            String inp = sc.nextLine().trim();
            if (inp.isEmpty()) continue;

            String[] p=inp.split(" ");
            if (p.length<2) {
                System.out.println("Invalid input. Must include name and amount.");
                continue;
            }

            String name = String.join(" ",java.util.Arrays.copyOfRange(p,0,p.length-1));
            double amount;
            try {
                amount = Double.parseDouble(p[p.length - 1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Try again.");
                continue;
            }
            manag.addUser(name,amount);
            count++;
        }

        // Display options for balance sheet or detailed transactions
        System.out.println("Choose an option:\n1. Balances\n2. Detailed Transactions");
        int sel=sc.nextInt();
        if (sel == 1) {
            manag.printBalance();
        } else if (sel == 2) {
            manag.printTransactions();
        } else {
            System.out.println("Invalid selection.");
        }
        sc.close();
    }
}
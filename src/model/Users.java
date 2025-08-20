package src.model;

public class Users {
    private String name;
    private double amountPaid;

    // Constructor to initialize user with name and amount paid
    public Users(String name, double amountPaid) {
        this.name = name;
        this.amountPaid = amountPaid;
    }

    // retruns the name of the user
    public String getName() {
        return name;
    }

    // returns the amount paid by the user
    public double getAmount() {
        return amountPaid;
    }

    // sets the a new amount paid by the user
    public void setAmount(double amountPaid) {
        this.amountPaid = amountPaid;
    }
}

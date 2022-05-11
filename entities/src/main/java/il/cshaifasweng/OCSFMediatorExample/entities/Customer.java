package il.cshaifasweng.OCSFMediatorExample.entities;

public class Customer extends User {
    private double budget;

    public Customer(int user_id, String first_name, String last_name, String user_email){
        super(user_id, first_name, last_name, user_email);
        budget = 0.0;
    }

    public void deposit(double amountToAdd){
        budget += amountToAdd;
    }

    public void pay(double amountToPay){
        budget -= amountToPay;
    }

    public double getBudget(){
        return budget;
    }
}

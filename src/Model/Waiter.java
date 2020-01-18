package Model;

import java.util.*;

public class Waiter extends Employee {
    private double orderRate = 0.10;
    private ArrayList<Order> ordersReceived = new ArrayList<>();

    public Waiter(int id, String name) {
        super(id, name);
    }

    public void createOrder(Order order){ ordersReceived.add(order);}

    public ArrayList<Order> getOrdersReceived(){return ordersReceived;}


    public double calculateExpense() {
        double salaryWaiter = 0.0;
        for(int i = 0; i < ordersReceived.size();i++){
            salaryWaiter += ordersReceived.get(i).calculateTotalPrice() * 0.10;
        }
        return salaryWaiter;
    }
}

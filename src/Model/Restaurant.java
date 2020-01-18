package Model;

import java.util.ArrayList;
import java.util.Random;



public class Restaurant {
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    public Restaurant(){
        initEmployees();
        initProducts();
    }
    private void initEmployees(){

        addCook("Monica", 100);
        addWaiter("Ross");
        addWaiter("Phobe");
        addWaiter("Rachel");
    }
    private void initProducts(){
        products.add(new MainDish("Pizza", 6, 2, 2));
        products.add(new MainDish("Burger", 5, 1.5, 2));

        products.add(new Beverage("Coke", 2, 0.5));
        products.add(new Beverage("Lemonade", 2, 0.3));

        products.add(new Dessert("Tiramusu", 4, 1, 1));
        products.add(new Dessert("Cake", 3, 0.5, 1));
        products.add(new Dessert("Ice Cream", 3, 0.5, 0.5));

        ArrayList<Product> HGproducts = new ArrayList<>();
        HGproducts.add(new MainDish("Pizza", 6, 2, 2));
        HGproducts.add(new Beverage("Coke", 2, 0.5));
        HGproducts.add(new Dessert("Tiramusu", 4, 1, 1));
        products.add(new MenuProduct("Hunger Games Menu", HGproducts));

        ArrayList<Product> Kidsproducts = new ArrayList<>();
        Kidsproducts.add(new MainDish("Burger", 5, 1.5, 2));
        Kidsproducts.add(new Beverage("Lemonade", 2, 0.3));
        Kidsproducts.add(new Dessert("Ice Cream", 3, 0.5, 0.5));
        products.add(new MenuProduct("Kids Menu", Kidsproducts));
    }

    public void listEmployees(){
        for(Employee employee: employees){
            System.out.println(employee);
        }
    }
    public void addCook(String cookName, double cookSalary){
        employees.add(new Cook(employees.size(),cookName,cookSalary));
    }
    public void addWaiter(String waiterName){
        employees.add(new Waiter(employees.size(),waiterName));
    }
    public Waiter assignWaiter(){
        Random rand = new Random();
        ArrayList<Waiter> waiters = new ArrayList<>();
        for(int i=0; i < employees.size();i++){
            if(employees.get(i) instanceof Waiter){
                waiters.add((Waiter) employees.get(i));
            }

        }
        int randomcount = rand.nextInt(waiters.size());
        return waiters.get(randomcount);
    }
        public double calculateExpenses(){
        double sumofexpenses = 0.0;
         for(Employee employee : employees){
                 sumofexpenses += employee.calculateExpense();}
            for (int i = 0; i < employees.size(); i++) {
                    if (employees.get(i) instanceof Waiter) {
                        for (int j = 0; j < ((Waiter) employees.get(i)).getOrdersReceived().size(); j++) {
                            for (int k = 0; k < ((Waiter) employees.get(i)).getOrdersReceived().get(j).getOrderedProducts().size(); k++) {
                                sumofexpenses += ((Waiter) employees.get(i)).getOrdersReceived().get(j).getOrderedProducts().get(k).calculateExpense();
                            }
                        }
                    }
                }
            return sumofexpenses;
         }


    public double calculateRevenue(){
        double sumofrevenue = 0.0;
        for (Employee employee: employees){
            if(employee instanceof Waiter){
                for(int i=0;i<((Waiter) employee).getOrdersReceived().size();i++){
                    sumofrevenue += ((Waiter) employee).getOrdersReceived().get(i).calculateTotalPrice();
                }
            }
        }
        return sumofrevenue;
    }

    public ArrayList<Product> getProducts(){
        return products;
     }
    public ArrayList<Employee> getEmployees(){
        return employees;
    }
}

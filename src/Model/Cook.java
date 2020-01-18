package Model;

public class Cook extends Employee {
    private double salary;
    private double taxRate = 0.18;
    public Cook(int id, String name, double cookSalary){
        super(id,name);
        this.salary = cookSalary;
    }
    public double getSalary(){
        return this.salary;
    }
    public double getTaxRate(){
        return this.taxRate;
    }

    @Override
    public double calculateExpense() {
        return salary + (salary * this.taxRate);
    }
}

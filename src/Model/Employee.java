package Model;

public abstract class Employee implements Expense {
    private int id;
    private String name;

    public Employee(int id,String name){
        this.id= id;
        this.name = name;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        int new_id = id + 1;
        return "Employee " + new_id + ": " + this.name;
    }

    public abstract double calculateExpense();
}

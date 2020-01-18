package Model;

public class Beverage extends Product {
    public Beverage(String name, double sellingPrice, double purchasePrice){
        super(name,sellingPrice,purchasePrice,0.0);
    }

    @Override
    public double calculateExpense() {

        return getPurchasePrice();
    }
}

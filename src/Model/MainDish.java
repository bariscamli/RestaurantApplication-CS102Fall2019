package Model;

public class MainDish extends Product {
    public MainDish(String name,double sellingPrice, double purchasePrice,double utilityCost){
        super(name,sellingPrice, purchasePrice, utilityCost);
    }
    @Override
    public double calculateExpense() {
        return getPurchasePrice() + getUtilityCost();
    }
}

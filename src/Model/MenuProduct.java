package Model;

import java.util.ArrayList;

public class MenuProduct extends Product{

    private ArrayList<Product> products = new ArrayList<>();
    public MenuProduct(String name, ArrayList<Product> products){
        super(name);
        this.products = products;
        calculateSellingPrice();
    }

    @Override
    public double calculateExpense() {
        double expense = 0.0;
        for(int i=0; i < products.size();i++){
            expense += products.get(i).calculateExpense();
        }
        return expense;
    }
    private double calculateSellingPrice(){
        double sellingprice = 0.0;
        for(int i=0;i < products.size();i++){
            if (products.get(i) instanceof Dessert)
                sellingprice += products.get(i).getSellingPrice() * 0.8;
            else if(products.get(i) instanceof Beverage)
                sellingprice += products.get(i).getSellingPrice() * 0.5;
            else if(products.get(i) instanceof MainDish)
                sellingprice += products.get(i).getSellingPrice() * 0.9;
        }
        setSellingPrice(sellingprice);
        return sellingprice;
    }
}

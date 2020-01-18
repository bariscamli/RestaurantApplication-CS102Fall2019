package Model;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> products = new ArrayList<Product>();

    public Order(){

    }
    public void addProduct(Product product){
        products.add(product);
    }
    public void listOrder(){
        for(int i = 0;i < products.size();i++){
            System.out.println("Ordered Products:" + products.get(i).getName() + "Price: " + products.get(i).getSellingPrice());
        }
    }

    public ArrayList<Product> getOrderedProducts() {
        return products;
    }
    public double calculateTotalPrice(){
        double totalprice = 0.0;
        for (Product product : products) totalprice += product.getSellingPrice();
        return totalprice;
    }

}

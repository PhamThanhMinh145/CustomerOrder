package model;

public class Order {
    private String productName;
    private int quatity;
    private double price;
    private double amount;

    public Order(String productName, int quatity, double price, double amount) {
        this.productName = productName;
        this.quatity = quatity;
        this.price = price;
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    public void showOrder(){
           String msg =  String.format("|%-25s|%-7d|%-12.2fVND|%-12.2fVND|",
                         productName,quatity, price, amount );
           System.out.println(msg);
    }
          
}

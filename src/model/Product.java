package model;

public class Product implements Comparable<Product>{
    private String productID;
    private String pName;
    private double price;
    private int quatity;
    private String categoryID;

    public Product(String productID, String pName, double price, int quatity, String categoryID) {
        this.productID = productID;
        this.pName = pName;
        this.price = price;
        this.quatity = quatity;
        this.categoryID = categoryID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void showProduct(){
        String msg;
        msg = String.format("|%-5s|%-25s|%-12.2fVND|%-7d|%-10s|",
                            productID,pName,price,quatity,categoryID);
        System.out.printf(msg);
    }

    @Override
    public int compareTo(Product o) {
        return this.productID.compareTo(o.productID);
    }

}

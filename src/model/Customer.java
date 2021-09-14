package model;

import java.util.ArrayList;

public class Customer {
    private String customerName;
    ArrayList<Order> listOrder;

    public Customer(String customerName, ArrayList<Order> listOrder) {
        this.customerName = customerName;
        this.listOrder = listOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(ArrayList<Order> listOrder) {
        this.listOrder = listOrder;
    }

    public void addOrder(Order order){
        listOrder.add(order);
    }
    
    
    
}

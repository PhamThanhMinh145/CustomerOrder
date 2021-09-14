/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import model.Category;
import model.Customer;
import model.Order;
import model.Product;
import myToy.CheckValidation;


public class ProductControl {

    ArrayList<Product> proList = new ArrayList<>();
    ArrayList<Category> cateList = new ArrayList<>();
    ArrayList<Customer> cusList = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    public void AddFromFileOfProduct() {
        try {
            FileReader fr = new FileReader("product.dat");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details = bf.readLine()) != null )
            {
                StringTokenizer stk = new StringTokenizer(details,";");
                String productID = stk.nextToken().trim().toUpperCase();
                String pName = stk.nextToken().trim().toUpperCase();
                double price = Double.parseDouble(stk.nextToken().trim());
                int quatity = Integer.parseInt(stk.nextToken().trim());
                String categoryID = stk.nextToken().toUpperCase().trim();
                Product p = new Product(productID, pName, price, quatity, categoryID);
                proList.add(p);
                
            }
            bf.close();
            fr.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FILE PRODUCT LỖI");
        }

    }
    
    public void AddFromFileCategory(){
         try {
            FileReader fr = new FileReader("category.dat");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while((details = bf.readLine()) != null )
            {
                StringTokenizer stk = new StringTokenizer(details,";");
                String cateID = stk.nextToken().toUpperCase();
                String cateName = stk.nextToken().toUpperCase();
                Category c = new Category(cateID, cateName);
                cateList.add(c);
                
                
            }
            bf.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("FILE CATEGORY LỖI");
        }
    }
    
     // SAVE TO FILE 
    public void saveToFileProduct(String file){
        if(proList.isEmpty()){
            System.out.println("The list author is empty");
            return;
        }
        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(Product p : proList){
                pw.println(p.getProductID()+ ";" + p.getpName()+ ";" 
                            + p.getPrice() + ";" + p.getQuatity() + ";" + p.getCategoryID());
                 
            }
            pw.close();
            fw.close();
            System.out.println("Save to file product successful");
                    
        } catch (Exception e) {
            System.out.println("Save to file product is unsuccessful");
        }
        
    }
    
    public void saveToFileCategory(String file){
        if(proList.isEmpty()){
            System.out.println("The list author is empty");
            return;
        }
        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(Category c : cateList){
                pw.println(c.getCategoryID() + ";" + c.getCateName());
                 
            }
            pw.close();
            fw.close();
            System.out.println("Save to file category successful");
                    
        } catch (Exception e) {
            System.out.println("Save to file category is unsuccessful");
        }
        
    }
    
    // HÀM CHECK DUPLICATION OF PRODUCT AND CATEGORY 
    
    public int checkProduct(String productID){
        
        if(proList.isEmpty())
            return -1;
        for(int i = 0; i < proList.size() ; i++)
            if(proList.get(i).getProductID().equalsIgnoreCase(productID))
                return i;
        return -1;
    }
    
    public int checkCategory(String cateID){
        if(cateList.isEmpty())
            return -1;
        for(int i = 0; i < cateList.size() ; i++)
            if(cateList.get(i).getCategoryID().equalsIgnoreCase(cateID))
                return i;
        return -1;
    }
    
    // HÀM SHOW DATA OF PRODUCT AND CATEGORY
     
    public void showProduct(){
        if(proList.isEmpty()){
            System.out.println("The list product is nothing");
            return;
        }
        System.out.println("------------List of detailed information PRODUCT-----------");
        String msg = String.format("|%-5s|%-25s|%-15s|%-7s|%-10s|%-15s|",
                "ID","Name","Price","Quatity","CategoryID", "Category name");
        System.out.println(msg);
        for(int i = 0; i < proList.size(); i++ ){
            proList.get(i).showProduct();
            for(int j = 0; j < cateList.size(); j++)
                if(proList.get(i).getCategoryID().contains(cateList.get(j).getCategoryID())){
                    System.out.printf("%-15s|", cateList.get(j).getCateName());
                    System.out.println();
                            
                }
                    
                    
        }
    }
    
    public void showCategory(){
        if(cateList.isEmpty()){
            System.out.println("The list category is nothing");
            return;
        }
         System.out.println("----------Information of Category----------");
        String msg = String.format("|%-6s|%-15s|","ID", "Name");
        System.out.println(msg);
        for(Category ca : cateList){
             ca.showCategory();
             System.out.println();
        }
        
    }
    
    // HÀM ADD OF PRODUCT AND CATEGORY
    
    public void addProduct(){
        String productID, pName, categoryID, notify;
        double price;
        int quatity, pos;
        do {         
            do{
                productID = CheckValidation.getString("Input product ID(Pxxx): ",
                            "Product ID is required. The format of ID is Bxxx with B is char and x is digits","^[P|p]\\d{3}$");
                pos = checkProduct(productID);
                if(pos >= 0)
                    System.out.println("The ID already existed");
                
            }while (pos != -1);
            
           pName = CheckValidation.getStringNoFormat("Input name: ", "Name is required");
           price = CheckValidation.getDoubleInAInterval("Input price: ", "Price is required ",0 , 100000000);
           quatity = CheckValidation.getIntInAnInterval("Input quatity", "Quatity is required",0, 5000);
           
           showCategory();
           do{
               categoryID = CheckValidation.getStringNoFormat("Input category ID: ", "ID is required");
               pos = checkCategory(categoryID);
               if(pos == -1)
                   System.out.println("Category ID must be include in list Category");
               
           }while (pos == -1);
           proList.add(new Product(productID, pName, price, quatity, categoryID));
            System.out.println("-----------Add Successfully------------");
            notify = CheckValidation.getString("Do you waanna continue to add (Y/N) ",
                    "Input is required." , "[Y-y|N-n]{1}");
            
           
        } while (notify.equalsIgnoreCase("Y"));
        
    }
    
    public void addCategory(){
        String cateID, cateName, notify;
        int pos;
        do{
            do{
                cateID = CheckValidation.getString("Input category ID(Bxxx): ",
                        "ID is required.The format of ID is Bxxx with B is char and x is digits",
                            "^[A-Za-z]{1}\\d{3}$");   
                pos = checkCategory(cateID);
                if(pos >= 0 )
                    System.out.println("The ID already existed");
                
            }while(pos != -1);
            cateName = CheckValidation.getStringNoFormat("Input name: ", "Name is required");
            cateList.add(new Category(cateID, cateName));
            System.out.println("-----------Add Successfully------------");
             notify = CheckValidation.getString("Do you waanna continue to add (Y/N)",
                    "Input is required." , "[Y-y|N-n]{1}"); 
            
            
        }while(notify.equalsIgnoreCase("Y"));
        
    }
    
    // 1. Add New
    
    public void AddNew(){
        
        Menu menu = new Menu("add new");
        menu.addOption("1. Add Category");
        menu.addOption("2. Add Product");
        menu.addOption("3. Exit");
        
        int choice;
        do {
            menu.showMenu();
            choice = menu.getSubChoice();
            switch(choice){
                case 1:
                    addCategory();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    System.out.println("Thank you!!!");
                    break;
            }
        }while(choice != 3);
    }
    
    // 2. Show list
    
    public void showList(){
        Menu menu = new Menu("show list");
        menu.addOption("1. Show Category");
        menu.addOption("2. Show Product");
        menu.addOption("3. Exit");
        int choice;
        do {
            menu.showMenu();
            choice = menu.getSubChoice();
            switch(choice){
                case 1:
                    showCategory();
                    break;
                case 2:
                    showProduct();
                    break;
                case 3:
                    System.out.println("Thank you!!!");
                    break;
            }
        }while(choice != 3);
    }
    
    
    // SEARCH CATEGORY BY NAME
    public void searchCategoryByName(){
        String cateName, notify;
        do{
            cateName = CheckValidation.getStringNoFormat("Input item: ", "Item is required");
            if(cateList.isEmpty())
                System.out.println("Not found");
            if(cateList != null)
            {
                for(int i = 0 ; i < cateList.size() ; i++){
                    if(cateList.get(i).getCateName().contains(cateName)){
                         cateList.get(i).showCategory();
                         System.out.println();
                    }         
                 }
            }
            
            notify = CheckValidation.getString("Do you waanna continue to Search (Y/N) ",
                    "Input is required." , "[Y-y|N-n]{1}");
            
                        
        }while(notify.equalsIgnoreCase("Y"));
    }
    
    // SEARCH  PRODUCT BY NAME
    public void searchProductByName(){
        String pName, notify;
        do{
            pName = CheckValidation.getStringNoFormat("Input name: ", "name is required");
            if(proList.isEmpty())
                System.out.println("Not found");
            if(proList != null)
            {
                for(int i = 0 ; i < proList.size() ; i++){
                    if(proList.get(i).getpName().contains(pName)){
                         proList.get(i).showProduct();
                         System.out.println();
                    }         
                 }
            }
            
            notify = CheckValidation.getString("Do you waanna continue to Search (Y/N) ",
                    "Input is required." , "[Y-y|N-n]{1}");
            
                        
        }while(notify.equalsIgnoreCase("Y"));
    }
        // SEARCH PRODUCT BY CATEGORY NAME
        
    public void searchProductByCateName(){
        String cateName, notify;
        int i = proList.size();
        do{
            cateName = CheckValidation.getStringNoFormat("Input item: ", "Item is required");
            if(proList.isEmpty() || cateList.isEmpty())
                System.out.println("Not found");
            for(Category c : cateList)
                if(c.getCateName().contains(cateName)){
                    for(Product p : proList)
                        if(c.getCategoryID().contains(p.getCategoryID())){
                            p.showProduct();
                            System.out.printf("%-5s|",c.getCateName());
                            
                            System.out.println();
                        }
                }
            
            notify = CheckValidation.getString("Do you waanna continue to Search (Y/N) ",
                    "Input is required." , "[Y-y|N-n]{1}");
            
                        
        }while(notify.equalsIgnoreCase("Y"));
    }
    
    
    // 3. SEARCH
    
    public void search(){
        Menu menu = new Menu("Search PRODUCT and CATEGORY");
        menu.addOption("1. Search catetory by name");
        menu.addOption("2. Search product by name");
        menu.addOption("3. Search product by category name");
        menu.addOption("4. Exit");
        int choice;
        do{
            menu.showMenu();
            choice = menu.getSubChoice();
            switch (choice) {
                case 1:
                    searchCategoryByName();
                    break;
                case 2:
                    searchProductByName();
                    break;
                case 3:
                    searchProductByCateName();
                    break;
                case 4:
                    System.out.println("Thank you!!!!");
                    break;
            }
            
        }while(choice != 4);
        
    }
    
    public Category checkToUpdate(String cateID){
        if(cateList.isEmpty())
            return null;
        for (int i = 0; i < cateList.size(); i++) {
            if(cateList.get(i).getCategoryID().equals(cateID))
                return cateList.get(i); 
        }
        return null;   
        
    }
    
    public Product checkToUpdateP(String pID){
        if(proList.isEmpty())
            return null;
        for(int i = 0; i < proList.size() ; i++)
            if(proList.get(i).getProductID().equals(pID))
                return proList.get(i);
        return null;
    }
    
    
    //　HÀM UPDATE CATEGORY
    public void editCategory(){
    String cateID, caName, notify;
    Category c;
    do{
        System.out.println("-----------UPDATE CATEGORY--------");
        
        cateID = CheckValidation.getStringNoFormat("Input id: ","ID is required");
         c= checkToUpdate(cateID);
        if(c == null)
            System.out.println("Not found ID to update");
        else{
            System.out.println("Input new name: ");
            caName = sc.nextLine();
            if(caName.trim().isEmpty())
                c.getCateName();
            else
                c.setCateName(caName);
            System.out.println("Update category successful");
        }
        notify = CheckValidation.getString("Do you wanna keep updating category(Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
    }while(notify.equalsIgnoreCase("Y"));
}
    
    // HAM UPDATE PRODUCT
    public void editProduct(){
        String id, pName, categoryID, notify;
        double price = 0;
        int quatity = 0 , pos;
        boolean check;
        Product p;
        do{
            System.out.println("-----------Update Product------------");
            id = CheckValidation.getStringNoFormat("Input id to update: ", "Id is required");
            p = checkToUpdateP(id);
            if(p == null)
                System.out.println("Not found ID to update product");
            else{
                System.out.println("Input new name: ");
                pName = sc.nextLine();
                if(pName.trim().isEmpty())
                    p.getpName();
                else
                    p.setpName(pName);
                
                 //input price
                 do{
                     System.out.println("Input new price: ");
                     try {
                         String priceStr = sc.nextLine();
                         if(priceStr.trim().isEmpty()){
                             p.getPrice();
                             break;
                         }
                         price = Double.parseDouble(priceStr);
                         check = true;
                     } catch (Exception e) {
                         System.out.println("Price must be real number");
                         check = false;
                     }
                     if(price < 0){
                         System.out.println("Price must be greater than 0");
                         check = false;
                        }
                     if(check)
                         p.setPrice(price);
       
                 }while(!check);
                 
                 // input quatity
                 do{
                     System.out.println("Input new quatity: ");
                     try {
                         String quatityStr = sc.nextLine();
                         if(quatityStr.trim().isEmpty()){
                             p.getQuatity();
                             break;
                         }
                         quatity = Integer.parseInt(quatityStr);
                         check = true;
                     } catch (Exception e) {
                         System.out.println("Quatity must be real number");
                         check = false;
                     }
                     if(quatity < 0){
                         System.out.println("Quatity must be greater than 0");
                         check = false;
                        }
                     if(check)
                         p.setQuatity(quatity);
       
                 }while(!check);
                 // input category
                 showCategory();
                 do{
                     System.out.println("Input a new category ID: ");
                     categoryID = sc.nextLine();
                     if(categoryID.trim().isEmpty()){
                         p.getCategoryID();
                         break;
                     }
                      pos = checkCategory(categoryID);
                      if(pos == -1)
                          System.out.println("Category ID must be included in list category");
                      else {
                          p.setCategoryID(categoryID);
                          break;
                      }
                 
                 }while(pos == -1);
                 System.out.println("Update Product is successful");
            }
            
            notify = CheckValidation.getString("Do you wanna keep updating product(Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
            
        }while(notify.equalsIgnoreCase("Y"));
    }
    
    
    public Product checkCategoryIDInProduct(String cateID){
        if(proList.isEmpty())
            return null;
        for(int i = 0; i < proList.size() ; i++)
            if(proList.get(i).getCategoryID().equals(cateID))
                return proList.get(i);
        return null;
       
        
    }
    
    // HÀM DELETE PRODUCT VS CATEGORY
    public void removeCategory(){
        String cateID, confirm, notify;
        Category c;
        Product p;
        do{
            System.out.println("------------Delete Category------------");
            cateID = CheckValidation.getStringNoFormat("Input ID to delete: ", "ID is required");
            c = checkToUpdate(cateID);
            p = checkCategoryIDInProduct(cateID);
            
            if( c == null )
                System.out.println("Couldn't find ID");
            else{
                   try {
                        if( p.getCategoryID().equals(c.getCategoryID()))
                            System.out.println("Don't delete this ID because there is this ID in Product ");

                    } catch (Exception e) {
                            System.out.println("----------Information of Category----------");
                            String msg = String.format("|%-6s|%-15s|","ID", "Name");
                            System.out.println(msg);
                            c.showCategory();
                            System.out.println();
                            confirm = CheckValidation.getString("Do you wanna remove this category (Y/N)", "Input is required", "[Y-y|N-n]{1}");
                            if(confirm.equalsIgnoreCase("Y")){
                                cateList.remove(c);
                                System.out.println("Delete successfull!!!");
                            }
                }
                        
  
            }
            notify = CheckValidation.getString("Do you wanna continue removing (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");
                
            
        }while(notify.equalsIgnoreCase("Y"));
        
    }
    
    public void removeProduct(){
        String id, confirm, notify;
        Product p;
        do{
            System.out.println("------------Delete Product------------");
            id = CheckValidation.getStringNoFormat("Input ID to delete: ", "ID is required");
            p = checkToUpdateP(id);
            
            if( p == null )
                System.out.println("Couldn't find ID");
            else{
                   
                  System.out.println("------------Detailed information PRODUCT-----------");
                  String msg = String.format("|%-5s|%-25s|%-15s|%-7s|%-10s|",
                                    "ID","Name","Price","Quatity","CategoryID");
                  System.out.println(msg);
                  p.showProduct();
                  System.out.println();
                  confirm = CheckValidation.getString("Do you wanna remove this product (Y/N)", "Input is required", "[Y-y|N-n]{1}");
                  if(confirm.equalsIgnoreCase("Y")){
                     proList.remove(p);
                     System.out.println("Delete product item is  successfull!!!");
                    }
                }
            notify = CheckValidation.getString("Do you wanna continue removing (Y/N)",
                    "Input is required", "[Y-y|N-n]{1}");

        }while(notify.equalsIgnoreCase("Y"));
    }
    
    
    // 4. UPDATE CATEGORY
    public void updateCategory(){
         Menu menu = new Menu("Update and Detele CATEGORY");
        menu.addOption("1. Edit category");
        menu.addOption("2. Delete category");
        menu.addOption("3. Exit");
        int choice;
        do{
            menu.showMenu();
            choice = menu.getSubChoice();
            switch (choice) {
                case 1:
                    editCategory();
                    break;
                case 2:
                    removeCategory();
                    break;
                case 3:
                    System.out.println("Thank you!!!!");
                    break;
            }
            
        }while(choice != 3);
        
    }
    
    //5. UPDATE PRODUCT
    public void updateProduct(){
        Menu menu = new Menu("Update and Detele PRODUCT");
        menu.addOption("1. Edit product");
        menu.addOption("2. Delete product");
        menu.addOption("3. Exit");
        int choice;
        do{
            menu.showMenu();
            choice = menu.getSubChoice();
            switch (choice) {
                case 1:
                    editProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    System.out.println("Thank you!!!!");
                    break;
            }
            
        }while(choice != 3);
    }
    
    // HÀM CHO CUSTOMER
    
    public void displayProductForCustomer(){
        int i = 0;
       System.out.println("------------List of detailed information PRODUCT-----------");
        String msg ;
                msg = String.format("|%-5s|%-25s|%-15s|",
                "No.","Name's product","Price");
        System.out.println(msg);
        for(Product p : proList){
            i++;
            msg = String.format("|%-5s|%-25s|%-12.2fVND|",
                             i, p.getpName(), p.getPrice());
            System.out.println(msg);
   
        }
        
        
    } 
        public int checkCustomer1(String cusName){
       
        if(cusList.isEmpty())
            return -1;
        for(int i = 0 ; i < cusList.size() ; i++)
            if(cusList.get(i).getCustomerName().equalsIgnoreCase(cusName))
                return i;
        return -1;
    }
    
    public Customer checkCustomer(String cusName){
       
        if(cusList.isEmpty())
            return null;
        for(int i = 0 ; i < cusList.size() ; i++)
            if(cusList.get(i).getCustomerName().equalsIgnoreCase(cusName))
                return cusList.get(i);
        return null;
    }
    
    public void addOrderProduct(){
       
        String msg, customerName, nameProduct, notify;
        int i, quatity;
        double price, amount;
        Product p;
        do{
        customerName = CheckValidation.getStringNoFormat("Your name", "Name is required");
        displayProductForCustomer();
        i = CheckValidation.getInt("Select No of product: ", "There isn't No in List of PRODUCT");
        p = proList.get(i-1); 
        System.out.println("Your Name's product: " + p.getpName());
        nameProduct = p.getpName();
        quatity = CheckValidation.getIntInAnInterval("Input quatity: ","Quatity isnot ranger",0, proList.get(i-1).getQuatity());
        int a =  p.getQuatity()-  quatity;
        proList.get(i-1).setQuatity(a);
        price = p.getPrice();
        amount = p.getPrice()*quatity;
        
        msg = String.format("|%-25s|%-7s|%-15s|%-15s|",
                "Name's product","Quatity","Price", "Amount");
        System.out.println(msg);
        msg = String.format("|%-25s|%-7d|%-12.2fVND|%-12.2fVND|",
                         p.getpName(), quatity, price, amount );
        System.out.println(msg);
        
        boolean check = false;
        Customer customerResult = null;
        if(cusList.size() > 0){
            for(Customer c : cusList){
                if(c.getCustomerName().equals(customerName)){
                    check = true;
                    customerResult = c;
                }
            }
        }
        
        Order o = new Order(nameProduct, quatity, price, amount);
        
        if(check)
            customerResult.getListOrder().add(o);
        else{
            ArrayList<Order> listO = new ArrayList<>();
            listO.add(o);
            Customer customer = new Customer(customerName, listO);
            cusList.add(customer);
        }
      System.out.println("-----------Add Successfully------------");
      notify = CheckValidation.getString("Do you waanna continue to add (Y/N)",
                    "Input is required." , "[Y-y|N-n]{1}");   
       
        }while(notify.equalsIgnoreCase("Y"));
       
    }
    
    public void displayCustomer(){
      String  msg = String.format("|%-25s|%-7s|%-15s|%-15s|",
                "Name's product","Quatity","Price", "Amount");
    
        for(Customer c: cusList){
            System.out.println("Customer: " + c.getCustomerName());
            System.out.println(msg);
           
            for(Order o : c.getListOrder()){
                o.showOrder();
            }
        }
    }
    
    
    public void saveToFileCustomer(String file){
        if(proList.isEmpty()){
            System.out.println("The list author is empty");
            return;
        }
        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for(Customer c : cusList){
                pw.println(c.getCustomerName());
                for(Order o: c.getListOrder()){
                    pw.printf(o.getProductName() + ";" + o.getQuatity() +";"+ o.getPrice() + ";"+ o.getAmount());
                    pw.println();
                }
            }
            pw.close();
            fw.close();
            System.out.println("Save to file product successful");
                    
        } catch (Exception e) {
            System.out.println("Save to file product is unsuccessful");
        }
        
    }
    
    
}


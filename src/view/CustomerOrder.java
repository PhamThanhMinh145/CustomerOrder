/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Menu;
import control.ProductControl;

/**
 *
 * @author thanh
 */
public class CustomerOrder {

    public static void main(String[] args) {
        Menu menu = new Menu("Pham Thanh Minh - SE140310");
        menu.addOption("Select the option below:");
        menu.addOption("1. Add new ");
        menu.addOption("2. Show list");
        menu.addOption("3. Search ");
        menu.addOption("4. Update category");
        menu.addOption("5. Update product");
        menu.addOption("6. Order product");
        menu.addOption("7. Show order list of customer");
        menu.addOption("8. Read date from file!");
        menu.addOption("9. Exit");

        //CustomerController
        ProductControl pc = new ProductControl();
         
        pc.AddFromFileCategory();
        pc.AddFromFileOfProduct();
       
        int choice;
        boolean change = false;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    pc.AddNew();
                    change = true;
                    break;
                case 2:
                    pc.showList();
                    break;
                case 3:
                    pc.search();
                    break;
                case 4:
                    pc.updateCategory();
                    change = true;
                    break;
                case 5:
                    pc.updateProduct();
                    change = true;
                    break;
                case 6:
                    pc.addOrderProduct();
                    change = true;
                    break;
                case 7:
                    pc.displayCustomer();
                    pc.saveToFileCustomer("customerOrder.dat");
                    break;
                case 8:
                    break;
                case 9:
                    if (change == true) {
                        pc.saveToFileCategory("category.dat");
                        pc.saveToFileProduct("product.dat");
                        pc.saveToFileCustomer("customerOrder.dat");
                    }
                    change = false;
                    System.out.println("Thank you. See you again");
                    break;
            }

        } while (choice != 9);
    }

}

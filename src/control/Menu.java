/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.util.ArrayList;
import myToy.CheckValidation;

/**
 *
 * @author thanh
 */
public class Menu {
    private String title;
    private ArrayList<String> option = new ArrayList<>();

    public Menu(){
        
    }
    public Menu(String title) {
        this.title = title;
    }
    
    public void addOption(String newOption){
        option.add(newOption);
    }
    
    public void showMenu(){
        if(option.isEmpty()){
            System.out.println("There is no option in the menu");
            return;
        }
        System.out.println("\n----------------------------------");
        System.out.println("Welcome to " + title);
        for (String string : option) 
            System.out.println(string);
    }
    
    public int getChoice(){
        int maxOption = option.size() -1;
        String inputMsg = "Choose from 1 to " + maxOption;
        String errorMsg = "Try again. You have to choose option from 1 to " + maxOption;
        return CheckValidation.getIntInAnInterval(inputMsg, errorMsg,1, maxOption);
    }
    
    public int getSubChoice(){
        int maxOption = option.size();
        String inputMsg = "Choose from 1 to " + maxOption;
        String errorMsg = "Try again. You have to choose option from 1 to " + maxOption;
        return CheckValidation.getIntInAnInterval(inputMsg, errorMsg,1, maxOption);
    }
}

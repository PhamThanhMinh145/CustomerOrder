package myToy;


import java.util.Scanner;

public class CheckValidation {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String inputMsg, String errorMsg, String format){
        String input;
        boolean match;
        while(true){
            System.out.println(inputMsg);
            input = sc.nextLine().toUpperCase();
            match= input.matches(format);
            if(input.length() == 0 || input.isEmpty() || match == false)
                System.out.println(errorMsg);
            else
                return input;

        }
    }

    public static String getStringNoFormat(String inputMsg, String errorMsg ){
        String input;
        while(true){
            System.out.println(inputMsg);
            input = sc.nextLine();
            if(input.length() == 0 || input.isEmpty())
                System.out.println(errorMsg);
            else
                return input;
        }
        
        
  }
    
    public static int getIntInAnInterval(String inputMsg, String errorMsg, int lowerBound, int upperBound ){
        int n, tmp;
        if(lowerBound > upperBound)
        {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while(true){
            try{
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if(n < lowerBound || n > upperBound) throw new Exception();
                return n;
            }catch(Exception e)
            {
                System.out.println(errorMsg);
            }
        }
              
    }
    
    public static int getInt(String inputMsg, String errorMsg){
        int n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return  n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
            
        }
    }
    
    public static double getDouble(String inputMsg, String errorMsg){
        double n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
            
        }
    }
    
    public static double getDoubleInAInterval(String inputMsg, String errorMsg, double lowerBound, double upperBound){
        double n, tmp;
        if(lowerBound > upperBound){
            tmp = lowerBound;
            lowerBound= upperBound;
            upperBound = tmp;
        }
        while (true) {            
            try {
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if(n < lowerBound || n > upperBound) throw  new Exception();
                return n;
                        
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

}

package utils;

public class CustomerValidation {
    
    public static boolean checkCustomerID(String ID){
        if((ID.matches("[C]+\\d{3}"))){
            return true;
        }
        return false;
    }
    
    public static boolean checkCustomerName(String name){
        if(name.equals(null)){
            return false;
        }
        return true;
    }
    
    public static boolean checkCustomerAddress(String Address){
       if(Address.equals(null)){
           return false;
       }
       return true;
    }
    
    public static boolean checkCustomerPhone(String Phone){
        if(Phone.equals(null) && !(Phone.length() >= 10) && !(Phone.length() <= 12)){
           return false;
       }
       return true;
    }
}   
    
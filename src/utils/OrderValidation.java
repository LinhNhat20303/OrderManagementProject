package utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class OrderValidation {

    public static boolean checkOrderID(String Id) {
        if (Id.matches("[D]+\\d{3}")) {
            return true;
        }
        return false;
    }

    public static boolean checkProductID(String Id) {
        if (Id.matches("[D]+\\d{3}")) {
            return true;
        }
        return false;
    }

    public static boolean checkCustomerID(String Id) {
        if (Id.matches("[D]+\\d{3}")) {
            return true;
        }
        return false;
    }

    public static boolean checkOrderName(String Name) {
        if (Name.length() > 5 && Name.length() < 30) {
            return true;
        }

        return false;
    }

    public static boolean checkBookPrice(double price) {
        if (price > 0) {
            return true;
        }
        return false;
    }

    public static boolean checkBookQuantity(int quantity) {
        if (quantity > 0) {
            return true;
        }
        return false;
    }

    public static boolean checkBookStatus(String Status) {
        if (Status.equals("Available") || Status.equals("Not Available")) {
            return true;
        }
        return false;
    }

    public static boolean checkOrderQuantity(int quantity) {
        return quantity > 0;

    }

    public static Date toDate(String strDate) throws ParseException {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        df.setLenient(false);
        return df.parse(strDate);
    }

    public static String toString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        return df.format(date);
    }

    public static Date inputDate(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {

            }
        } while (date == null & !allowEmpty);
        return date;
    }

    public static boolean validateDate(Date date) {
        DateFormat sdf = new SimpleDateFormat();
        sdf.setLenient(false);
        try {
            String inputDate = sdf.format(date);
            Date parsedDate = sdf.parse(inputDate);
            return parsedDate.equals(date);
        } catch (Exception e) {
            return false;
        }
    }

}
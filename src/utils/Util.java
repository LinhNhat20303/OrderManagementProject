
package utils;

import java.util.Scanner;

public class Util {

    private static final String IGNORE_CASE_PATTERN = "(?i)";
    public static final String SEP = ", ";
    public static final String DATE_FORMAT = "MM/dd/yyyy";

    public static int inputInteger(String message, int minValue, int maxValue) {
        int val = minValue - 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                // Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    static Scanner sc = new Scanner(System.in);

    private Util() {
    }

    public static String inputString(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (!allowEmpty && str.isBlank());
        // } while (!allowEmpty && str.trim().isEmpty());
        return str.trim();
    }

    public static boolean validateStringPattern(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = Util.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }

    public static boolean validateString(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = Util.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }

    public static String inputStringWithSize(String message, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (str.length() > min && str.length() < max);
        // } while (!allowEmpty && str.trim().isEmpty());
        return str.trim();
    }

    public static int inputInt(String msg) {
        int data = 0;
        boolean flag;
        do {
            try {
                flag = false;
                System.out.print(msg);
                data = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(e);
                flag = true;
            }
        } while (flag);
        return data;
    }

    public static double inputDouble(String msg) {
        double ret = 0;
        boolean flag;
        do {
            try {
                System.out.print(msg);
                flag = false;
                ret = Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(e);
                flag = true;
            }
        } while (flag);
        return ret;
    }
}


package model;

import Main.OException;
import utils.CustomerValidation;
import utils.Util;

public class Customers implements Comparable<Customers> {

    private static final String ID_Format = "CXXX";
    private static final String ID_Pattern = "C\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 4;

    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID");
        sb.append(Util.SEP).append("NAME");
        sb.append(Util.SEP).append("QUANTITY");
        sb.append(Util.SEP).append("Address");
        sb.append(Util.SEP).append("Phone");
        return sb.toString();
    }

    public Customers() {
    }

    public Customers(String customerID, String customerName, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        // check
        if (CustomerValidation.checkCustomerID(customerID)) {
            this.customerID = customerID;
        } else {
            System.err.println("Error, customerID is not valid");
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) throws OException {
        // check
        if (CustomerValidation.checkCustomerName(customerName)) {
            this.customerName = customerName;
        } else {
            throw new OException("Error, customerName is not valid");
        }
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) throws OException {
        // check
        if (CustomerValidation.checkCustomerAddress(customerAddress)) {
            this.customerAddress = customerAddress;
        } else {
            throw new OException("Error, customerAddress is not valid");
        }
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) throws OException {
        // check
        if (CustomerValidation.checkCustomerPhone(customerPhone)) {
            this.customerPhone = customerPhone;
        } else {
            throw new OException("Error, customerPhone is not valid");
        }
    }

    public static String inputId() {
        String id = null;
        do {
            id = Util.inputString("Input id with patern (" + ID_Format + ")", false);
            if (!CustomerValidation.checkCustomerID(id)) {
                System.out.println("Error");
            } else {
                break;
            }
        } while (true);
        return id;
    }

    // input()
    public void input() {

        // customerID
        if (this.customerID == null) {
            setCustomerID(inputId().trim().toUpperCase());
        }

        // customerName
        while (true) {
            try {
                setCustomerName(Util.inputString("Enter the customer name", false).trim().toUpperCase());
                break;
            } catch (OException ex) {
                ex.getMessage();
            }
        }

        // customerAddress
        while (true) {
            try {
                setCustomerAddress(Util.inputString("Enter the customer address", false).trim().toUpperCase());
                break;
            } catch (OException ex) {
                ex.getMessage();
            }
        }
        // customerPhone
        while (true) {
            try {
                setCustomerPhone(Util.inputString("Enter the customer phone", false));
                break;
            } catch (OException ex) {
                ex.getMessage();
            }
        }
    }

    public void update() {

        System.out.println("Press enter to skip fields that don't need to be changed");

        // customerID
        String id = Util.inputString("Enter the customer ID", true);
        if (!id.isEmpty()) {
            if (CustomerValidation.checkCustomerID(id)) {
                setCustomerID(id);
            } else {
                setCustomerID(inputId());
            }
        }

        // customerName
        String name = Util.inputString("Enter the customer name", true);
        if (!name.isEmpty()) {
            while (true) {
                try {
                    setCustomerName(name);
                    break;
                } catch (OException ex) {
                    System.err.println("Error");
                    name = Util.inputString("Enter the customer name", true);
                }
            }
        }

        // customerAddress
        String address = Util.inputString("Enter the customer address", true);
        if (!address.isEmpty()) {
            while (true) {
                try {
                    setCustomerAddress(address);
                    break;
                } catch (OException ex) {
                    System.err.println("Error");
                    address = Util.inputString("Enter the customer address", true);
                }
            }
        }

        // customerPhone
        String phone = Util.inputString("Enter the customer phone", true);
        if (!phone.isEmpty()) {
            while (true) {
                try {
                    setCustomerPhone(phone);
                    break;
                } catch (OException ex) {
                    System.err.println("Error");
                    phone = Util.inputString("Enter the customer phone", true);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(customerID);
        sb.append(Util.SEP).append(customerName);
        sb.append(Util.SEP).append(customerAddress);
        sb.append(Util.SEP).append(customerPhone);
        return sb.toString();
    }

    public void parseCustomer(String entityString) throws Exception {
        // can check so luong attribute (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Customers.ENTITY_ATTRIBUTE_COUNT) {
                setCustomerID(attributes[0]);
                setCustomerName(attributes[1]);
                setCustomerAddress(attributes[2]);
                setCustomerPhone(attributes[3]);
            }
        }
    }

    @Override
    public int compareTo(Customers o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

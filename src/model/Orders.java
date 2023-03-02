
package model;

import Services.CustomersManagement;
import Services.ProductManagement;

import java.util.Date;
import java.util.Comparator;

import utils.CustomerValidation;
import utils.OrderValidation;
import utils.Util;

public class Orders {

    private static final String ID_Format = "DXXX";
    private static final String ID_Pattern = "D\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 6;

    private String orderID;
    private String customerID;
    private String productID;
    private int orderQuantity;
    private Date orderDate;
    private boolean status;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("orderID");
        sb.append(Util.SEP).append("customerID");
        sb.append(Util.SEP).append("productID");
        sb.append(Util.SEP).append("orderQuantity");
        sb.append(Util.SEP).append("orderDate");
        sb.append(Util.SEP).append("status");
        return sb.toString();
    }

    public Orders() {
    }

    public void input() {

        do {
            String customerID = Util.inputString("Input customer's id (" + ID_Format + ")", false);
            if (CustomersManagement.getInstance().getCustomerById(customerID) != null) {
                if (CustomerValidation.checkCustomerID(customerID)) {
                    setCustomerID(customerID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Customer not found.");
            }
        } while (true);

        // productID
        do {
            String productID = Util.inputString("Input product's id", false);
            if (ProductManagement.getInstance().getProductById(productID) != null) {
                if (OrderValidation.checkProductID(productID)) {
                    setProductID(productID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Product not found.");
            }
        } while (true);

        // orderQuantity
        do {
            int quantity = Util.inputInt("Input order quantity");
            if (OrderValidation.checkOrderQuantity(quantity)) {
                setOrderQuantity(quantity);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);

        // orderDate
        do {
            Date orderDate = OrderValidation.inputDate("Input order date", false);
            if (OrderValidation.validateDate(orderDate)) {
                setOrderDate(orderDate);
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);

        // status
        do {
            String status = Util.inputString("Input status (T/F)", false);
            if (OrderValidation.checkBookStatus(status)) {
                setStatus(status.trim().toUpperCase().startsWith("T"));
                break;
            } else {
                System.out.println("Error.");
            }
        } while (true);
    }

    public void update() {

        System.out.println("Press ENTER to skip fields that don't need to be changed");

        // orderID
        do {
            System.out.println("\nOld order ID: " + this.orderID);
            String oID = Util.inputString("Enter the new order ID", true);
            if (!oID.isEmpty()) {
                if (OrderValidation.checkOrderID(oID)) {
                    setOrderID(oID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        // customerID
        do {
            System.out.println("\nOld customer ID: " + this.customerID);
            String cID = Util.inputString("Enter the new customer ID", true);
            if (!cID.isEmpty()) {
                if (OrderValidation.checkCustomerID(cID)) {
                    setCustomerID(cID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderID);
        sb.append(Util.SEP).append(customerID);
        sb.append(Util.SEP).append(productID);
        sb.append(Util.SEP).append(orderQuantity);
        sb.append(Util.SEP).append(orderDate);
        sb.append(Util.SEP).append(status);
        return sb.toString();
    }

    public void parseOrders(String entityString) throws Exception {
        // can check so luong attribute (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Orders.ENTITY_ATTRIBUTE_COUNT) {
                System.out.println("sucxxx");
                setOrderID(attributes[0]);
                setCustomerID(attributes[1]);
                setProductID(attributes[2]);
                try {
                    setOrderQuantity(Integer.parseInt(attributes[3]));
                } catch (NumberFormatException ex) {
                    System.out.println(">>>>> Err: " + ex.getMessage());
                }
                setOrderDate(OrderValidation.toDate(attributes[4]));
                // setStatus(attributes[5]);
                System.out.println(attributes);
            }
        }
    }
}

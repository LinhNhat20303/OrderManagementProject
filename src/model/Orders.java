package model;

import Services.CustomersManagement;
import Services.ProductManagement;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import utils.OrderValidation;
import utils.Util;

public class Orders {

    private static final String ID_Format = "DXXX";
    private static final String ID_Pattern = "D\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 6;
    
    private List<OrderLine> productList = new ArrayList();
    private String orderID;
    private Customers customer;
//    private String productID;
//    private int orderQuantity;
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

    public List<OrderLine> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderLine> productList) {
        if (productList != null) {
            this.productList.addAll(productList);
        }
    }

    public Orders() {
    }

    public void input() {

        do {
            String customerID = Util.inputString("Input customer's id (" + ID_Format + ")", false);
            Customers cus =CustomersManagement.getInstance().getCustomerById(customerID);
            if (cus != null) {
               
                   setCustomer(cus);
                    break;
             
            } else {
                System.out.println("Customer not found.");
            }
        } while (true);

        // productID
        do {
            String productID = Util.inputString("Input product's id", false);
            if (ProductManagement.getInstance().getProductById(productID) != null) {
                if (OrderValidation.checkProductID(productID)) {
                    int quantity = Util.inputInteger("Input Product's Quantity", 0, Integer.MAX_VALUE);
                    productList.add(new OrderLine(productID, quantity));
//                    setProductID(productID);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Product not found.");
            }
        } while (true);

        // orderQuantity
//        do {
//            int quantity = Util.inputInt("Input order quantity");
//            if (OrderValidation.checkOrderQuantity(quantity)) {
//                setOrderQuantity(quantity);
//                break;
//            } else {
//                System.out.println("Error.");
//            }
//        } while (true);

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

        // customer
        do {
            System.out.println("\nOld customer ID: " + this.customer);
            String cID = Util.inputString("Enter the new customer ID", true);
            Customers cus = CustomersManagement.getInstance().getCustomerById(cID);
            if (cus != null) {
               
                    setCustomer(cus);
                    break;
           
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

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

//    public String getProductID() {
//        return productID;
//    }
//
//    public void setProductID(String productID) {
//        this.productID = productID;
//    }
//
//    public int getOrderQuantity() {
//        return orderQuantity;
//    }
//
//    public void setOrderQuantity(int orderQuantity) {
//        this.orderQuantity = orderQuantity;
//    }

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
        for (OrderLine orderLine : productList) {
            sb.append("\n");
            sb.append(orderID);
            sb.append(Util.SEP).append(customer);
            sb.append(Util.SEP).append(orderLine.getProductId());
            sb.append(Util.SEP).append(orderLine.getOrderQuantity());
            sb.append(Util.SEP).append(orderDate);
            sb.append(Util.SEP).append(status);
        }
        return sb.toString().substring(1);
    }

    public void parseOrders(String entityString) throws Exception {
        // can check so luong attribute (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Orders.ENTITY_ATTRIBUTE_COUNT) {
                setOrderID(attributes[0]);
                setCustomer(CustomersManagement.getInstance().getCustomerById(attributes[1]));
                // setProductID(attributes[2]);
                productList.add(new OrderLine(attributes[2], Integer.parseInt(attributes[3])));
                setOrderDate(OrderValidation.toDate(attributes[4]));
                setStatus(Boolean.parseBoolean(attributes[5]));
            }
        }
    }
}

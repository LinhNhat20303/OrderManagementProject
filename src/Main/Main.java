package Main;

import Services.FileDataService;
import Services.OrderManagement;
import Services.UserManagerment;
import Services.ProductManagement;
import Services.CustomersManagement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orders;
import model.Products;
import model.Customers;

public class Main {

    public enum object {
        CUSTOMER,
        ORDER,
        PRODUCTS
    }

    private static String productDataFilePath = "./src/data/product.txt";
    private static String orderDataFilePath = "./src/data/order.txt";
    private static String customerDataFilePath = "./src/data/customer.txt";

    public static OrderManagement orderManagement;
    public static ProductManagement productManagement;
    public static CustomersManagement customerManagement;

    private void prepare() {
        try {
            // customer
            this.customerManagement = customerManagement.getInstance();
            this.customerManagement.setDatabaseService(new FileDataService(Main.customerDataFilePath, Customers.getAttributesHeader()));
            this.customerManagement.loadData();
            // order
            this.orderManagement = orderManagement.getInstace();
            this.orderManagement.setDatabaseService(new FileDataService(Main.orderDataFilePath,Orders.getAttributesHeader()));
            this.orderManagement.loadData();
            // product
            this.productManagement = productManagement.getInstance();
            this.productManagement.setDatabaseService(new FileDataService(Main.productDataFilePath,Products.getAttributesHeader()));
            this.productManagement.loadData();

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void process() {

        Menu menu = new Menu();
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case CUSTOMER_CREATE_NEW_CUSTOMER:
                    mainFunc.add(object.CUSTOMER);
                    break;
                case CUSTOMER_UPDATE:
                    mainFunc.update(object.CUSTOMER);
                    break;
                case CUSTOMER_SEARCH:
                    mainFunc.searchCustomerByID();
                    break;
                case CUSTOMER_SAVE_TO_FILE:
                    mainFunc.SaveToFile(object.CUSTOMER);
                    break;
                case CUSTOMER_PRINT_ALL:
                    mainFunc.print(object.CUSTOMER);
                    break;
                case ORDER_CREATE_NEW_ORDER:
                    mainFunc.add(object.ORDER);
                    break;
                case ORDER_DELETE:
                    mainFunc.deleteOrder();
                    break;
                case ORDER_LIST_ALL_ORDER_ASC:
                    mainFunc.printAllOrderAsc();
                    break;
                case ORDER_LIST_ALL_PENDING_ORDER:
                    mainFunc.listAllPendingOrder();
                    break;
                case ORDER_SAVE_TO_FILE:
                    mainFunc.SaveToFile(object.ORDER);
                    break;
                case ORDER_UPDATE:
                    mainFunc.update(object.ORDER);
                    break;
                case PRODUCT_PRINT_ALL:
                    mainFunc.print(object.PRODUCTS);
                case EXIT:
                    System.out.println("Exited!");
                    break;

                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    private void start() {
        System.out.println("Order management");
        if (UserManagerment.getInstance().login()) {
            UserManagerment.getInstance().getCurrentUser().output();
            prepare();
            process();
        } else {
            System.out.println("Login failed!");
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }

}

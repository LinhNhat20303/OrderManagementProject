
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
    private enum object {
        CUSTOMER,
        ORDER
    }

    // đưa path sang hàm khác
    private static String productDataFilePath = "./src/data/product.txt";
    private static String orderDataFilePath = "./src/data/order.txt";
    private static String customerDataFilePath = "./src/data/customer.txt";

    private OrderManagement orderManagement;
    private ProductManagement productManagement;
    private CustomersManagement customerManagement;

    private void prepare() {
        try {
            // order
            this.orderManagement = orderManagement.getInstace();
            this.orderManagement
                    .setDatabaseService(new FileDataService(Main.orderDataFilePath, Orders.getAttributesHeader()));
            this.orderManagement.loadData();
            // product
            this.productManagement = productManagement.getInstance();
            this.productManagement
                    .setDatabaseService(new FileDataService(Main.productDataFilePath, Products.getAttributesHeader()));
            this.productManagement.loadData();
            // customer
            this.customerManagement = customerManagement.getInstance();
            this.customerManagement.setDatabaseService(
                    new FileDataService(Main.customerDataFilePath, Customers.getAttributesHeader()));
            this.customerManagement.loadData();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkRole() {
        return UserManagerment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN);
    }

    private void add(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Add new Customer");
                customerManagement.addNew();
            }
            if (obj == object.ORDER) {
                System.out.println("Add new order");
                orderManagement.addNew();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    private void deleteOrder() {
        System.out.println("Delete Order");
        orderManagement.deleteOrder();
    }

    private void update(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Update customer information");
                customerManagement.updateCustomer();
            }
            if (obj == object.ORDER) {
                System.out.println("Update order information");
                orderManagement.updateOrder();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    private void searchCustomerByID() {
        if (checkRole()) {
            System.out.println("Search information");
            customerManagement.search();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    private void SaveToFile(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Save Data");
                customerManagement.saveData();
            }
            if (obj == object.ORDER) {
                System.out.println("Save Data");
                orderManagement.saveData();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    private void PrintFromFile() {
        if (checkRole()) {
            System.out.println("Save to File");
            customerManagement.printAll();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    // product
    private void printAllProduct() {
        System.out.println("Print all product in product.txt");
        productManagement.PrintAll();
    }

    private void printAllOrderAsc() {
        System.out.println("Print all orders(ascending)");
        orderManagement.printAllAsc();

    }

    private void process() {
        Menu menu = new Menu();
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case CUSTOMER_CREATE_NEW_CUSTOMER:
                    add(object.CUSTOMER);
                    break;
                case CUSTOMER_UPDATE:
                    update(object.CUSTOMER);
                    break;
                case CUSTOMER_SEARCH:
                    searchCustomerByID();
                    break;
                case CUSTOMER_SAVE_TO_FILE:
                    SaveToFile(object.CUSTOMER);
                    break;
                case CUSTOMER_PRINT_ALL:
                    PrintFromFile();
                    break;
                case ORDER_CREATE_NEW_ORDER:
                    add(object.ORDER);
                    break;
                case ORDER_DELETE:
                    deleteOrder();
                    break;
                case ORDER_LIST_ALL_ORDER_ASC:
                    printAllOrderAsc();
                    break;
                // hoàn thành các function liên quan đến order
                case ORDER_LIST_ALL_PENDING_ORDER:

                    break;
                case ORDER_SAVE_TO_FILE:
                    SaveToFile(object.ORDER);
                    break;

                case ORDER_PRINT_ALL:
                    printAllProduct();
                    break;
                case ORDER_UPDATE:
                    update(object.ORDER);
                    break;

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

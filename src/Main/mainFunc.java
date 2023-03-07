package Main;

import Main.Main.object;
import Services.UserManagerment;

public class mainFunc {

    private synchronized static boolean checkRole() {
        return UserManagerment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN);
    }

    public synchronized static void add(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Add new Customer");
                Main.customerManagement.addNew();
            }
            if (obj == object.ORDER) {
                System.out.println("Add new order");
                Main.orderManagement.addNew();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    public synchronized static void deleteOrder() {
        System.out.println("Delete Order");
        Main.orderManagement.deleteOrder();
    }

    public synchronized static void print(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Print ");
                Main.customerManagement.printAll();
                ;
            }
            if (obj == object.PRODUCTS) {
                System.out.println("Print all product in product.txt");
                Main.productManagement.PrintAll();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    public synchronized static void update(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Update customer information");
                Main.customerManagement.updateCustomer();
            }
            if (obj == object.ORDER) {
                System.out.println("Update order information");
                Main.orderManagement.updateOrder();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }

    }

    public synchronized static void searchCustomerByID() {
        if (checkRole()) {
            System.out.println("Search information");
            Main.customerManagement.search();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    public synchronized static void SaveToFile(object obj) {
        if (checkRole()) {
            if (obj == object.CUSTOMER) {
                System.out.println("Save Data");
                Main.customerManagement.saveData();
            }
            if (obj == object.ORDER) {
                System.out.println("Save Data");
                Main.orderManagement.saveData();
            }
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    public synchronized static void printAllOrderAsc() {
        System.out.println("Print all orders(ascending)");
        Main.orderManagement.printAllAsc();
        ;

    }

    public synchronized static void listAllPendingOrder() {
        System.out.println("List all pending orders: ");
        Main.orderManagement.listAllPendingOrder();
    }
}
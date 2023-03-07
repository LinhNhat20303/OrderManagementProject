package Main;

import Main.Main.object;
import Services.UserManagerment;

public class MainFunc {

    private boolean checkRole() {
        return UserManagerment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN);
    }

    public void add(object obj) {
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

    public void deleteOrder() {
        System.out.println("Delete Order");
        Main.orderManagement.deleteOrder();
    }

    public void print(object obj) {
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

    public void update(object obj) {
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

    public void searchCustomerByID() {
        if (checkRole()) {
            System.out.println("Search information");
            Main.customerManagement.search();
        } else {
            System.out.println("Dont have permission need Admin role");
        }
    }

    public void SaveToFile(object obj) {
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

    public void printAllOrderAsc() {
        System.out.println("Print all orders(ascending)");
        Main.orderManagement.printAllAsc();
        ;

    }

    public void listAllPendingOrder() {
        System.out.println("List all pending orders: ");
        Main.orderManagement.listAllPendingOrder();
    }
}

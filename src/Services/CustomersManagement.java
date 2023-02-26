
package Services;

import Main.OException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customers;

import utils.Util;

public class CustomersManagement extends DataManagement<Customers> {

    private static CustomersManagement instance = new CustomersManagement();

    public static CustomersManagement getInstance() {
        return instance;
    }

    public Customers addNew() {
        String customerId = Customers.inputId();
        Customers customer = getCustomerById(customerId);
        if (customer == null) {
            customer = new Customers();
            customer.setCustomerID(customerId);
            customer.input();
            if (entityList.add(customer)) {
                insertData(customer);
            }
        } else {
            System.out.println("This customers [" + customerId + "] already exists.");
        }
        return customer;
    }

    public void printAll() {
        for (Customers customers : entityList) {
            System.out.println(customers);
        }
    }

    public void updateCustomer() {
        String customerId = Customers.inputId();
        Customers customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Not found");
        } else {
            customer.update();
            saveData();
        }
    }

    public Customers getCustomerById(String cusID) {
        if (cusID != null && !cusID.isBlank()) {
            for (Customers cus : entityList) {
                if (cusID.equalsIgnoreCase(cus.getCustomerID())) {
                    return cus;
                }
            }
        }
        return null;
    }

    public void search() {
        String FindID = Util.inputString("Enter the customerID you want to find", true);
        for (Customers customers : entityList) {
            if (customers.getCustomerID().equals(FindID)) {
                // format
                System.out.println(customers);
                return;
            }
        }
        System.out.println("Not Found");
    }

    public void printFromFle() {
        for (Customers customers : entityList) {
            System.out.println(customers);
        }
    }

    @Override
    protected Customers parseEntity(String stringEntity) {
        try {
            Customers obj = new Customers();
            obj.parseCustomer(stringEntity);
            return obj;

        } catch (Exception ex) {
            Logger.getLogger(CustomersManagement.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

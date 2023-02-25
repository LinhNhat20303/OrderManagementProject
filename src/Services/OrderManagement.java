/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Services;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Orders;
import utils.Util;

import static model.Orders.compNameAsc;

public class OrderManagement extends DataManagement<Orders> {

    private static OrderManagement instance = new OrderManagement();

    public static OrderManagement getInstace() {
        return instance;
    }

    public void printAllAsc() {
        for (Orders orders : entityList) {
            Collections.sort(entityList, compNameAsc);
            System.out.println(orders);
        }
    }

    public Orders addNew() {
        String orderId = Util.inputString("Input Id", false);
        Orders order = this.getOrderByID(orderId);
        if (order == null) {
            order = new Orders();
            order.setOrderID(orderId);
            order.input();
            if (entityList.add(order)) {
                insertData(order);

            } else {
                System.out.println("This order " + orderId + "already exists");
            }
        }
        return order;
    }

    public Orders getOrderByID(String orderID) {
        if (orderID != null && !orderID.isBlank()) {
            for (Orders order : entityList) {
                if (orderID.equalsIgnoreCase(order.getCustomerID())) {
                    return order;
                }
            }
        }
        return null;
    }

    public void updateOrder() {
        String orderId = Util.inputString(null, false);
        Orders customer = getOrderByID(orderId);
        if (customer == null) {
            System.out.println("Not found");
        } else {
            customer.update();
            saveData();
        }
    }

    @Override
    protected Orders parseEntity(String stringEntity) {
        try {
            Orders obj = new Orders();
            obj.parseOrders(stringEntity);
            return obj;
        } catch (Exception ex) {
            Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

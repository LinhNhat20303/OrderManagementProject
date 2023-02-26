/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Services;

import java.util.Collections;
import java.util.List;
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
        String orderId = Util.inputString("Input id", false);
        Orders order = getOrderByID(orderId);
        if (order == null) {
            System.out.println("Not found");
        } else {
            order.update();
            saveData();
        }
    }

    public void deleteOrder() {
        String id = Util.inputString("Input ID", false);
        if (id != null && !id.isBlank()) {
            for (Orders order : entityList) {
                if (id.equalsIgnoreCase(order.getOrderID())) {
                    entityList.remove(order);
                    System.out.println("Deleted.");
                    break;
                }
            }
        }
        System.out.println("Not found.");
    }

    public void printOutTable(List<Orders> list) {
        for (Orders order : list) {
            Collections.sort(list, compNameAsc);
            System.out.println(order);
        }
    }

    public void filterPendingOrder() {
        List<Orders> resultList = this.entityList
                .stream()
                .filter(ord -> (ord.getStatus()) == false)
                .toList();
        printOutTable(resultList);
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

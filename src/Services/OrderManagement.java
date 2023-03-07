package Services;

import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderLine;

import model.Orders;
import utils.OrderValidation;
import utils.Util;

public class OrderManagement extends DataManagement<Orders> {

    private static OrderManagement instance = new OrderManagement();

    public static OrderManagement getInstace() {
        return instance;
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
                if (orderID.equalsIgnoreCase(order.getOrderID())) {
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

    public void printAllAsc() {
        Collections.sort(entityList, new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
//                String name1 = CustomersManagement.getInstance().getCustomerById(o1.getCustomerID()).getCustomerName();
//                String name2 = CustomersManagement.getInstance().getCustomerById(o2.getCustomerID()).getCustomerName();
//                return name1.compareTo(name2);
                return o1.getCustomer().getCustomerName().compareTo(o2.getCustomer().getCustomerName());
            }
        });
        printOutTable(entityList);
    }

    private void printOutTable(List<Orders> list) {
        Formatter fmt = new Formatter();
        fmt.format("%9s %11s %11s %9s %13s %9s\n",
                "OrderID",
                "CustomerID",
                "ProductID",
                "Quantity",
                "OrderDate",
                "Status");
        for (Orders ord : list) {
            for (OrderLine line : ord.getProductList()) {
                fmt.format("%9s %11s %11s %9s %13s %9s\n",
                        ord.getOrderID(),
                        ord.getCustomer().getCustomerID(),
                        line.getProductId(),
                        line.getOrderQuantity(),
                        OrderValidation.toString(ord.getOrderDate()),
                        ord.getStatus());
            }
        }
        System.out.println(fmt);
    }

    public void listAllPendingOrder() {
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

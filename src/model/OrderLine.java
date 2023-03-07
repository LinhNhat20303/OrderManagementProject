package model;

public class OrderLine {
    private String product;
    private int orderQuantity;

    public String getProductId() {
        return product;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public OrderLine(String product, int orderQuantity) {
        this.product = product;
        this.orderQuantity = orderQuantity;
    }

}

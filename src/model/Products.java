
package model;

import utils.Util;

public class Products implements Comparable<Products> {

    public final String ID_Format = "PXXX";
    public final String ID_Pattern = "P\\{3}";
    private static int ENTITY_ATTRIBUTE_COUNT = 5;

    private String productID;
    private String productName;
    private String unit;
    private String origin;
    private double price;

    public static String getAttributesHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Products");
        sb.append(Util.SEP).append("productID");
        sb.append(Util.SEP).append("productName");
        sb.append(Util.SEP).append("unit");
        sb.append(Util.SEP).append("origin");
        sb.append(Util.SEP).append("price");
        return sb.toString();
    }

    public Products() {
    }

    public Products(String productID, String productName, String unit, String origin, double price) {
        this.productID = productID;
        this.productName = productName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(productID);
        sb.append(Util.SEP).append(productName);
        sb.append(Util.SEP).append(unit);
        sb.append(Util.SEP).append(origin);
        sb.append(Util.SEP).append(price);
        return sb.toString();
    }

    public void parseProduct(String entityString) throws Exception {
        // can check so luong attribute (id, name, price, quantity, publisherId, status)
        if (entityString != null) {
            String[] attributes = entityString.split(Util.SEP, -1);
            if (attributes.length >= Products.ENTITY_ATTRIBUTE_COUNT) {
                setProductID(attributes[0]);
                setProductName(attributes[1]);
                setUnit(attributes[2]);
                setOrigin(attributes[3]);
                try {
                    setPrice(Double.parseDouble(attributes[4]));
                } catch (NumberFormatException ex) {
                    System.out.println(">>>>> Err: " + ex.getMessage());
                }
            }
        }
    }

    @Override
    public int compareTo(Products o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from
                                                                       // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

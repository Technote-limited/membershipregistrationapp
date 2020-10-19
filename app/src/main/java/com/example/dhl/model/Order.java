package com.example.dhl.model;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("id")
    private int id;
    @SerializedName("order_number")
    private String order_number;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("order_date")
    private String order_date;
    @SerializedName("product_code")
    private String product_code;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
}

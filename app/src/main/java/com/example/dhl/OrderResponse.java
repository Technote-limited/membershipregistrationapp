package com.example.dhl;

import com.example.dhl.model.Order;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("orders")
    @Expose
    private List<Order> orders;

    /**
     * No args constructor for use in serialization
     *
     */
    public OrderResponse() {
    }

    /**
     *
     * @param orders
     * @param error
     */
    public OrderResponse(Boolean error, List<Order> orders) {
        super();
        this.error = error;
        this.orders = orders;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}

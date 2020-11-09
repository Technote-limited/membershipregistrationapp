package com.example.dhl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("order_number")
    @Expose
    private Integer orderNumber;
    @SerializedName("cards")
    @Expose
    private String cards;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Order() {
    }

    /**
     *
     * @param orderNumber
     * @param cards
     * @param quantity
     * @param orderDate
     * @param createdBy
     * @param status
     */
    public Order(Integer orderNumber, String cards, String quantity, String orderDate, String createdBy, Integer status) {
        super();
        this.orderNumber = orderNumber;
        this.cards= cards;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.createdBy = createdBy;
        this.status = status;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

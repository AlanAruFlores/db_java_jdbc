package org.ar.ecommerce.models;

import java.util.Date;

public class Order {
    private Integer idOrder;
    private Date orderDate;
    private Float total;

    private Client client;

    public Order() {
    }

    public Order(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Order(Date orderDate, Float total, Client client) {
        this.orderDate = orderDate;
        this.total = total;
        this.client = client;
    }

    public Order(Integer idOrder, Date orderDate, Float total, Client client) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.total = total;
        this.client = client;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", orderDate=" + orderDate +
                ", total=" + total +
                ", client=" + client +
                '}';
    }
}

package com.team.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(OrderProductpk.class)
public class OrderProduct {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;
    
    
    private int quantity;
}

class OrderProductpk implements Serializable {
    
    private int product;
    private int order;

    // getters/setters and most importantly equals() and hashCode()
}
package com.team.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ShoppingBasketProductpk.class)
public class ShoppingBasketProduct {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "shopping_basket_id", referencedColumnName = "shoppingBasketId")
    private ShoppingBasket shoppingBasket;
    
    
    private int quantity;
}

class ShoppingBasketProductpk implements Serializable {
    
    private int product;
    private int shoppingBasket;

    // getters/setters and most importantly equals() and hashCode()
}
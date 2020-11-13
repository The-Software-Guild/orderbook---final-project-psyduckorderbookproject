package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;

public class Order {
    
    public int orderID;
    public int quantity;
    public BigDecimal price;
    
    public Order(int orderID, int quantity, BigDecimal price) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
   }
   
   public int getOrderID() {
       return orderID;
   }
   
   public int getQuantity() {
       return quantity;
   }
   
   public BigDecimal getPrice() {
       return price;
   }
}

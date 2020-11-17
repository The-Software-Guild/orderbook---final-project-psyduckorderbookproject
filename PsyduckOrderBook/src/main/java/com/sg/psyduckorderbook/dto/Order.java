package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;

public class Order {
    
    public int orderID;
    public BigDecimal quantity;
    public BigDecimal price;
    
    public Order(int orderID, BigDecimal quantity, BigDecimal price) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.price = price;
   }
    
   public void setprice(BigDecimal price) {
       this.price = price;
   }
   
   public void setQuantity(BigDecimal quantity) {
       this.quantity = quantity;
   }
   
   public int getOrderID() {
       return orderID;
   }
   
   public BigDecimal getQuantity() {
       return quantity;
   }
   
   public BigDecimal getPrice() {
       return price;
   }
}

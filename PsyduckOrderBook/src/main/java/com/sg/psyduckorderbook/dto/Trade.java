package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Trade {
    
    int numberID;
    LocalTime time;
    BigDecimal price;
    BigDecimal quantity;
    
    public Trade(int numberID) {
       this.numberID = numberID;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public int getNumberID() {
        return numberID;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public BigDecimal getQuantity() {
        return quantity;
    }
}

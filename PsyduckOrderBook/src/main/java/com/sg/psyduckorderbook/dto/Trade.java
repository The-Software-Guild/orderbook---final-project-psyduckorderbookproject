package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {
    
    int numberID;
    LocalDateTime time;
    BigDecimal price;
    BigDecimal quantity;
    
    public Trade(int numberID) {
       this.numberID = numberID;
    }
    
    public void setTime(LocalDateTime time) {
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
    
    public LocalDateTime getTime() {
        return time;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public BigDecimal getQuantity() {
        return quantity;
    }
}

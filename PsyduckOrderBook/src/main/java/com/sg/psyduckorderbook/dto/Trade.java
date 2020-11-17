package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {
    
    int numberID;
    LocalDateTime time;
    BigDecimal price;
    BigDecimal quantity;
    
    public Trade(int numberID, LocalDateTime time, BigDecimal price, BigDecimal quantity) {
       this.numberID = numberID;
       this.time = time;
       this.price = price;
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
    
    public String toStringFile(){
        return "Time: " + String.valueOf(time) + ", Number ID: " + String.valueOf(numberID) +
            ", Price: " + String.valueOf(price) + ", Quantity: " + String.valueOf(quantity);
    }
}

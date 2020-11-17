package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;

public class SellOrder extends Order{
    
    public SellOrder(int orderID, BigDecimal quantity, BigDecimal price) {
        super(orderID, quantity, price);
    }
    
}

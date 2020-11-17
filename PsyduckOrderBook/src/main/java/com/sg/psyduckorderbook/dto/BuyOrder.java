package com.sg.psyduckorderbook.dto;

import java.math.BigDecimal;

public class BuyOrder extends Order{
    
    public BuyOrder(int orderID, BigDecimal quantity, BigDecimal price) {
        super(orderID, quantity, price);
    }
    
}

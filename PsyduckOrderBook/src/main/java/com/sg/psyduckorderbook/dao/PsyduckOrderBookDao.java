package com.sg.psyduckorderbook.dao;

public interface PsyduckOrderBookDao {
    
    public void display();
    
    public void displayStats();
    
    public void match();
    
    public void matchAllOrders();
    
    public void addAllOrders();
    
    public void addBuyOrder();
    
    public void addSellOrder();
    
    public void fillFullBuyOrder();
    
    public void fillFullSellOrder();
    
    public void fillPartialBuyOrder();
    
    public void fillPartialSellOrder();
}

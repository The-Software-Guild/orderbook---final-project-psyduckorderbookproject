package com.sg.psyduckorderbook.dao;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.util.ArrayList;

public interface PsyduckOrderBookDao {

    public boolean isEmpty();
    
    public Trade match();
    
    public void load() throws PsyduckOrderBookPersistenceException;
    
    public void loadFile(String file) throws PsyduckOrderBookPersistenceException;
    
    public ArrayList<Trade> matchAllOrders();
    
    public void addBuyOrder(BuyOrder newBuyOrder);
    
    public void addSellOrder(SellOrder newSellOrder);
    
    public void fillFullBuyOrder(BuyOrder myBuyOrder);
    
    public void fillFullSellOrder(SellOrder mySellOrder);
    
    public void fillPartialBuyOrder(BuyOrder myBuyOrder, SellOrder mySellOrder);
    
    public void fillPartialSellOrder(BuyOrder myBuyOrder, SellOrder mySellOrder);
    
    public ArrayList<BuyOrder> getBuyOrders();
    
    public ArrayList<SellOrder> getSellOrders();
    
    public ArrayList<ArrayList> getOrderBook();
    
    public ArrayList<Trade> getTrades();

    public void close() throws PsyduckOrderBookPersistenceException;
    
    public void loadFIXOrder(String file) throws PsyduckOrderBookPersistenceException;
}

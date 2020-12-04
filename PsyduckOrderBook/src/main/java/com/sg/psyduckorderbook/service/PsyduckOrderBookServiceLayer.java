package com.sg.psyduckorderbook.service;

import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.util.ArrayList;

public interface PsyduckOrderBookServiceLayer {
    
    public boolean isEmpty();
    
    public Trade match() throws PsyduckOrderBookIsEmpty;
    
    public ArrayList<SellOrder> getSellOrders();
    
    public ArrayList<BuyOrder> getBuyOrders();
    
    public void load() throws PsyduckOrderBookPersistenceException;
    
    public void loadFile(String file) throws PsyduckOrderBookPersistenceException;
    
    public ArrayList<Trade> matchAllOrders();
    
    public ArrayList<ArrayList> getOrderBook();
    
    public ArrayList<Trade> getTrades();

    public void close() throws PsyduckOrderBookPersistenceException;

    public void loadFIXFile(String fixName) throws PsyduckOrderBookPersistenceException;
}

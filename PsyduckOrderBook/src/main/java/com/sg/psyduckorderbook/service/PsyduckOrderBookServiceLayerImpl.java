package com.sg.psyduckorderbook.service;

import com.sg.psyduckorderbook.dao.PsyduckOrderBookDao;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.util.ArrayList;

public class PsyduckOrderBookServiceLayerImpl implements PsyduckOrderBookServiceLayer {

    PsyduckOrderBookDao localDao;
    
    public PsyduckOrderBookServiceLayerImpl(PsyduckOrderBookDao localDao){
        this.localDao = localDao;
    }
    
    @Override
    public void load()throws PsyduckOrderBookPersistenceException {
        localDao.load();
    }

    @Override
    public Trade match() throws PsyduckOrderBookIsEmpty{
        if (!localDao.isEmpty()) 
            return localDao.match();
        else 
            throw new PsyduckOrderBookIsEmpty("OrderBook is empty");
    }

    @Override
    public ArrayList<Trade> matchAllOrders() {
        return localDao.matchAllOrders();
    }

    @Override
    public boolean isEmpty(){
        return localDao.isEmpty();
    }
    
    @Override
    public ArrayList<ArrayList> getOrderBook() {
        return localDao.getOrderBook();
    }

    @Override
    public ArrayList<SellOrder> getSellOrders() {
       return localDao.getSellOrders();
    }

    @Override
    public ArrayList<BuyOrder> getBuyOrders() {
       return localDao.getBuyOrders();
    }
    
    @Override
    public ArrayList<Trade> getTrades(){
        return localDao.getTrades();
    }

    @Override
    public void close() throws PsyduckOrderBookPersistenceException{
        localDao.close();
    }

    @Override
    public void loadFile(String file) throws PsyduckOrderBookPersistenceException {
        localDao.loadFile(file);
    }
}

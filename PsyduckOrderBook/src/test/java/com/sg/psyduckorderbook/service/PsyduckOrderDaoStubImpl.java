package com.sg.psyduckorderbook.service;

import com.sg.psyduckorderbook.dao.PsyduckOrderBookDao;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PsyduckOrderDaoStubImpl implements PsyduckOrderBookDao{
    
    public Trade onlyTrade = null;
    public BuyOrder onlyBuyOrder = null;
    public SellOrder onlySellOrder = null;
    public ArrayList<ArrayList> orderbook = new ArrayList<>();
    public ArrayList<BuyOrder> buyOrders = new ArrayList<>();
    public ArrayList<SellOrder> sellOrders = new ArrayList<>();
    public ArrayList<Trade> trades = new ArrayList<>();
    
    public PsyduckOrderDaoStubImpl() {
        onlyBuyOrder = new BuyOrder(1001, new BigDecimal(42), new BigDecimal(190));
        onlySellOrder = new SellOrder(976, new BigDecimal(50), new BigDecimal(191));
        buyOrders.add(onlyBuyOrder);
        sellOrders.add(onlySellOrder);
        orderbook.add(buyOrders);
        orderbook.add(sellOrders);

        trades.add(onlyTrade);
    }

    public ArrayList<BuyOrder> getBuyOrders() {
        return buyOrders;
    }
    
    public ArrayList<SellOrder> getSellOrders() {
        return sellOrders;
    }
    
    @Override
    public boolean isEmpty() {
        boolean buyEmpty = true;
        boolean sellEmpty = true;
        
        if(buyOrders.isEmpty())
            buyEmpty = false;
        
        if(sellOrders.isEmpty())
            sellEmpty = false;
        
        if(buyEmpty == false && sellEmpty == false)
            return true;
        else
            return false;
    }

    @Override
    public Trade match() {
        int id = 1;
        LocalDateTime time = LocalDateTime.now();
        BigDecimal price = onlyBuyOrder.getPrice();
        BigDecimal quantity = onlyBuyOrder.getQuantity();
        
        onlyTrade = new Trade(id, time, price, quantity);
        trades.add(onlyTrade);
        
        return onlyTrade;
    }
    
    @Override
    public ArrayList<Trade> getTrades() {
        return trades;
    }
    
    @Override
    public ArrayList<ArrayList> getOrderBook() {
        return orderbook;
    }
    
    //Following code is not used here
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void matchAllOrders() {
        
    }
    
    @Override
    public void addBuyOrder(BuyOrder newBuyOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSellOrder(SellOrder newSellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFullBuyOrder(BuyOrder myBuyOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillFullSellOrder(SellOrder mySellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillPartialBuyOrder(BuyOrder myBuyOrder, SellOrder mySellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fillPartialSellOrder(BuyOrder myBuyOrder, SellOrder mySellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load() throws PsyduckOrderBookPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

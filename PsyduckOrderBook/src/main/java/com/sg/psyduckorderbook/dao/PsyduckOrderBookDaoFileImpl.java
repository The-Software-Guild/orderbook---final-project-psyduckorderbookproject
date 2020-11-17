package com.sg.psyduckorderbook.dao;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PsyduckOrderBookDaoFileImpl implements PsyduckOrderBookDao{
    
    PrintWriter orderBookOut, tradesOut;
    private ArrayList<ArrayList> orderbook;
    private ArrayList<BuyOrder> buyOrders;
    private ArrayList<SellOrder> sellOrders;
    private ArrayList<Trade> trades;
           
    @Override
    public void load() throws PsyduckOrderBookPersistenceException {
        BigDecimal tempPrice, tempQuantity;
        orderbook = new ArrayList<>();
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        trades = new ArrayList<>();
        orderbook.add(buyOrders);
        orderbook.add(sellOrders);
        Random randomizer = new Random();
        try {
            orderBookOut = new PrintWriter(new FileWriter("OrderBook.txt"));
            orderBookOut.println("Sell Orders" + "\t\t\t\t\t\t" + "Buy Orders");
            tradesOut = new PrintWriter(new FileWriter("Trades.txt"));
        } catch (IOException e) {
            throw new PsyduckOrderBookPersistenceException("Could not create files");
        }
        
        for(int i = 1; i < 2001; i++){
            int priceInt = randomizer.nextInt(101);
            int quantityInt = randomizer.nextInt(31) + 20;
            if (priceInt < 10){
                tempPrice = new BigDecimal("190.0" + priceInt);
            } else if (priceInt < 100){
                tempPrice = new BigDecimal("190." + priceInt);
            } else {
                tempPrice = new BigDecimal("191");
            }
            tempQuantity = new BigDecimal("" + quantityInt);
            tempQuantity.setScale(2, RoundingMode.HALF_UP);
            tempPrice.setScale(2, RoundingMode.HALF_UP);
            if(i % 2 == 0){
                addBuyOrder(new BuyOrder(i, tempQuantity, tempPrice));
            } else {
                addSellOrder(new SellOrder(i, tempQuantity, tempPrice));
            }
        }
        Collections.sort(buyOrders);
        Collections.sort(sellOrders);
    }
    
     public Trade match() {
        Trade newTrade = null;
        BuyOrder myBuyOrder = buyOrders.get(buyOrders.size() - 1);
        SellOrder mySellOrder = sellOrders.get(sellOrders.size() - 1);
        /*
        BigDecimal compareBD = new BigDecimal("9999999");
        BigDecimal difference;
        SellOrder mySellOrder = null;
        SellOrder compareSellOrder;
        BuyOrder myBuyOrder = buyOrders.get(0);
        for (int i = sellOrders.size() - 1; i > 0; i--){
            compareSellOrder = sellOrders.get(i);
            difference = compareSellOrder.getPrice().subtract(myBuyOrder.getPrice());
            if (difference.compareTo(BigDecimal.ZERO) == -1){
                difference = difference.multiply(new BigDecimal("-1"));
            }
            if (difference.compareTo(compareBD) == -1){
                compareBD = difference;
                mySellOrder = sellOrders.get(i);
            }
        }
        */
        if (myBuyOrder.getQuantity().compareTo(mySellOrder.getQuantity()) == 0){
            fillFullBuyOrder(myBuyOrder);
            fillFullSellOrder(mySellOrder);
            newTrade = new Trade(trades.size() + 1, LocalDateTime.now(), mySellOrder.getPrice(), myBuyOrder.getQuantity());
        } else if (myBuyOrder.getQuantity().compareTo(mySellOrder.getQuantity()) == 1){
            fillPartialBuyOrder(myBuyOrder, mySellOrder);
            fillFullSellOrder(mySellOrder);
            newTrade = new Trade(trades.size() + 1, LocalDateTime.now(), mySellOrder.getPrice(), mySellOrder.getQuantity());
        } else if (myBuyOrder.getQuantity().compareTo(mySellOrder.getQuantity()) == -1){
            fillFullBuyOrder(myBuyOrder);
            fillPartialSellOrder(myBuyOrder, mySellOrder);
            newTrade = new Trade(trades.size() + 1, LocalDateTime.now(), mySellOrder.getPrice(), myBuyOrder.getQuantity());
        }
        tradesOut.println(newTrade.toStringFile());
        trades.add(newTrade);
        return newTrade;
    }

    @Override
    public void matchAllOrders() {
        while (!isEmpty()){
            match();
        }
    }


    @Override
    public void addBuyOrder(BuyOrder newBuyOrder) {
        buyOrders.add(newBuyOrder);
        orderBookOut.println(newBuyOrder.toStringFile());
    }

    @Override
    public void addSellOrder(SellOrder newSellOrder) {
        sellOrders.add(newSellOrder);
        orderBookOut.print(newSellOrder.toStringFile() + "\t\t");
    }

    @Override
    public void fillFullBuyOrder(BuyOrder myBuyOrder) {
        buyOrders.remove(myBuyOrder);
    }

    @Override
    public void fillFullSellOrder(SellOrder mySellOrder) {
        sellOrders.remove(mySellOrder);
    }

    @Override
    public void fillPartialBuyOrder(BuyOrder myBuyOrder, SellOrder mySellOrder) {
       myBuyOrder.setQuantity(myBuyOrder.getQuantity().subtract(mySellOrder.getQuantity()));
    }

    @Override
    public void fillPartialSellOrder(BuyOrder myBuyOrder, SellOrder mySellOrder) {
        mySellOrder.setQuantity(mySellOrder.getQuantity().subtract(myBuyOrder.getQuantity()));
    }

    @Override
    public boolean isEmpty() {
        return buyOrders.isEmpty()||sellOrders.isEmpty();
    }
    
    @Override
    public ArrayList<BuyOrder> getBuyOrders(){
        return buyOrders;
    }
    
    @Override
    public ArrayList<SellOrder> getSellOrders(){
        return sellOrders;
    }
    
    @Override
    public ArrayList<ArrayList> getOrderBook(){
        return orderbook;
    }
    
    @Override
    public ArrayList<Trade> getTrades(){
        return trades;
    }

    @Override
    public void close() {
        orderBookOut.close();
        tradesOut.close();
    }
}

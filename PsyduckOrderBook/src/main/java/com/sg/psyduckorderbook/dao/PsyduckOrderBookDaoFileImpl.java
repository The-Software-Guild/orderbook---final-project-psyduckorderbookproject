package com.sg.psyduckorderbook.dao;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PsyduckOrderBookDaoFileImpl implements PsyduckOrderBookDao{
    
    PrintWriter orderBookOut, tradesOut, exportOut;
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
            //orderBookOut.println("Buy Orders" + "\t\t\t\t\t\t" + "Sell Orders");
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
            if(i % 2 == 1){
                addBuyOrder(new BuyOrder(i, tempQuantity, tempPrice));
            } else {
                addSellOrder(new SellOrder(i, tempQuantity, tempPrice));
            }
        }
        Collections.sort(buyOrders);
        Collections.sort(sellOrders);
        addAllOrders();
    }
    
    @Override
    public void loadFile(String file) throws PsyduckOrderBookPersistenceException {
        Scanner input;
        orderbook = new ArrayList<>();
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        trades = new ArrayList<>();
        orderbook.add(buyOrders);
        orderbook.add(sellOrders);
        
        try {
            orderBookOut = new PrintWriter(new FileWriter("OrderBook.txt"));
            tradesOut = new PrintWriter(new FileWriter("Trades.txt"));
        } catch (IOException e) {
            throw new PsyduckOrderBookPersistenceException("Could not create files");
        }
        
        try {
            input = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            throw new PsyduckOrderBookPersistenceException("Could not load orders", e);
        }
        String currentLine;
        currentLine = input.nextLine();
        
        String delimeter = null;
        
        if(currentLine.contains("::"))
            delimeter = "::";
        else if (currentLine.contains(";"))
            delimeter = ";";
        else if (currentLine.contains(","))
            delimeter = ",";
        
        BuyOrder currentBuyOrder;
        SellOrder currentSellOrder;
        boolean nextLine = input.hasNext();
        
        if(!delimeter.equals(null)) {
            while (nextLine) {
                int cut1 = currentLine.indexOf(delimeter);
                String idString = currentLine.substring(0, cut1);
            
                int cut2 = currentLine.indexOf(delimeter, cut1 + delimeter.length());
                String quantityString = currentLine.substring(cut1 + delimeter.length(), cut2);
            
                String priceString = currentLine.substring(cut2  + delimeter.length());
            
                int id = Integer.parseInt(idString);
                BigDecimal quantity = new BigDecimal(quantityString);
                BigDecimal price = new BigDecimal(priceString);
            
                if(id % 2 == 1){
                    addBuyOrder(new BuyOrder(id, quantity, price));
                } else {
                    addSellOrder(new SellOrder(id, quantity, price));
                }
                if (nextLine = input.hasNext())
                    currentLine = input.nextLine();
            }
        }
        Collections.sort(buyOrders);
        Collections.sort(sellOrders);
        addAllOrders();
    }
    
    public void exportFile() throws PsyduckOrderBookPersistenceException{
        try{
            exportOut = new PrintWriter(new FileWriter("Book.txt"));
        } catch (IOException e){
            throw new PsyduckOrderBookPersistenceException("Could not export data from memory");
        }
        if (!buyOrders.isEmpty()){
            for (int i = 0; i < buyOrders.size(); i++){
		exportOut.println(buyOrders.get(i).getOrderID() + "::" + 
                    buyOrders.get(i).getQuantity() + "::" + 
                    buyOrders.get(i).getPrice());
                //System.out.println("Buy " + i);
            }
        }
        if (!sellOrders.isEmpty()){
            for (int i = 0; i < sellOrders.size(); i++){
                exportOut.println(sellOrders.get(i).getOrderID() + "::" + 
                    sellOrders.get(i).getQuantity() + "::" + 
                    sellOrders.get(i).getPrice());
                //System.out.println("Sell: " + i);
            }  
        }
        exportOut.flush();
        exportOut.close();
    }
    
    public void addAllOrders() {
        boolean nextLine = false;
        int size = 0;
        int difference = 0;
        orderBookOut.println("Buy Orders\t\t\t\t\t\t\t\tSell Orders");
        ArrayList<BuyOrder> myBuyOrders = orderbook.get(0);
        ArrayList<SellOrder> mySellOrders = orderbook.get(1);
        
        if(myBuyOrders.size() > mySellOrders.size()) {
            size = myBuyOrders.size();
            difference = myBuyOrders.size() - mySellOrders.size();
            for(int i = size; i >= 0; i --) {
            if (i  < myBuyOrders.size()) {
                orderBookOut.print("ID: " + myBuyOrders.get(i).getOrderID() + ", Price: " +
                    myBuyOrders.get(i).getPrice() + ", Quantity: " + 
                    myBuyOrders.get(i).getQuantity() + "\t\t\t\t\t");
                nextLine = false;
            } else {
               orderBookOut.print("\t\t\t\t\t\t\t\t\t");
            }
            if (i - difference < mySellOrders.size() && i - difference >= 0) {
                orderBookOut.print("ID: " + mySellOrders.get(i - difference).getOrderID() + ", Price: " +
                    mySellOrders.get(i - difference).getPrice() + ", Quantity: " + 
                    mySellOrders.get(i - difference).getQuantity() + "\t\t\t\t\t");
                nextLine = true;
            } else {
                nextLine = true;
            }
            if(nextLine == true)
                orderBookOut.println();
            }
        } else {
            size = mySellOrders.size();
            difference = mySellOrders.size() - myBuyOrders.size();
            for(int i = size; i >= 0; i --) {
            if (i - difference < myBuyOrders.size() && i - difference >= 0) {
                orderBookOut.print("ID: " + myBuyOrders.get(i - difference).getOrderID() + ", Price: " +
                    myBuyOrders.get(i - difference).getPrice() + ", Quantity: " + 
                    myBuyOrders.get(i - difference).getQuantity() + "\t\t\t\t\t");
                nextLine = false;
            } else {
               orderBookOut.print("\t\t\t\t\t\t\t\t\t");
            }
            if (i  < mySellOrders.size()) {
                orderBookOut.print("ID: " + mySellOrders.get(i).getOrderID() + ", Price: " +
                    mySellOrders.get(i).getPrice() + ", Quantity: " + 
                    mySellOrders.get(i).getQuantity() + "\t\t\t\t\t");
                nextLine = true;
            } else {
                nextLine = true;
            }
            if(nextLine == true)
                orderBookOut.println();
        }
        }
        
        System.out.println("Buy " + myBuyOrders.size());
        System.out.println("Sell " + mySellOrders.size());
        /*
        for(int i = size - 1; i >= 0; i --) {
            if (i  < myBuyOrders.size()) {
                orderBookOut.print("ID: " + myBuyOrders.get(i).getOrderID() + ", Price: " +
                    myBuyOrders.get(i).getPrice() + ", Quantity: " + 
                    myBuyOrders.get(i).getQuantity() + "\t\t\t\t\t");
                nextLine = false;
            } else {
               orderBookOut.print("\t\t\t\t\t\t\t\t\t");
            }
            if (i - difference < mySellOrders.size()) {
                orderBookOut.print("ID: " + mySellOrders.get(i).getOrderID() + ", Price: " +
                    mySellOrders.get(i).getPrice() + ", Quantity: " + 
                    mySellOrders.get(i).getQuantity() + "\t\t\t\t\t");
                nextLine = true;
            } else {
                nextLine = true;
            }
            if(nextLine == true)
                orderBookOut.println();
        }
*/
        orderBookOut.flush();
        orderBookOut.close();
    }
    
    @Override
     public Trade match() {
        Trade newTrade = null;
        BuyOrder myBuyOrder = buyOrders.get(buyOrders.size() - 1);
        SellOrder mySellOrder = sellOrders.get(sellOrders.size() - 1);
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
    public ArrayList<Trade> matchAllOrders() {
        ArrayList<Trade> output = new ArrayList<>();
        while (!isEmpty()){
            output.add(match());
            
        }
        return output;
    }

    @Override
    public void addBuyOrder(BuyOrder newBuyOrder) {
        buyOrders.add(newBuyOrder);
    }

    @Override
    public void addSellOrder(SellOrder newSellOrder) {
        sellOrders.add(newSellOrder);
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
    public void close() throws PsyduckOrderBookPersistenceException{
        exportFile();
        tradesOut.flush();
        tradesOut.close();
    }
}

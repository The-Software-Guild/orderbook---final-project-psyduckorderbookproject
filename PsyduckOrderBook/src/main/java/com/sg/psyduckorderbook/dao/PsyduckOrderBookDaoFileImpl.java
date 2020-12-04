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
    
    PrintWriter orderBookOut, tradesOut, exportOut, fixOut;
    private ArrayList<ArrayList> orderbook;
    private ArrayList<BuyOrder> buyOrders;
    private ArrayList<SellOrder> sellOrders;
    private ArrayList<Trade> trades;
    BigDecimal cumulativeQuantity = new BigDecimal(0);
    BigDecimal totalPrice = new BigDecimal(0);
           
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
            fixOut = new PrintWriter(new FileWriter("FixMessages.txt"));
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
            fixOut = new PrintWriter(new FileWriter("FixMessages.txt"));
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
    
    @Override
    public void loadFIXOrder(String file) throws PsyduckOrderBookPersistenceException{
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
            fixOut = new PrintWriter(new FileWriter("FixMessages.txt"));
        } catch (IOException e) {
            throw new PsyduckOrderBookPersistenceException("Could not create files");
        }
        try {
            input = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            throw new PsyduckOrderBookPersistenceException("Could not load orders", e);
        }try {
            orderBookOut = new PrintWriter(new FileWriter("OrderBook.txt"));
            tradesOut = new PrintWriter(new FileWriter("Trades.txt"));
            fixOut = new PrintWriter(new FileWriter("FixMessages.txt"));
        } catch (IOException e) {
            throw new PsyduckOrderBookPersistenceException("Could not create files");
        }
        String currentLine;
        currentLine = input.nextLine();
        String delimeter = null;
        if(currentLine.contains("^"))
            delimeter = "^";
        else if (currentLine.contains(";"))
            delimeter = ";";
        else if (currentLine.contains("|"))
            delimeter = "|";
        boolean nextLine = input.hasNext();
        if(!delimeter.equals(null)) {
            while (nextLine) {
                int cut1 = currentLine.indexOf(delimeter);
                String fixTypeFIXString = currentLine.substring(0, cut1);
                int cut2 = currentLine.indexOf(delimeter, cut1 + delimeter.length());
                String lengthFIXString = currentLine.substring(cut1 + delimeter.length(), cut2);
		int cut3 = currentLine.indexOf(delimeter, cut2 + delimeter.length());
                String messageTypeFIXString = currentLine.substring(cut2 + delimeter.length(), cut3);
		if (messageTypeFIXString.equals("35=D")){
                    int cut4 = currentLine.indexOf(delimeter, cut3 + delimeter.length());
                    String targetFIXString = currentLine.substring(cut3 + delimeter.length(), cut4);
                    int cut5 = currentLine.indexOf(delimeter, cut4 + delimeter.length());
                    String senderFIXString = currentLine.substring(cut4 + delimeter.length(), cut5);
                    int cut6 = currentLine.indexOf(delimeter, cut5 + delimeter.length());
                    String tickerNameFIXString = currentLine.substring(cut5 + delimeter.length(), cut6);
                    int cut7 = currentLine.indexOf(delimeter, cut6 + delimeter.length());
                    String limitMarketFIXString = currentLine.substring(cut6 + delimeter.length(), cut7);
                    int cut8 = currentLine.indexOf(delimeter, cut7 + delimeter.length());
                    String quantityFIXString = currentLine.substring(cut7 + delimeter.length(), cut8);
                    String quantityConvString = quantityFIXString.substring(3);
                    BigDecimal quantity = new BigDecimal(quantityConvString);
                    int cut9 = currentLine.indexOf(delimeter, cut8 + delimeter.length());
                    String idFIXString = currentLine.substring(cut8 + delimeter.length(), cut9);
                    String idConvString = idFIXString.substring(3);
                    int id = Integer.parseInt(idConvString);
                    int cut10 = currentLine.indexOf(delimeter, cut9 + delimeter.length());
                    String priceFIXString = currentLine.substring(cut9 + delimeter.length(), cut10);
                    String priceConvString = priceFIXString.substring(3);
                    BigDecimal price = new BigDecimal(priceConvString);
                    String buySellFIXString = currentLine.substring(cut10  + delimeter.length());
                    if(buySellFIXString.equals("54=1")){
                        addBuyOrder(new BuyOrder(id, quantity, price));
                    } else if (buySellFIXString.equals("54=2")){
                        addSellOrder(new SellOrder(id, quantity, price));
                    }
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
            }
        }
        if (!sellOrders.isEmpty()){
            for (int i = 0; i < sellOrders.size(); i++){
                exportOut.println(sellOrders.get(i).getOrderID() + "::" + 
                    sellOrders.get(i).getQuantity() + "::" + 
                    sellOrders.get(i).getPrice());
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
        cumulativeQuantity = cumulativeQuantity.add(newBuyOrder.getQuantity());
        totalPrice = totalPrice.add(newBuyOrder.getPrice());   
        int length = 0;
        String tempMessage = "8=FIX4.2|9=" + length + "|35=D|49=JMS|56=AMIR|55=PSYD|40=2"
                + "|38=" + newBuyOrder.getQuantity() + "|11=" + newBuyOrder.getOrderID()
                + "|44=" + newBuyOrder.getPrice() + "|54=1"; 
        length = tempMessage.length() - 17;
        String message = "8=FIX4.2|9=" + length + "|35=D|49=JMS|56=AMIR|55=PSYD|40=2"
                + "|38=" + newBuyOrder.getQuantity() + "|11=" + newBuyOrder.getOrderID()
                + "|44=" + newBuyOrder.getPrice() + "|54=1";
        buyOrders.add(newBuyOrder);
        fixOut.println(message);
    }

    @Override
    public void addSellOrder(SellOrder newSellOrder) {
        cumulativeQuantity = cumulativeQuantity.add(newSellOrder.getQuantity());
        totalPrice = totalPrice.add(newSellOrder.getPrice());   
        int length = 0;
        String tempMessage = "8=FIX4.2|9=" + length + "|35=D|49=JMS|56=AMIR|55=PSYD|40=2"
                + "|38=" + newSellOrder.getQuantity() + "|11=" + newSellOrder.getOrderID()
                + "|44=" + newSellOrder.getPrice() + "|54=2"; 
        length = tempMessage.length() - 17;
        String message = "8=FIX4.2|9=" + length + "|35=D|49=JMS|56=AMIR|55=PSYD|40=2"
                + "|38=" + newSellOrder.getQuantity() + "|11=" + newSellOrder.getOrderID()
                + "|44=" + newSellOrder.getPrice() + "|54=2";
        sellOrders.add(newSellOrder);
        fixOut.println(message);
    }

    @Override
    public void fillFullBuyOrder(BuyOrder myBuyOrder) {
        cumulativeQuantity = cumulativeQuantity.add(myBuyOrder.getQuantity());
        totalPrice = totalPrice.add(myBuyOrder.getPrice());   
        int length = 0;
        String tempMessage = "8=FIX4.2|9=" + length + "|35=D|55=PSYD"
                + "|39=2|150=2|20=0|17=" + myBuyOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + myBuyOrder.getPrice() + "|32="+ myBuyOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP); 
        length = tempMessage.length() - 17;
        String message = "8=FIX4.2|9=" + length + "|35=D|55=PSYD"
                + "|39=2|150=2|20=0|17=" + myBuyOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + myBuyOrder.getPrice() + "|32="+ myBuyOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP);
        buyOrders.remove(myBuyOrder);
        fixOut.println(message);
    }

    @Override
    public void fillFullSellOrder(SellOrder mySellOrder) {
        cumulativeQuantity = cumulativeQuantity.add(mySellOrder.getQuantity());
        totalPrice = totalPrice.add(mySellOrder.getPrice());
        int length = 0;
        String tempMessage = "8=FIX4.2|9=" + length + "|35=8|55=PSYD"
                + "|39=2|150=2|20=0|17=" + mySellOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + mySellOrder.getPrice() + "|32="+ mySellOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP); 
        length = tempMessage.length() - 17;
        String message = "8=FIX4.2|9=" + length + "|35=8|55=PSYD"
                + "|39=2|150=2|20=0|17=" + mySellOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + mySellOrder.getPrice() + "|32="+ mySellOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP);
        sellOrders.remove(mySellOrder);
        fixOut.println(message);
    }

    @Override
    public void fillPartialBuyOrder(BuyOrder myBuyOrder, SellOrder mySellOrder) {
        cumulativeQuantity = cumulativeQuantity.add(myBuyOrder.getQuantity());
        totalPrice = totalPrice.add(myBuyOrder.getPrice());
        
        int length = 0;
        String tempMessage = "8=FIX4.2|9=" + length + "|35=8|55=PSYD"
                + "|39=2|150=2|20=0|17=" + myBuyOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + myBuyOrder.getPrice() + "|32="+ mySellOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP); 
        length = tempMessage.length() - 17;
        String message = "8=FIX4.2|9=" + length + "|35=8|55=PSYD"
                + "|39=2|150=2|20=0|17=" + myBuyOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + myBuyOrder.getPrice() + "|32="+ mySellOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP);
        buyOrders.remove(myBuyOrder);
       myBuyOrder.setQuantity(myBuyOrder.getQuantity().subtract(mySellOrder.getQuantity()));
       fixOut.println(message);
    }

    @Override
    public void fillPartialSellOrder(BuyOrder myBuyOrder, SellOrder mySellOrder) {
        cumulativeQuantity = cumulativeQuantity.add(mySellOrder.getQuantity());
        totalPrice = totalPrice.add(mySellOrder.getPrice());
        int length = 0;
        String tempMessage = "8=FIX4.2|9=" + length + "|35=8|55=PSYD"
                + "|39=1|150=1|20=0|17=" + mySellOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + mySellOrder.getPrice() + "|32="+ myBuyOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP); 
        length = tempMessage.length() - 17;
        String message = "8=FIX4.2|9=" + length + "|35=8|55=PSYD"
                + "|39=1|150=1|20=0|17=" + mySellOrder.getOrderID() + "|14=" + cumulativeQuantity  
                + "|151=0|31=" + mySellOrder.getPrice() + "|32="+ myBuyOrder.getQuantity()
                + "|6=" + totalPrice.divide(cumulativeQuantity, 2, RoundingMode.HALF_UP);
        mySellOrder.setQuantity(mySellOrder.getQuantity().subtract(myBuyOrder.getQuantity()));
        fixOut.println(message);
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

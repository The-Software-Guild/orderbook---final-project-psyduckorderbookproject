package com.sg.psyduckorderbook.ui;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.Order;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PsyduckOrderBookView {
    
    private UserIO io;
    
    public PsyduckOrderBookView (UserIO io) {
        this.io = io;
    }
    
    public int getMenuSelection() {
        io.println("Main Menu");
        io.println("1. View Order Book");
        io.println("2. View Order Book Stats");
        io.println("3. Match an Order");
        io.println("4. Match All Orders");
        io.println("5. View a Trade");
        io.println("6. View All Trades");
        io.println("7. Exit");  
        return io.readInt("Please select from the choices above.", 1, 7);
    }

    public void displayOrderBook(List<ArrayList> orderBook) {
        int n = 1;
        int page = 10;
        int size = 0;
        int amount = 0;
        io.println("Buy Orders\t\t\t\t\tSell Orders");
        ArrayList<BuyOrder> myBuyOrders = orderBook.get(0);
        ArrayList<SellOrder> mySellOrders = orderBook.get(1);
        size = myBuyOrders.size();
        int difference = myBuyOrders.size() - mySellOrders.size();
        amount = size/page;
        for(int i = size ; i >= 0; i --) {
            
            if (i < myBuyOrders.size()) {
                io.print("ID: " + myBuyOrders.get(i).getOrderID() + ", Price: " +
                    myBuyOrders.get(i).getPrice() + ", Quantity: " + 
                    myBuyOrders.get(i).getQuantity() + "\t\t");
            }
            if (i - difference < mySellOrders.size()) {
                io.println("ID: " + mySellOrders.get(i-difference).getOrderID() + ", Price: " +
                    mySellOrders.get(i-difference).getPrice() + ", Quantity: " + 
                    mySellOrders.get(i-difference).getQuantity() + "       ");
            }
            n++;
            if(n > page) {
                boolean input = false;
                String answer = "";
                io.println("");
                if(page/orderBook.size() == 0) {
                    System.out.println(size);
                    io.println("Would you like to keep going? There is 1 page left");
                } else {
                    io.println("Would you like to keep going? There are " + 
                        String.valueOf(amount) + " pages left");
                }
                while(!input) {
                    answer = io.readString("Please input yes or no").toLowerCase();
                    if (answer.equals("yes") || answer.equals("no")) {
                        input = true;
                    } else {
                        io.println("Invalid input");
                    }
                }
                if(answer.equals("yes")) {
                    n = 1;
                    amount --;
                    io.println("");
                    io.println("Buy Orders                                  Sell Orders");
                } else if (answer.equals("no"))
                    break;
            }
        }
    }

    public void exitMessage() {
        io.println("GOOD BYE!");
    }

    public void unknownCommand() {
        io.println("UNKNOWN COMMANDS ENTERED");
    }
    
    public void allTrades(List<Trade> trades) {
        int n = 1;
        int page = 10;
        int size = trades.size();
        int amount = size/page;
        for (Trade currentTrade: trades) {
            io.println("Time: " + currentTrade.getTime() + ", Trade ID#: " + 
                currentTrade.getNumberID() + ", Quantity: " + currentTrade.getQuantity()); 
            n++;
            if(n > page) {
                io.println("");
                boolean input = false;
                String answer = "";
                io.println("Would you like to keep going? There are " + 
                    String.valueOf(amount) + " pages left");
                while(!input) {
                    answer = io.readString("Please input yes or no").toLowerCase();
                    if (answer.equals("yes") || answer.equals("no")) {
                        input = true;
                    } else {
                        io.println("Invalid input");
                    }
                }
                if(answer.equals("yes")) {
                    n = 1;
                    amount --;
                    io.println("");
                }
            }
        }
    }

    public void matchID(List<Trade> trades) {
        int num = io.readInt("Please enter an ID number");
        boolean valid = false;
        
        for (Trade currentTrade: trades) { 
            if (num == currentTrade.getNumberID()) {
                io.println("ID: " + currentTrade.getNumberID() + ", Price: " + 
                    currentTrade.getPrice()+ ", Quantity: " + currentTrade.getQuantity()
                    + ", Time: " + currentTrade.getTime());
                valid = true;
            }
        }
        if (valid == false) {
            io.println("There is no order by that ID number");
        }
    }

    public void matchAllBanner() {
        io.println("All Orders Have Been Matched");
    }

    public void matchAllOrders(Order order1, Order order2) {
        io.print("ID: " + order1.getOrderID() + ", Price: " + 
                    order1.getPrice()+ ", Quantity: " + order1.getQuantity() + "       ");
        io.println("ID: " + order2.getOrderID() + ", Price: " + 
                    order2.getPrice()+ ", Quantity: " + order2.getQuantity() + "       ");
    }

    public void orderBookIsEmpty() {
        io.println("Order book is empty");
    }

    public void displayStats(List<SellOrder> sellOrders, List<BuyOrder> buyOrders) {
        int numBuyOrders = 0;
        int numSellOrders = 0;
        BigDecimal buyQuantity = new BigDecimal(0);
        BigDecimal sellQuantity = new BigDecimal(0);
        BigDecimal avgBuyPrice = new BigDecimal(0);
        BigDecimal avgSellPrice = new BigDecimal(0);
        
        for (BuyOrder buyer: buyOrders) {
            avgBuyPrice = buyer.getPrice().add(avgBuyPrice);
            buyQuantity = buyer.getQuantity().add(buyQuantity);
            numBuyOrders++;
        }
        avgBuyPrice = avgBuyPrice.divide(new BigDecimal(buyOrders.size()));
        
        for (SellOrder seller: sellOrders) {
            avgSellPrice = seller.getPrice().add(avgSellPrice);
            sellQuantity = seller.getQuantity().add(sellQuantity);
            numSellOrders++;
        }
        avgSellPrice = avgSellPrice.divide(new BigDecimal(sellOrders.size()));
        
        io.println("OrderBook Statistics");
        io.println("    Number of Sell Orders: " + String.valueOf(numSellOrders));
        io.println("    Number of Buy Orders: " + String.valueOf(numBuyOrders));
        io.println("    Overall Sell Quantity: " + String.valueOf(sellQuantity));
        io.println("    Overall Buy Quantity: " + String.valueOf(buyQuantity));
        io.println("    Average Buy Price: $" + String.valueOf(avgBuyPrice));
        io.println("    Average Sel Price: $" + String.valueOf(avgSellPrice));
    }

    public void tradeSuccess(int tradeNumber, LocalDateTime time) {
        io.println(String.valueOf(time) + " Trade has successfully been completed, trade ID# is: " + tradeNumber);
    }

    public void tradeFailure() {
        io.println("Error while doing trade");
    }

    public void load() {
        io.println("Matching all Orders please wait...");
    }

    public void loadError() {
        io.println("Error while loading program");
    }

    public void emptyOrderBook() {
        io.println("OrderBook is empty");
    }

    public void noTrades() {
        io.println("No trades have been completed at this time");
    }

    public String takeInput() {
        boolean valid = false;
        String input = null;
        while(!valid) {
            input = io.readString("Do you want to read input? (y/n)");
            input.toLowerCase();
            if(input.equals("y") || input.equals("n")) {
                valid = true;
            } else {
                io.println("Not a valid input please enter y or n");
            }
        }
        return input;
    }

    public String getFile() {
        return io.readString("Please enter the name of the file you want to read from");
    }

    public int decideFile() {
        io.println("Do you want to read from a normal file or a FIX file?");
        io.println("Press 1 for normal file");
        io.println("Press 2 for a FIX file");
        return io.readInt("", 1, 2);
    }

    public String getFIXName() {
        return io.readString("Please enter the name of the FIX file you want to read from");
    }
}
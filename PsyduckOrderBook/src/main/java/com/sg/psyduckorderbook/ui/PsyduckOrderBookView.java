package com.sg.psyduckorderbook.ui;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.Order;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.math.BigDecimal;
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

    public void displayOrderBook(List<Order> orderBook) {
        int i = 1;
        int page = 10;
        io.println("Buy Orders                            Sell Orders");
        for (Order currentOrder: orderBook) {
            if(i%2 != 0) {
                io.print("ID: " + currentOrder.getOrderID() + ", Price: " + 
                    currentOrder.getPrice()+ ", Quantity: " + currentOrder.getQuantity() + "       ");
                i++;
                
            } else if (i%2 == 0) {
                io.println("ID: " + currentOrder.getOrderID() + ", Price: " + 
                    currentOrder.getPrice()+ ", Quantity: " + currentOrder.getQuantity());
                i++;
            } 
            if(i > page) {
                boolean input = false;
                String answer = "";
                io.println("");
                if(page/orderBook.size() == 0) {
                    io.println("Would you like to keep going? There is " + 
                        String.valueOf(page/orderBook.size() + 1 + " page left"));
                } else {
                    io.println("Would you like to keep going? There are " + 
                        String.valueOf(page/orderBook.size() + 1 + " pages left"));
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
                    i = 1;
                    io.println("");
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

    public void matchID(List<Trade> trades) {
        int num = io.readInt("Please enter an ID number");
        boolean valid = false;
        
        for (Trade currentTrade: trades) { 
            if (num == currentTrade.getNumberID()) {
                io.println("ID: " + currentTrade.getNumberID() + ", Price: " + 
                    currentTrade.getQuantity() + ", Quantity: " + currentTrade.getQuantity()
                    + ", Time: " + currentTrade.getTime());
                valid = true;
            }
        }
        
        if (valid == false) {
            io.println("There is no order by that ID number");
        }
    }

    public void matchOrder(BuyOrder ex1, SellOrder ex2) {  
        io.println("Matching the following buy and sell orders");
        io.println("Buy Order                             Sell Order");
        io.print("ID: " + ex1.getOrderID() + ", Price: " + 
                    ex1.getPrice()+ ", Quantity: " + ex1.getQuantity() + "       ");
        io.println("ID: " + ex2.getOrderID() + ", Price: " + 
                    ex2.getPrice()+ ", Quantity: " + ex2.getQuantity() + "       ");
    }

    public void matchAllBanner() {
        io.println("Matching All Orders");
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
            numBuyOrders++;
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

    public void tradeSuccess(int tradeNumber) {
        io.println("Trade has successfully been completed, trade ID# is: " + tradeNumber);
    }

    public void tradeFailure() {
        io.println("Error while doing trade");
    }
}
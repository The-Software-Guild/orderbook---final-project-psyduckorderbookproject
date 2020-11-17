package com.sg.psyduckorderbook.controller;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.Order;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import com.sg.psyduckorderbook.ui.PsyduckOrderBookView;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PsyduckOrderBookController {
    
    private PsyduckOrderBookView view;
    
    public PsyduckOrderBookController (PsyduckOrderBookView view) {
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            int menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:     //View Order Book
                    viewOrderBook();
                    break;
                case 2:     //Match One Order
                    viewOrderBookStats();
                    break;
                case 3:
                    matchOrder();
                    break;
                //Not able to do until I know the logic of it
                case 4:     //Match All Orders
                    matchAllOrders();
                    break;
                case 5:     //View particular trade
                    matchTrade();
                    break;
                case 6:
                    viewAllTrades();
                    break;
                case 7:     //Ending the program
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.getMenuSelection();
    }

    private void viewOrderBook() {
        /*
        List<Order> orderBook = new ArrayList();
        List<BuyOrder> buyOrders = new ArrayList();
        List<SellOrder> sellOrders = new ArrayList();       
        BuyOrder ex1 = new BuyOrder(1, 10, new BigDecimal(150.00));
        SellOrder ex2 = new SellOrder(2, 20, new BigDecimal(120.00));
        BuyOrder ex3 = new BuyOrder(3, 15, new BigDecimal(130.00));
        SellOrder ex4 = new SellOrder(4, 12, new BigDecimal(128.00));
        BuyOrder ex5 = new BuyOrder(5, 18, new BigDecimal(140.00));
        SellOrder ex6 = new SellOrder(6, 11, new BigDecimal(145.00));       
        orderBook.add(ex1);
        orderBook.add(ex2);
        orderBook.add(ex3);
        orderBook.add(ex4);
        orderBook.add(ex5);
        orderBook.add(ex6);        
        buyOrders.add(ex1);
        buyOrders.add(ex3);
        buyOrders.add(ex5);        
        sellOrders.add(ex2);
        sellOrders.add(ex4);
        sellOrders.add(ex6);
        */
        view.displayOrderBook(orderBook);
    }

    private void matchAllOrders() {
        
    }

    private void matchTrade() {
        /*
        Trade ex1 = new Trade(1);
        ex1.setPrice(new BigDecimal(150));
        ex1.setTime(LocalTime.of(12, 32, 45));
        ex1.setQuantity(new BigDecimal(100));    
        Trade ex2 = new Trade(2);
        ex2.setPrice(new BigDecimal(120));
        ex2.setTime(LocalTime.of(18, 15, 58));
        ex2.setQuantity(new BigDecimal(110));    
        Trade ex3 = new Trade(3);
        ex3.setPrice(new BigDecimal(133));
        ex3.setTime(LocalTime.of(1, 40, 01));
        ex3.setQuantity(new BigDecimal(110)); 
        ArrayList<Trade> trades = new ArrayList();  
        trades.add(ex1);
        trades.add(ex2);
        trades.add(ex3);
        */
        if(trades.size() == 0) {
            view.orderBookIsEmpty();
        } else {
            view.matchID(trades);
        }
    }

    private void unknownCommand() {
        view.unknownCommand();
    }

    private void exitMessage() {
        view.exitMessage();
    }

    private void viewAllTrades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void viewOrderBookStats() {
        /*
        List<BuyOrder> buyOrders = new ArrayList();
        List<SellOrder> sellOrders = new ArrayList();
        
        BuyOrder ex1 = new BuyOrder(1, 10, new BigDecimal(150.00));
        SellOrder ex2 = new SellOrder(2, 20, new BigDecimal(120.00));
        BuyOrder ex3 = new BuyOrder(3, 15, new BigDecimal(130.00));
        SellOrder ex4 = new SellOrder(4, 12, new BigDecimal(128.00));
        BuyOrder ex5 = new BuyOrder(5, 18, new BigDecimal(140.00));
        SellOrder ex6 = new SellOrder(6, 11, new BigDecimal(145.00));
        
        buyOrders.add(ex1);
        buyOrders.add(ex3);
        buyOrders.add(ex5);
        
        sellOrders.add(ex2);
        sellOrders.add(ex4);
        sellOrders.add(ex6);
        */
        view.displayStats(sellOrders, buyOrders);
    }

    private void matchOrder() {
        /*
        Trade currentTrade = new Trade(1001);
        currentTrade.setPrice(new BigDecimal(133));
        currentTrade.setTime(LocalTime.of(1, 40, 01));
        currentTrade.setQuantity(new BigDecimal(110));
        */
        if (currentTrade != null) {
            view.tradeSuccess(currentTrade.getNumberID());
        } else {
            view.tradeFailure();
        }
    }
}

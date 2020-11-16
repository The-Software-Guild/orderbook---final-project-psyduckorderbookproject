package com.sg.psyduckorderbook.controller;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.Order;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.ui.PsyduckOrderBookView;
import java.math.BigDecimal;
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
                    matchOneOrder();
                    break;
                //Not able to do until I know the logic of it
                case 3:     //Match All Orders
                    matchAllOrders();
                    break;
                case 4:     //View particular trade
                    viewTradeID();
                    break;
                case 5:     //Ending the program
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
        //Temporary
        List<Order> orderBook = new ArrayList();
        
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
        
        view.displayOrderBook(orderBook);
    }

    private void matchOneOrder() {
        //Gonna change it once he figures out match One Order
        BuyOrder ex1 = new BuyOrder(1, 10, new BigDecimal(150.00));
        SellOrder ex2 = new SellOrder(2, 20, new BigDecimal(120.00));
        
        view.matchOrder(ex1, ex2);
    }

    private void matchAllOrders() {
        
    }

    private void viewTradeID() {
        List<Order> orderBook = new ArrayList();
        
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

        if(orderBook.size() == 0) {
            view.orderBookIsEmpty();
        }
        view.matchID(orderBook);
    }

    private void unknownCommand() {
        view.unknownCommand();
    }

    private void exitMessage() {
        view.exitMessage();
    }
}

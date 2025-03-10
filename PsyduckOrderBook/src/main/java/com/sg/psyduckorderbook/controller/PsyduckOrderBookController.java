package com.sg.psyduckorderbook.controller;

import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.Order;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import com.sg.psyduckorderbook.service.PsyduckOrderBookIsEmpty;
import com.sg.psyduckorderbook.service.PsyduckOrderBookServiceLayer;
import com.sg.psyduckorderbook.service.PsyduckOrderBookServiceLayerImpl;
import com.sg.psyduckorderbook.ui.PsyduckOrderBookView;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PsyduckOrderBookController {
    
    private PsyduckOrderBookView view;
    private PsyduckOrderBookServiceLayer service;
    
    public PsyduckOrderBookController (PsyduckOrderBookView view, PsyduckOrderBookServiceLayer service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() throws PsyduckOrderBookPersistenceException{
        
        boolean keepGoing = true;
        
        String read = takeInput();
        
        if (read.equals("n")) {
            try {
                service.load();
            } catch (PsyduckOrderBookPersistenceException e){
                keepGoing = false;
                view.loadError();
            }
        } else {
            int choice = decideFile();
                
            if(choice == 1) {
                String fileName = getFile();
                try {
                    service.loadFile(fileName);
                } catch (PsyduckOrderBookPersistenceException e){
                    keepGoing = false;
                    view.loadError();
                }
            } else if (choice == 2) {
                String fixName = getFIXname();
                try {
                    service.loadFIXFile(fixName);
                } catch (PsyduckOrderBookPersistenceException e){
                    keepGoing = false;
                    view.loadError();
                }
            }
        }
        
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
        List<ArrayList> orderBook = service.getOrderBook();
        if(!orderBook.get(1).isEmpty())
            view.displayOrderBook(orderBook);
        else
            view.emptyOrderBook();
    }

    private void matchAllOrders() {
        view.load();
        ArrayList<Trade> output = service.matchAllOrders();
        
        for(Trade success: output) {
            view.tradeSuccess(success.getNumberID(), success.getTime());
        }
        view.matchAllBanner();
    }

    private void matchTrade() {
        ArrayList<Trade> trades = service.getTrades();
        
        if(trades.size() == 0) {
            view.orderBookIsEmpty();
        } else {
            view.matchID(trades);
        } 
    }

    private void unknownCommand() {
        view.unknownCommand();
    }

    private void exitMessage() throws PsyduckOrderBookPersistenceException{
        service.close();
        view.exitMessage();
    }

    private void viewAllTrades() {
        ArrayList <Trade> trades = service.getTrades();
        if(!trades.isEmpty())
            view.allTrades(trades);
        else
            view.noTrades();
    }

    private void viewOrderBookStats() {
        ArrayList<BuyOrder> buyOrders = service.getBuyOrders();
        ArrayList<SellOrder> sellOrders = service.getSellOrders();
        
        if(buyOrders.isEmpty() || sellOrders.isEmpty())
            view.emptyOrderBook();
        else
            view.displayStats(sellOrders, buyOrders);
    }

    private void matchOrder() {
        Trade currentTrade = null;
        try {
            currentTrade = service.match();
            view.tradeSuccess(currentTrade.getNumberID(), currentTrade.getTime());
        } catch (PsyduckOrderBookIsEmpty ex) {
            view.orderBookIsEmpty();
        }
    }

    private String takeInput() {
        return view.takeInput();
    }

    private String getFile() {
        return view.getFile();
    }

    private int decideFile() {
        return view.decideFile();
    }

    private String getFIXname() {
        return view.getFIXName();
    }
}
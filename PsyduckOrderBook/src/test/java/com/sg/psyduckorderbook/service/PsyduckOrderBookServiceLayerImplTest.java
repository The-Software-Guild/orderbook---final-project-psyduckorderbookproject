package com.sg.psyduckorderbook.service;

import com.sg.psyduckorderbook.dao.PsyduckOrderBookDao;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookDaoFileImpl;
import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PsyduckOrderBookServiceLayerImplTest {
    
    PsyduckOrderBookServiceLayer service;
    
    public PsyduckOrderBookServiceLayerImplTest() {
        //PsyduckOrderBookDao dao = new PsyduckOrderDaoStubImpl();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        service = ctx.getBean("serviceLayer", PsyduckOrderBookServiceLayer.class);
    }

    @Test
    public void testLoad() throws Exception {
        ArrayList<BuyOrder> buyOrders = service.getBuyOrders();
        ArrayList<SellOrder> sellOrders = service.getSellOrders();
        
        assertFalse(buyOrders.isEmpty());
        assertFalse(sellOrders.isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void testMatch() throws Exception {
       Trade result = service.match();
       Trade expected = new Trade(1, result.getTime(), 
               new BigDecimal(190), new BigDecimal(42));
       assertEquals(result.getNumberID(), expected.getNumberID());
       assertEquals(result.getPrice(), expected.getPrice());
       assertEquals(result.getQuantity(), expected.getQuantity());
    }
    
    @org.junit.jupiter.api.Test
    public void testMatchAllOrders() throws PsyduckOrderBookIsEmpty {
        ArrayList<Trade> result = service.matchAllOrders();
        Trade ex1 = new Trade(1, LocalDateTime.now(), 
               new BigDecimal(190), new BigDecimal(42));
        ArrayList<Trade> expected = new ArrayList<>();
        expected.add(ex1);
        
        for(int i = 0; i < result.size(); i++) {
            assertEquals(expected.get(i).getNumberID(), result.get(i).getNumberID());
            assertEquals(expected.get(i).getPrice(), result.get(i).getPrice());
            assertEquals(expected.get(i).getQuantity(), expected.get(i).getQuantity());
        }
    }
    
    @org.junit.jupiter.api.Test
    public void testIsEmpty() {
        boolean empty = service.isEmpty();
        assertFalse(empty);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetOrderBook() {
        ArrayList<ArrayList> orderBook = service.getOrderBook();
        int expectedSize = 2;
        int sizeResult = orderBook.size();
        boolean buyEmpty = false;
        boolean sellEmpty = false; 
        if(orderBook.get(0).isEmpty())
            buyEmpty = true;
        if(orderBook.get(1).isEmpty())
            sellEmpty = true; 
        assertEquals(expectedSize, sizeResult);
        assertFalse(buyEmpty);
        assertFalse(sellEmpty);
    }

    @org.junit.jupiter.api.Test
    public void testGetSellOrders() {
        boolean empty = false;
        ArrayList<SellOrder> sellOrders = service.getSellOrders();
        if(sellOrders.isEmpty())
            empty = true;   
        assertFalse(empty);
    }

    @org.junit.jupiter.api.Test
    public void testGetBuyOrders() {
        boolean empty = false;
        ArrayList<BuyOrder> buyOrders = service.getBuyOrders();
        System.out.println(buyOrders.isEmpty());
        if(buyOrders.isEmpty())
            empty = true;   
        assertFalse(empty);
    }
    
    @org.junit.jupiter.api.Test
    public void testGetTrades() {
        boolean empty = false;
        ArrayList<Trade> trade = service.getTrades();
        if(trade.isEmpty())
            empty = true; 
        assertFalse(empty);
    }
}
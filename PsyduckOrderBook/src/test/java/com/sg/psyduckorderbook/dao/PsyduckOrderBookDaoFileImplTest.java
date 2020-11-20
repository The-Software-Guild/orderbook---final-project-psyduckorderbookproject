package com.sg.psyduckorderbook.dao;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import com.sg.psyduckorderbook.service.PsyduckOrderBookIsEmpty;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PsyduckOrderBookDaoFileImplTest {
    
    PsyduckOrderBookDaoFileImpl localDao;
    
    public PsyduckOrderBookDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        localDao = new PsyduckOrderBookDaoFileImpl();
        localDao.load();
        
    }
    
    @AfterEach
    public void tearDown() {
        localDao.close();
    }

    /**
     * Test of load method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
        //PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        //instance.load();
        ArrayList<ArrayList> myOrderBook = localDao.getOrderBook();
        assertNotNull(myOrderBook.get(0));
        assertNotNull(myOrderBook.get(1));
        assertEquals(1000, myOrderBook.get(0).size());
        assertEquals(1000, myOrderBook.get(1).size());
    }


    @Test
    public void testMatch() {
        System.out.println("match");
        ArrayList<BuyOrder> buyOrders = localDao.getBuyOrders();
        BuyOrder matchBuyOrder = buyOrders.get(buyOrders.size() - 1);
        ArrayList<SellOrder> sellOrders = localDao.getSellOrders();
        SellOrder matchSellOrder = sellOrders.get(sellOrders.size() - 1);
        Trade result = localDao.match();
        if (matchBuyOrder.getQuantity().compareTo(matchSellOrder.getQuantity()) >= 0 ){
            assertEquals(matchBuyOrder.getQuantity(),result.getQuantity());
        }else{
            assertEquals(matchSellOrder.getQuantity(),result.getQuantity());
        }
        assertEquals(matchBuyOrder.getPrice(), result.getPrice());
        
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of matchAllOrders method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testMatchAllOrders() {
        System.out.println("matchAllOrders");
        ArrayList<Trade> completed = localDao.matchAllOrders();
        assertFalse(completed.isEmpty());
        assertNotNull(!completed.isEmpty());
    }

    /**
     * Test of addBuyOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testAddBuyOrder() {
        System.out.println("addBuyOrder");
        BuyOrder newBuyOrder = new BuyOrder(2001, new BigDecimal("30"),new BigDecimal("190.78"));
        localDao.addBuyOrder(newBuyOrder);
        assertEquals(1001, localDao.getBuyOrders().size());
        assertEquals(newBuyOrder, localDao.getBuyOrders().get(localDao.getBuyOrders().size() -1));
    }

    /**
     * Test of addSellOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testAddSellOrder() {
        System.out.println("addSellOrder");
        SellOrder newSellOrder = new SellOrder(2002, new BigDecimal("40"),new BigDecimal("190.24"));
        localDao.addSellOrder(newSellOrder);
        assertEquals(1001, localDao.getSellOrders().size());
        assertEquals(newSellOrder, localDao.getSellOrders().get(localDao.getSellOrders().size() -1));
    }

    /**
     * Test of fillFullBuyOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillFullBuyOrder() {
        System.out.println("fillFullBuyOrder");
        BuyOrder myBuyOrder = localDao.getBuyOrders().get(0);
        
        localDao.fillFullBuyOrder(myBuyOrder);
        assertEquals(999, localDao.getBuyOrders().size());
    }

    /**
     * Test of fillFullSellOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillFullSellOrder() {
        System.out.println("fillFullSellOrder");
        SellOrder mySellOrder = localDao.getSellOrders().get(0);
        
        localDao.fillFullSellOrder(mySellOrder);
        assertEquals(999, localDao.getSellOrders().size());
    }

    /**
     * Test of fillPartialBuyOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillPartialBuyOrder() {
        System.out.println("fillPartialBuyOrder");
        BuyOrder myBuyOrder = localDao.getBuyOrders().get(0);
        BigDecimal buyQuantity = myBuyOrder.getQuantity();
        
        SellOrder mySellOrder = localDao.getSellOrders().get(0);
        BigDecimal sellQuantity = mySellOrder.getQuantity();
        int i = 1;
        while (buyQuantity.compareTo(sellQuantity) <= 0){
            mySellOrder = localDao.getSellOrders().get(i);
            sellQuantity = mySellOrder.getQuantity();
            i++;
        }
        localDao.fillPartialBuyOrder(myBuyOrder, mySellOrder);
        assertNotNull(localDao.getBuyOrders().get(0));
        assertEquals(buyQuantity.subtract(sellQuantity), myBuyOrder.getQuantity());
    }

    /**
     * Test of fillPartialSellOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillPartialSellOrder() {
        System.out.println("fillPartialSellOrder");
        SellOrder mySellOrder = localDao.getSellOrders().get(0);
        BigDecimal sellQuantity = mySellOrder.getQuantity();
        
        BuyOrder myBuyOrder = localDao.getBuyOrders().get(0);
        BigDecimal buyQuantity = myBuyOrder.getQuantity();
        int i = 1;
        while (sellQuantity.compareTo(buyQuantity) <= 0){
            myBuyOrder = localDao.getBuyOrders().get(i);
            buyQuantity = myBuyOrder.getQuantity();
            i++;
        }
        localDao.fillPartialSellOrder(myBuyOrder, mySellOrder);
        assertNotNull(localDao.getSellOrders().get(0));
        assertEquals(sellQuantity.subtract(buyQuantity), mySellOrder.getQuantity());
    }

    /**
     * Test of isEmpty method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty"); 
        assertFalse(localDao.isEmpty());
        localDao.matchAllOrders();
        assertTrue(localDao.isEmpty());
    }

    /**
     * Test of getBuyOrders method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetBuyOrders() {
        System.out.println("getBuyOrders");
        ArrayList<BuyOrder> myBuyOrders = localDao.getBuyOrders();
        assertEquals(1000, myBuyOrders.size());
    }

    /**
     * Test of getSellOrders method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetSellOrders() {
        System.out.println("getSellOrders");
        ArrayList<SellOrder> mySellOrders = localDao.getSellOrders();
        assertEquals(1000, mySellOrders.size());
    }

    /**
     * Test of getOrderBook method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetOrderBook() {
        System.out.println("getOrderBook");
        ArrayList<ArrayList> myOrderBook = localDao.getOrderBook();
        assertEquals(2, myOrderBook.size());
        assertEquals(1000, myOrderBook.get(0).size());
        assertEquals(1000, myOrderBook.get(1).size());
    }

    /**
     * Test of getTrades method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetTrades() {
        System.out.println("getTrades");
        ArrayList<Trade> myTrades = localDao.getTrades();
        assertEquals(0,myTrades.size());
        Trade myTrade = localDao.match();
        assertEquals(1, myTrades.size());
        assertEquals(myTrade, myTrades.get(0));
        
    }

}
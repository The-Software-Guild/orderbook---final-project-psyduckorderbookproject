package com.sg.psyduckorderbook.dao;

import com.sg.psyduckorderbook.dto.BuyOrder;
import com.sg.psyduckorderbook.dto.SellOrder;
import com.sg.psyduckorderbook.dto.Trade;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PsyduckOrderBookDaoFileImplTest {
    
    public PsyduckOrderBookDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of load method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.load();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testMatch() {
        System.out.println("match");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        Trade expResult = null;
        Trade result = instance.match();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of matchAllOrders method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testMatchAllOrders() {
        System.out.println("matchAllOrders");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.matchAllOrders();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBuyOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testAddBuyOrder() {
        System.out.println("addBuyOrder");
        BuyOrder newBuyOrder = null;
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.addBuyOrder(newBuyOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSellOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testAddSellOrder() {
        System.out.println("addSellOrder");
        SellOrder newSellOrder = null;
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.addSellOrder(newSellOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillFullBuyOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillFullBuyOrder() {
        System.out.println("fillFullBuyOrder");
        BuyOrder myBuyOrder = null;
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.fillFullBuyOrder(myBuyOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillFullSellOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillFullSellOrder() {
        System.out.println("fillFullSellOrder");
        SellOrder mySellOrder = null;
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.fillFullSellOrder(mySellOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillPartialBuyOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillPartialBuyOrder() {
        System.out.println("fillPartialBuyOrder");
        BuyOrder myBuyOrder = null;
        SellOrder mySellOrder = null;
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.fillPartialBuyOrder(myBuyOrder, mySellOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillPartialSellOrder method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testFillPartialSellOrder() {
        System.out.println("fillPartialSellOrder");
        BuyOrder myBuyOrder = null;
        SellOrder mySellOrder = null;
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.fillPartialSellOrder(myBuyOrder, mySellOrder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBuyOrders method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetBuyOrders() {
        System.out.println("getBuyOrders");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        ArrayList<BuyOrder> expResult = null;
        ArrayList<BuyOrder> result = instance.getBuyOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSellOrders method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetSellOrders() {
        System.out.println("getSellOrders");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        ArrayList<SellOrder> expResult = null;
        ArrayList<SellOrder> result = instance.getSellOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderBook method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetOrderBook() {
        System.out.println("getOrderBook");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        ArrayList<ArrayList> expResult = null;
        ArrayList<ArrayList> result = instance.getOrderBook();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrades method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testGetTrades() {
        System.out.println("getTrades");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        ArrayList<Trade> expResult = null;
        ArrayList<Trade> result = instance.getTrades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class PsyduckOrderBookDaoFileImpl.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        PsyduckOrderBookDaoFileImpl instance = new PsyduckOrderBookDaoFileImpl();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }  
}
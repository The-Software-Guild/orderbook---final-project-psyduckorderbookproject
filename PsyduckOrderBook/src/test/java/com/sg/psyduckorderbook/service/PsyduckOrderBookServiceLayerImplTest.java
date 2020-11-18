package com.sg.psyduckorderbook.service;

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

public class PsyduckOrderBookServiceLayerImplTest {
    
    public PsyduckOrderBookServiceLayerImplTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of load method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testLoad() throws Exception {
        System.out.println("load");
        PsyduckOrderBookServiceLayerImpl instance = null;
        instance.load();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of match method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testMatch() throws Exception {
        System.out.println("match");
        PsyduckOrderBookServiceLayerImpl instance = null;
        Trade expResult = null;
        Trade result = instance.match();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of matchAllOrders method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testMatchAllOrders() {
        System.out.println("matchAllOrders");
        PsyduckOrderBookServiceLayerImpl instance = null;
        instance.matchAllOrders();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        PsyduckOrderBookServiceLayerImpl instance = null;
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderBook method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetOrderBook() {
        System.out.println("getOrderBook");
        PsyduckOrderBookServiceLayerImpl instance = null;
        ArrayList<ArrayList> expResult = null;
        ArrayList<ArrayList> result = instance.getOrderBook();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSellOrders method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetSellOrders() {
        System.out.println("getSellOrders");
        PsyduckOrderBookServiceLayerImpl instance = null;
        ArrayList<SellOrder> expResult = null;
        ArrayList<SellOrder> result = instance.getSellOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBuyOrders method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetBuyOrders() {
        System.out.println("getBuyOrders");
        PsyduckOrderBookServiceLayerImpl instance = null;
        ArrayList<BuyOrder> expResult = null;
        ArrayList<BuyOrder> result = instance.getBuyOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrades method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetTrades() {
        System.out.println("getTrades");
        PsyduckOrderBookServiceLayerImpl instance = null;
        ArrayList<Trade> expResult = null;
        ArrayList<Trade> result = instance.getTrades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class PsyduckOrderBookServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testClose() {
        System.out.println("close");
        PsyduckOrderBookServiceLayerImpl instance = null;
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

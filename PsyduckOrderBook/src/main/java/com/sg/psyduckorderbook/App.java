package com.sg.psyduckorderbook;

import com.sg.psyduckorderbook.controller.PsyduckOrderBookController;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookDao;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookDaoFileImpl;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import com.sg.psyduckorderbook.service.PsyduckOrderBookServiceLayer;
import com.sg.psyduckorderbook.service.PsyduckOrderBookServiceLayerImpl;
import com.sg.psyduckorderbook.ui.PsyduckOrderBookView;
import com.sg.psyduckorderbook.ui.UserIO;
import com.sg.psyduckorderbook.ui.UserIOConsoleImpl;

public class App {
    
    public static void main(String[] args) throws PsyduckOrderBookPersistenceException { 
        UserIO myIO = new UserIOConsoleImpl();
        PsyduckOrderBookDao dao = new PsyduckOrderBookDaoFileImpl();
        PsyduckOrderBookView myView = new PsyduckOrderBookView(myIO);
        PsyduckOrderBookServiceLayer myService = new PsyduckOrderBookServiceLayerImpl(dao);
        PsyduckOrderBookController controller = new PsyduckOrderBookController(myView, myService);
        controller.run();
    }
}

package com.sg.psyduckorderbook;

import com.sg.psyduckorderbook.controller.PsyduckOrderBookController;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import com.sg.psyduckorderbook.ui.PsyduckOrderBookView;
import com.sg.psyduckorderbook.ui.UserIO;
import com.sg.psyduckorderbook.ui.UserIOConsoleImpl;

public class App {
    
    public static void main(String[] args) throws PsyduckOrderBookPersistenceException { 
        UserIO myIO = new UserIOConsoleImpl();
        PsyduckOrderBookView myView = new PsyduckOrderBookView(myIO);
        PsyduckOrderBookController controller = new PsyduckOrderBookController(myView);
        controller.run();
    }
}

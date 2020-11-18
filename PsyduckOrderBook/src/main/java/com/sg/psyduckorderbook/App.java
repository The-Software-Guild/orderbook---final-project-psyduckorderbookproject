package com.sg.psyduckorderbook;

import com.sg.psyduckorderbook.controller.PsyduckOrderBookController;
import com.sg.psyduckorderbook.dao.PsyduckOrderBookPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    
    public static void main(String[] args) throws PsyduckOrderBookPersistenceException { 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        PsyduckOrderBookController controller = ctx.getBean("controller", PsyduckOrderBookController.class);
        controller.run();
    }
}

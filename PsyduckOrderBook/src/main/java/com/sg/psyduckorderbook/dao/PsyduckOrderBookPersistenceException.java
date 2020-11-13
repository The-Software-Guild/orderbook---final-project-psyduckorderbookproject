package com.sg.psyduckorderbook.dao;

public class PsyduckOrderBookPersistenceException extends Exception {
    
    public PsyduckOrderBookPersistenceException(String message) {
        super(message);
    }
    
    public PsyduckOrderBookPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }  
}

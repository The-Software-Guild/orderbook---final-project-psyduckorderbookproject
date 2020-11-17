package com.sg.psyduckorderbook.service;

public class PsyduckOrderBookDuplicateException extends Exception{
    
    public PsyduckOrderBookDuplicateException (String message) {
        super(message);
    }
    
    public PsyduckOrderBookDuplicateException (String message, Throwable cause) {
        super(message, cause);
    }
}

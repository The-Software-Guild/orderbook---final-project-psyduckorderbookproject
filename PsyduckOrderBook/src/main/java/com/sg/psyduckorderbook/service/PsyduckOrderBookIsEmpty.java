package com.sg.psyduckorderbook.service;

public class PsyduckOrderBookIsEmpty extends Exception{
    
    public PsyduckOrderBookIsEmpty (String message) {
        super(message);
    }
    
    public PsyduckOrderBookIsEmpty (String message, Throwable cause) {
        super(message, cause);
    }
}

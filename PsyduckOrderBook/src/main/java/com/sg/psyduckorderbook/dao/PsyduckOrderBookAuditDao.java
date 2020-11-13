package com.sg.psyduckorderbook.dao;

public interface PsyduckOrderBookAuditDao {
    
    public void writeAuditEntry(String entry) throws PsyduckOrderBookPersistenceException;
}

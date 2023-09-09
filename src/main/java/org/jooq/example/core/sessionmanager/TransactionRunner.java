package org.jooq.example.core.sessionmanager;

public interface TransactionRunner {
    
    <T> T doInTransaction(TransactionAction<T> action);
}

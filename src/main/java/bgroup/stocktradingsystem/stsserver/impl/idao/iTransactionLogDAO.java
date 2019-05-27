package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;

import java.util.List;

public interface iTransactionLogDAO  {
    void insert(TransactionLog log);
    void delete(int id);
    void update(TransactionLog log);
    List<TransactionLog> select(String cond);
}

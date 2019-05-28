package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.TransactionLogDAO;
import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionLogService {
    @Autowired
    TransactionLogDAO transactionLogDAO;


    public void addLog(TransactionLog log) {
        transactionLogDAO.insert(log);
    }
}

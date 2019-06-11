package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.TransactionLogDAO;
import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionLogService {
    final private TransactionLogDAO transactionLogDAO;

    @Autowired
    public TransactionLogService(TransactionLogDAO transactionLogDAO) {
        this.transactionLogDAO = transactionLogDAO;
    }

    public void addLog(TransactionLog log) {
        transactionLogDAO.insert(log);
    }

    public List<TransactionLog> fetchLog(int fundId) {
        return transactionLogDAO.select("fund_id=" + fundId);
    }
}

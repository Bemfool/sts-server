package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.TradeRecordDAO;
import bgroup.stocktradingsystem.stsserver.domain.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeRecordService {
    final private TradeRecordDAO tradeRecordDAO;

    @Autowired
    public TradeRecordService(TradeRecordDAO tradeRecordDAO) {
        this.tradeRecordDAO = tradeRecordDAO;
    }

    public List<TradeRecord> fetchRecord(int fundId) {
        return tradeRecordDAO.select("fund_id = " + fundId);
    }
}

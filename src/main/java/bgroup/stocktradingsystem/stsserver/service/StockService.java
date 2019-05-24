package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.StockDAO;
import bgroup.stocktradingsystem.stsserver.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockDAO stockDAO;

    public List<Stock> fetchAllStock() {
        return stockDAO.select("");
    }
}

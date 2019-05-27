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

    public Stock fetchCertainStock(String code) {
        return stockDAO.select("stock_code = '" + code + "'").get(0);
    }

    public void updateStock(Stock stock) {
        stockDAO.update(stock);
    }

    public void updateStockList(List<Stock> stocks) {
        for (Stock stock : stocks)
            stockDAO.update(stock);
    }
}

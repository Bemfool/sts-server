package bgroup.stocktradingsystem.stsserver.impl;

import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.domain.Stock;

import java.util.List;

public interface iStockDAO {
    public void insert(Stock stock);
    public void update(Stock stock);
    public void delete(String code);
    public List<Stock> select(String cond);
}

package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.domain.Stock;

import java.util.List;

public interface iStockDAO {
    void insert(Stock stock);
    void update(Stock stock);
    void delete(String code);
    List<Stock> select(String cond);
}

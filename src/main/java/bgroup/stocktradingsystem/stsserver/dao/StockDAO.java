package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.impl.iStockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StockDAO implements iStockDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Stock stock) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Stock stock) {

    }

    @Override
    public List<Stock> select(String cond) {
        return jdbcTemplate.query("SELECT * FROM stock", (rs, rowNum) -> {
            Stock stock = new Stock();
            stock.setStockCode(rs.getString("stock_code"));
            return stock;
        });

//        List<Stock> stocks;
//        if (cond.isEmpty()){
//            stocks = jdbcTemplate.query("SELECT * FROM stock",
//                    (resultSet, i) -> {
//                        Stock stock = new Stock();
//                        stock.setStockCode(resultSet.getString("stock_code"));
////                        stock.setStockName(resultSet.getString("stock_name"));
////                        stock.setStockPrice(resultSet.getDouble("stock_price"));
////                        stock.setStockState(resultSet.getString("stock_state"));
////                        stock.setFloorPrice(resultSet.getDouble("floor_price"));
////                        stock.setCeilingPrice(resultSet.getDouble("ceiling_price"));
//                        return stock;
//                    });
//        return stocks;
//        }
//        else
//            return jdbcTemplate.query("SELECT * FROM stock WHERE " + cond,
//                    (resultSet, i) -> {
//                        Stock stock = new Stock();
//                        stock.setStockCode(resultSet.getString("stock_code"));
//                        stock.setStockName(resultSet.getString("stock_name"));
//                        stock.setStockPrice(resultSet.getDouble("stock_price"));
//                        stock.setStockState(resultSet.getString("stock_state"));
//                        stock.setFloorPrice(resultSet.getDouble("floor_price"));
//                        stock.setCeilingPrice(resultSet.getDouble("ceiling_price"));
//                        return stock;
//                    });
    }


}

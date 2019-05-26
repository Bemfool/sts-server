package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.impl.idao.iStockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StockDAO implements iStockDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Stock stock) {
        jdbcTemplate.update("INSERT INTO stock" +
                "(stock_code, stock_name, stock_price, stock_state, floor_price, ceiling_price)" +
                "VALUES(?, ?, ?, ?, ?, ?)", preparedStatement -> {
            preparedStatement.setString(1, stock.getStockCode());
            preparedStatement.setString(2,stock.getStockName() );
            preparedStatement.setDouble(3, stock.getStockPrice() );
            preparedStatement.setString(4, stock.getStockState());
            preparedStatement.setDouble(5, stock.getFloorPrice());
            preparedStatement.setDouble(6,stock.getCeilingPrice());
        });
    }

    @Override
    public void delete(String code) {
        jdbcTemplate.update("DELETE FROM stock WHERE stock_code = ?",
                preparedStatement -> { preparedStatement.setString(1, code);
        });
    }

    @Override
    public void update(Stock stock) {
        jdbcTemplate.update("UPDATE stock SET stock_name = ?," +
                "stock_price = ?," +
                "stock_state = ?," +
                "floor_price = ?," +
                "ceiling_price = ? WHERE stock_code = ?", preparedStatement -> {
                    preparedStatement.setString(1, stock.getStockName());
                    preparedStatement.setDouble(2, stock.getStockPrice() );
                    preparedStatement.setString(3, stock.getStockState());
                    preparedStatement.setDouble(4, stock.getFloorPrice() );
                    preparedStatement.setDouble(5, stock.getCeilingPrice());
                    preparedStatement.setString(6, stock.getStockCode());
                });
    }

    @Override
    public List<Stock> select(String cond) {
        List<Stock> stocks;
        if (cond.isEmpty()){
            stocks = jdbcTemplate.query("SELECT * FROM stock",
                    new StockMapper());
            return stocks;
        } else
            return jdbcTemplate.query("SELECT * FROM stock WHERE " + cond,
                    new StockMapper());
    }

    class StockMapper implements RowMapper<Stock> {
        @Override
        public Stock mapRow(ResultSet resultSet, int rowNum) throws  SQLException {
            Stock stock = new Stock();
            stock.setStockCode(resultSet.getString("stock_code"));
            stock.setStockName(resultSet.getString("stock_name"));
            stock.setStockPrice(resultSet.getDouble("stock_price"));
            stock.setStockState(resultSet.getString("stock_state"));
            stock.setFloorPrice(resultSet.getDouble("floor_price"));
            stock.setCeilingPrice(resultSet.getDouble("ceiling_price"));
            return stock;
        }
    }



}

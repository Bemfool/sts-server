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
        jdbcTemplate.update("INSERT INTO stock_new" +
                "(stock_code, stock_name, stock_price, stock_state, stock_limit, " +
                "closing_price, stock_amount, stock_total)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", preparedStatement -> {
            preparedStatement.setString(1, stock.getStockCode());
            preparedStatement.setString(2,stock.getStockName() );
            preparedStatement.setDouble(3, stock.getStockPrice() );
            preparedStatement.setString(4, stock.getStockState());
            preparedStatement.setDouble(5, stock.getStockLimit());
            preparedStatement.setDouble(6,stock.getClosingPrice());
            preparedStatement.setInt(7,stock.getStockAmount() );
            preparedStatement.setDouble(8,stock.getStockTotal() );
        });
    }

    @Override
    public void delete(String code) {
        jdbcTemplate.update("DELETE FROM stock_new WHERE stock_code = ?",
                preparedStatement -> { preparedStatement.setString(1, code);
                });
    }

    @Override
    public void update(Stock stock) {
        jdbcTemplate.update("UPDATE stock_new SET stock_name = ?," +
                "stock_price = ?," +
                "stock_state = ?," +
                "stock_limit = ?," +
                "closing_price = ?," +
                "stock_amount = ?," +
                "stock_total = ? WHERE stock_code = ?", preparedStatement -> {
            preparedStatement.setString(1, stock.getStockName());
            preparedStatement.setDouble(2, stock.getStockPrice() );
            preparedStatement.setString(3, stock.getStockState());
            preparedStatement.setDouble(4, stock.getStockLimit());
            preparedStatement.setDouble(5,stock.getClosingPrice());
            preparedStatement.setInt(6,stock.getStockAmount() );
            preparedStatement.setDouble(7,stock.getStockTotal() );
            preparedStatement.setString(8, stock.getStockCode());
        });
    }

    @Override
    public List<Stock> select(String cond) {
        List<Stock> stocks;
        if (cond.isEmpty()){
            stocks = jdbcTemplate.query("SELECT * FROM stock_new",
                    new StockMapper());
            return stocks;
        } else
            return jdbcTemplate.query("SELECT * FROM stock_new WHERE " + cond,
                    new StockMapper());
    }

    public List<Stock> selectStockFromSSRelation(int securitiesId) {
        return jdbcTemplate.query("SELECT stock_code, stock_name, stock_price," +
                "stock_state, stock_limit, closing_price, stock_amount, stock_total " +
                "FROM securities_stock NATURAL JOIN stock " +
                "WHERE securities_id = " + securitiesId, new StockMapper());
    }

    public int selectAmountFromSSRelation(int fundId, String stockCode) {
        return jdbcTemplate.query("SELECT amount " +
                "FROM fund_account NATURAL JOIN securities_stock " +
                "WHERE stock_code = '" + stockCode + "' AND fund_id = " + fundId,
                (resultSet, i) -> resultSet.getInt("amount")).get(0);
    }

    public void updateAmountFromSSRelation(int fundId, String stockCode, int stockCount) {
        jdbcTemplate.update("UPDATE fund_account NATURAL JOIN securities_stock " +
                "SET amount = amount - " + stockCount + " " +
                "WHERE fund_id = ? AND stock_code = ?", preparedStatement -> {
            preparedStatement.setInt(1,fundId );
            preparedStatement.setString(2, stockCode);
        });
    }


    class StockMapper implements RowMapper<Stock> {
        @Override
        public Stock mapRow(ResultSet resultSet, int rowNum) throws  SQLException {
            Stock stock = new Stock();
            stock.setStockCode(resultSet.getString("stock_code"));
            stock.setStockName(resultSet.getString("stock_name"));
            stock.setStockPrice(resultSet.getDouble("stock_price"));
            stock.setStockState(resultSet.getString("stock_state"));
            stock.setStockLimit(resultSet.getDouble("stock_limit"));
            stock.setClosingPrice(resultSet.getDouble("closing_price"));
            stock.setStockAmount(resultSet.getInt("stock_amount"));
            stock.setStockTotal(resultSet.getDouble("stock_total"));
            return stock;
        }
    }




}

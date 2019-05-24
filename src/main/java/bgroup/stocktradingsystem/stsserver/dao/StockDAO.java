package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.impl.iStockDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StockDAO implements iStockDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.query("SELECT * FROM stock WHERE " + cond,
                new StockDAO.StockRowMapper());
    }

    class StockRowMapper implements RowMapper<Stock> {
        @Override
        public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
            Stock stock = new Stock();
            stock.setStockCode(rs.getString("stock_code"));
            stock.setStockName(rs.getString("stock_name"));
            stock.setStockPrice(rs.getDouble("stock_price"));
            stock.setStockState(rs.getString("stock_state"));
            stock.setFloorPrice(rs.getDouble("floor_price"));
            stock.setCeilingPrice(rs.getDouble("ceiling_price"));
            return stock;
        }
    }
}

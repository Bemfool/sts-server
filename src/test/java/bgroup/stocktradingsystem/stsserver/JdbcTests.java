package bgroup.stocktradingsystem.stsserver;

import bgroup.stocktradingsystem.stsserver.dao.AdminAccountDAO;
import bgroup.stocktradingsystem.stsserver.dao.StockDAO;
import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTests {

    @Autowired
    StockService stockService;

//    @Resource
//    private JdbcTemplate jdbcTemplate;

//    @Test
//    public void queryFirstStockCode() {
//        List<Stock> list = jdbcTemplate.query("SELECT * FROM stock", (rs, rowNum) -> {
//            Stock stock = new Stock();
//            stock.setStockCode(rs.getString("stock_code"));
//            return stock;
//        });
//        System.out.println(list.get(0).getStockCode());
//    }

    @Test
    public void selectFirstStockCode() {
        List<Stock> list = stockService.fetchAllStock();
        System.out.println(list.get(0).getStockCode());
    }

}

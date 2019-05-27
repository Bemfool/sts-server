package bgroup.stocktradingsystem.stsserver;

import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.service.account.AdminAccountService;
import bgroup.stocktradingsystem.stsserver.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTests {

    @Autowired
    StockService stockService;

    @Autowired
    AdminAccountService adminAccountService;

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

    @Test
    public void insertNewAdmin() {
//        AdminAccount adminAccount = new AdminAccount("9999999999", "Maria", "000000");
//        adminAccountService.newAccount(adminAccount);
    }

}

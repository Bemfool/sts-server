package bgroup.stocktradingsystem.stsserver;

import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.domain.account.AdminAccount;
import bgroup.stocktradingsystem.stsserver.service.account.AdminAccountService;
import bgroup.stocktradingsystem.stsserver.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
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

    /**
     * 测试插入新管理员账户，抛出重复主键异常。
     *
     */
    @Test
    public void insertNewAdmin() {
        AdminAccount adminAccount = new AdminAccount("12345678", "许三多", "000000");
        try {
            adminAccountService.newAccount(adminAccount);
        } catch(DataAccessException e) {
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception.toString());
        }

    }

}

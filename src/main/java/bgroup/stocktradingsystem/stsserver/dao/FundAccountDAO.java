package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.FundAccount;
import bgroup.stocktradingsystem.stsserver.impl.iFundAccountDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

public class FundAccountDAO implements iFundAccountDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean insert(FundAccount account) {
        return true;
    }

    @Override
    public Boolean delete(String id) {
        return true;
    }

    @Override
    public Boolean update(FundAccount account) {
        return true;
    }

    @Override
    public List<FundAccount> select(String cond) {
        return null;
    }
}

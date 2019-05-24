package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.FundAccount;
import bgroup.stocktradingsystem.stsserver.impl.iFundAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class FundAccountDAO implements iFundAccountDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

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

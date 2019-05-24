package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.SecuritiesAccount;
import bgroup.stocktradingsystem.stsserver.impl.iSecuritiesAccountDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

public class SecuritiesAccountDAO implements iSecuritiesAccountDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean insert(SecuritiesAccount account) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Boolean update(SecuritiesAccount account) {
        return null;
    }

    @Override
    public List<SecuritiesAccount> select(String cond) {
        return null;
    }
}

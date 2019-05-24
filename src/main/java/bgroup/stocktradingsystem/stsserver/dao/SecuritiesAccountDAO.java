package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.SecuritiesAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.iSecuritiesAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecuritiesAccountDAO implements iSecuritiesAccountDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

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

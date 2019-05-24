package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.impl.iIndexDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

public class IndexDAO implements iIndexDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean insert(Index index) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Boolean update(Index index) {
        return null;
    }

    @Override
    public List<Index> select(String cond) {
        return null;
    }
}

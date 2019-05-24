package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.impl.idao.iIndexDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IndexDAO implements iIndexDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Index index) {


    }

    @Override
    public void delete(String id) {


    }

    @Override
    public void update(Index index) {


    }

    @Override
    public List<Index> select(String cond) {
        return null;
    }
}

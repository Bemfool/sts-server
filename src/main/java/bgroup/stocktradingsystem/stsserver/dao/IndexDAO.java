package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.impl.idao.iIndexDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IndexDAO implements iIndexDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Index index) {
        jdbcTemplate.update("INSERT INTO idx" +
                "(index_code, index_name, index_price)" +
                "VALUES(?, ?, ?)", preparedStatement -> {
            preparedStatement.setString(1, index.getIndexCode());
            preparedStatement.setString(2,index.getIndexName() );
            preparedStatement.setDouble(3, index.getIndexPrice() );
        });
    }

    @Override
    public void delete(String code) {
        jdbcTemplate.update("DELETE FROM idx WHERE index_code = ?",
                preparedStatement -> { preparedStatement.setString(1, code);
                });
    }

    @Override
    public void update(Index index) {
        jdbcTemplate.update("UPDATE idx SET index_name = ?," +
                "index_price = ?," +
                "index_state = ? WHERE index_code = ?", preparedStatement -> {
            preparedStatement.setString(1, index.getIndexName());
            preparedStatement.setDouble(2, index.getIndexPrice() );
            preparedStatement.setString(3, index.getIndexCode());
        });
    }

    @Override
    public List<Index> select(String cond) {
            List<Index> indices;
            if (cond.isEmpty()){
                indices = jdbcTemplate.query("SELECT * FROM idx",
                        new IndexMapper());
                return indices;
            } else
                return jdbcTemplate.query("SELECT * FROM idx WHERE " + cond,
                        new IndexMapper());
    }

    class IndexMapper implements RowMapper<Index> {
        @Override
        public Index mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Index index = new Index();
            index.setIndexCode(resultSet.getString("index_code"));
            index.setIndexName(resultSet.getString("index_name"));
            index.setIndexPrice(resultSet.getDouble("index_price"));
            return index;
        }
    }
}

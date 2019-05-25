package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.FundAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.iFundAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FundAccountDAO implements iFundAccountDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(FundAccount account) {
        jdbcTemplate.update("INSERT INTO fund_account" +
                "(id, password) VALUES(?, ?)", preparedStatement -> {
            preparedStatement.setString(1, account.getId() );
            preparedStatement.setString(2, account.getPassword());
        });
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM fund_account WHERE id = ?", preparedStatement -> {
            preparedStatement.setString(1, id);
        });
    }

    @Override
    public void update(FundAccount account) {
        jdbcTemplate.update("UPDATE fund_account SET password = ? WHERE id = ?", preparedStatement -> {
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getId());
        });
    }

    @Override
    public List<FundAccount> select(String cond) {
        if(cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM fund_account", new FundAccountMapper());
        else
            return jdbcTemplate.query("SELECT * FROM fund_account WHERE " + cond, new FundAccountMapper());
    }

    class FundAccountMapper implements RowMapper<FundAccount> {
        @Override
        public FundAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            FundAccount account = new FundAccount();
            account.setPassword(resultSet.getString("password"));
            account.setId(resultSet.getString("id"));
            return account;
        }
    }

}

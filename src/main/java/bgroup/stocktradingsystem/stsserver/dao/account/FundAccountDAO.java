package bgroup.stocktradingsystem.stsserver.dao.account;

import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.account.iFundAccountDAO;
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
                "(fund_id, securities_id, password, balance, interest, state) " +
                "VALUES(?, ?, ?, ?, ?, ?)", preparedStatement -> {
            preparedStatement.setInt(1, account.getFundId() );
            preparedStatement.setInt(2, account.getSecuritiesId());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setDouble(4, account.getBalance());
            preparedStatement.setDouble(5, account.getInterest());
            preparedStatement.setBoolean(6,account.isState() );
        });
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM fund_account WHERE fund_id = ?", preparedStatement -> {
            preparedStatement.setInt(1, id);
        });
    }

    @Override
    public void update(FundAccount account) {
        jdbcTemplate.update("UPDATE fund_account SET " +
                "securities_id = ?, " +
                "password = ?," +
                "balance = ?," +
                "interest = ?," +
                "state = ? WHERE id = ?", preparedStatement -> {
            preparedStatement.setInt(1,account.getSecuritiesId());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setDouble(4,account.getInterest() );
            preparedStatement.setBoolean(5,account.isState() );
            preparedStatement.setInt(6, account.getFundId());
        });
    }

    @Override
    public List<FundAccount> select(String cond) {
        if(cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM fund_account", new FundAccountMapper());
        else
            return jdbcTemplate.query("SELECT * FROM fund_account WHERE " + cond,
                    new FundAccountMapper());
    }

    @Override
    public int maxId() {
        return jdbcTemplate.query("SELECT MAX(fund_id) maxid",
                (resultSet, rowNum) -> resultSet.getInt("maxid")).get(0);
    }

    class FundAccountMapper implements RowMapper<FundAccount> {
        @Override
        public FundAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            FundAccount account = new FundAccount();
            account.setFundId(resultSet.getInt("fund_id"));
            account.setPassword(resultSet.getString("password"));
            account.setSecuritiesId(resultSet.getInt("securities_id"));
            account.setBalance(resultSet.getDouble("balance"));
            account.setInterest(resultSet.getDouble("interest"));
            account.setState(resultSet.getBoolean("state"));
            return account;
        }
    }

}

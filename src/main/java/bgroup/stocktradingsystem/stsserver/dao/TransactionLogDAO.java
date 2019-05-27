package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;
import bgroup.stocktradingsystem.stsserver.impl.idao.iTransactionLogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionLogDAO implements iTransactionLogDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(TransactionLog log) {
        // TODO 设置auto-increment
        jdbcTemplate.update("INSERT INTO transaction_log" +
                "(fund_id, action_time, change_amount, comment)" +
                "VALUES(?, ?, ?, ?)", preparedStatement -> {
            preparedStatement.setInt(1,log.getFundId() );
            preparedStatement.setDate(2, log.getActionTime() );
            preparedStatement.setDouble(3, log.getChangeAmount());
            preparedStatement.setString(4, log.getComment());
        });
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM transaction_log WHERE action_id = ?",
                preparedStatement -> preparedStatement.setInt(1, id));
    }

    @Override
    public void update(TransactionLog log) {
        jdbcTemplate.update("UPDATE transaction_log SET fund_id = ?," +
                "action_time = ?," +
                "change_amount = ?," +
                "comment = ? WHERE action_id = ?", preparedStatement -> {
            preparedStatement.setInt(1, log.getFundId());
            preparedStatement.setDate(2, log.getActionTime() );
            preparedStatement.setDouble(3, log.getChangeAmount());
            preparedStatement.setString(4, log.getComment() );
            preparedStatement.setInt(5, log.getActionId());
        });
    }

    @Override
    public List<TransactionLog> select(String cond) {
        if (cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM transaction_log",
                    new LogMapper());
        else
            return jdbcTemplate.query("SELECT * FROM transaction_log WHERE " + cond,
                    new LogMapper());
    }

    class LogMapper implements RowMapper<TransactionLog> {
        @Override
        public TransactionLog mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                TransactionLog log = new TransactionLog();
                log.setActionId(resultSet.getInt("action_id"));
                log.setActionTime(resultSet.getDate("action_time"));
                log.setChangeAmount(resultSet.getDouble("change_amount"));
                log.setFundId(resultSet.getInt(("fund_id")));
                log.setComment(resultSet.getString("comment"));
                return log;
        }
    }
}

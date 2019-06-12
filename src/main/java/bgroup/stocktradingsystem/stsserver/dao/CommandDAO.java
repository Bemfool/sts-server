package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.Command;
import bgroup.stocktradingsystem.stsserver.domain.Index;
import bgroup.stocktradingsystem.stsserver.impl.idao.iCommandDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CommandDAO用于管理Command数据库(后被每只股票自建一个表取代)。
 *
 */
@Repository
public class CommandDAO implements iCommandDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(Command command) {
        jdbcTemplate.update("INSERT INTO command" +
                "(time, fund_id, command_type, stock_code, stock_count, stock_price)" +
                "VALUES(?, ?, ?, ?, ?, ?)", preparedStatement -> {
            preparedStatement.setDate(1, command.getTime());
            preparedStatement.setInt(2, command.getFundId());
            preparedStatement.setBoolean(3, command.isCommandType());
            preparedStatement.setString(4, command.getStockCode());
            preparedStatement.setInt(5, command.getStockCount());
            preparedStatement.setDouble(6, command.getStockPrice());
        });
    }

    @Override
    public void delete(Command command) {
        jdbcTemplate.update("DELETE FROM command WHERE fund_id = ? " +
                        "AND time = ? " , preparedStatement -> {
                preparedStatement.setInt(1, command.getFundId());
                preparedStatement.setDate(2, command.getTime());
                });
    }

    @Override
    public List<Command> select(String cond) {
        List<Command> indices;
        if (cond.isEmpty()){
            indices = jdbcTemplate.query("SELECT * FROM command",
                    new CommandMapper());
            return indices;
        } else
            return jdbcTemplate.query("SELECT * FROM command WHERE " + cond,
                    new CommandMapper());
    }

    class CommandMapper implements RowMapper<Command> {
        @Override
        public Command mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Command command = new Command();
            command.setTime(resultSet.getDate("time"));
            command.setFundId(resultSet.getInt("fund_id"));
            command.setCommandType(resultSet.getBoolean("command_type"));
            command.setStockCode(resultSet.getString("stock_code"));
            command.setStockCount(resultSet.getInt("stock_count"));
            command.setStockPrice(resultSet.getDouble("stock_price"));
            return command;
        }
    }
}

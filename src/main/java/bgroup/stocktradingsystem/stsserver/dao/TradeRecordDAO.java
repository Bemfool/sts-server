package bgroup.stocktradingsystem.stsserver.dao;

import bgroup.stocktradingsystem.stsserver.domain.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TradeRecordDAO {
    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public TradeRecordDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TradeRecord> select(String cond) {
        if (cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM trade_record",
                    new RecordMapper());
        else
            return jdbcTemplate.query("SELECT * FROM trade_record WHERE " + cond,
                    new RecordMapper());
    }

    class RecordMapper implements RowMapper<TradeRecord> {
        @Override
        public TradeRecord mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            TradeRecord record = new TradeRecord();
            record.setAmount(resultSet.getInt("trade_id"));
            record.setType(resultSet.getBoolean("type"));
            record.setFundId(resultSet.getInt("fund_id"));
            record.setStockCode(resultSet.getString("stock_code"));
            record.setAmount(resultSet.getInt("amount"));
            record.setPrice(resultSet.getDouble("price"));
            record.setTime(resultSet.getString("time"));
            record.setStatus(resultSet.getBoolean("status"));
            return record;
        }
    }
}

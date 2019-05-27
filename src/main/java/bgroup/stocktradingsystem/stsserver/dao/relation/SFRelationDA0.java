package bgroup.stocktradingsystem.stsserver.dao.relation;

import bgroup.stocktradingsystem.stsserver.domain.relation.SecuritiesFund;
import bgroup.stocktradingsystem.stsserver.impl.idao.relation.iSFRelationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SFRelationDA0 implements iSFRelationDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(SecuritiesFund relation) {
        jdbcTemplate.update("INSERT INTO securities_fund" +
                "(securities_id, fund_id) VALUES(?, ?)", preparedStatement -> {
            preparedStatement.setInt(1, relation.getSecuritiesId() );
            preparedStatement.setInt(2, relation.getFundId());
        });
    }

    @Override
    public void delete(SecuritiesFund relation) {
        jdbcTemplate.update("DELETE FROM fund_account WHERE securities_id = ? AND fund_id = ?",
                preparedStatement -> {
            preparedStatement.setInt(1, relation.getFundId());
            preparedStatement.setInt(2, relation.getFundId() );
        });
    }

    @Override
    public List<SecuritiesFund> select(String cond) {
        if(cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM fund_account", new SFMapper());
        else
            return jdbcTemplate.query("SELECT * FROM fund_account WHERE " + cond, new SFMapper());
    }

    class SFMapper implements RowMapper<SecuritiesFund> {
        @Override
        public SecuritiesFund mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            SecuritiesFund relation = new SecuritiesFund();
            relation.setFundId(resultSet.getInt("fund_id"));
            relation.setSecuritiesId(resultSet.getInt("securities_id"));
            return relation;
        }
    }
}

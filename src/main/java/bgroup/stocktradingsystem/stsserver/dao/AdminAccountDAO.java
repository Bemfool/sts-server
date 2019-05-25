package bgroup.stocktradingsystem.stsserver.dao;

import java.sql.*;
import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.iAdminAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminAccountDAO implements iAdminAccountDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(AdminAccount account) {
        jdbcTemplate.update("INSERT INTO admin_account(id, password) VALUES(?, ?)",
                account.getId(), account.getPassword());

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(AdminAccount account) {

    }

    @Override
    public List<AdminAccount> select(String cond) {
        return jdbcTemplate.query("SELECT * FROM admin_account WHERE " + cond,
                new AdminAccountRowMapper());
    }


    class AdminAccountRowMapper implements RowMapper<AdminAccount> {

        @Override
        public AdminAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminAccount account = new AdminAccount();
            account.setId(rs.getString("id"));
            account.setName(rs.getString("name"));
            account.setPassword(rs.getString("password"));
            return account;
        }
    }
}

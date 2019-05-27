package bgroup.stocktradingsystem.stsserver.dao.account;

import java.sql.*;
import bgroup.stocktradingsystem.stsserver.domain.account.AdminAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.account.iAdminAccountDAO;
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
        jdbcTemplate.update("INSERT INTO admin_account(id, name, password, priv) VALUES(?, ?, ?, ?)",
                account.getId(), account.getName(), account.getPassword(), account.getPriv());

    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM admin_account WHERE id = ?", preparedStatement ->
                preparedStatement.setString(1,id ));
    }

    @Override
    public void update(AdminAccount account) {
        jdbcTemplate.update("UPDATE admin_account SET name = ?, password = ?, priv = ?" +
                        "WHERE id = ?",
                preparedStatement -> {
            preparedStatement.setString(1, account.getName());
            preparedStatement.setString(2,account.getPassword() );
            preparedStatement.setInt(3,account.getPriv() );
            preparedStatement.setString(4, account.getId());
                });
    }

    @Override
    public List<AdminAccount> select(String cond) {
        if(cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM admin_account", new AdminAccountRowMapper());
        else
            return jdbcTemplate.query("SELECT * FROM admin_account WHERE " + cond,
                    new AdminAccountRowMapper());
    }


    /**
     * 管理员账户与数据库返回结果的对应关系
     */
    class AdminAccountRowMapper implements RowMapper<AdminAccount> {
        @Override
        public AdminAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            AdminAccount account = new AdminAccount();
            account.setId(rs.getString("id"));
            account.setName(rs.getString("name"));
            account.setPassword(rs.getString("password"));
            account.setPriv(rs.getInt("priv"));
            return account;
        }
    }
}

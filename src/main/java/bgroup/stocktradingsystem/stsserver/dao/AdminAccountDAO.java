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

    /**
     * @param account 待插入的新账户
     */
    @Override
    public void insert(AdminAccount account) {
        jdbcTemplate.update("INSERT INTO admin_account(id, name, password, priv) VALUES(?, ?, ?, ?)",
                account.getId(), account.getName(), account.getPassword(), account.getPriv());

    }

    /**
     * @param id 待删除的账户id
     */
    @Override
    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM admin_account WHERE id = ?", preparedStatement ->
                preparedStatement.setString(1,id ));
    }

    /**
     * @param account 新的账户
     */
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

    /**
     * @param cond 条件
     * @return 选择的结果
     */
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

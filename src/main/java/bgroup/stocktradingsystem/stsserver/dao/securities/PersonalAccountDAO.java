package bgroup.stocktradingsystem.stsserver.dao.securities;

import bgroup.stocktradingsystem.stsserver.domain.PersonalAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.iPersonalAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonalAccountDAO implements iPersonalAccountDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(PersonalAccount account) {
        jdbcTemplate.update("INSERT INTO personal_account" +
                "(securities_id, register_date, name, gender, id_no, family_add," +
                "career, education, organization, phone_no, agent_id_no, state) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", preparedStatement -> {
            preparedStatement.setInt(1, account.getSecuritiesId() );
            preparedStatement.setDate(2, account.getRegisterDate());
            preparedStatement.setString(3, account.getName() );
            preparedStatement.setBoolean(4, account.getGender() );
            preparedStatement.setString(5, account.getIdNo() );
            preparedStatement.setString(6, account.getFamilyAdd() );
            preparedStatement.setString(7, account.getCareer() );
            preparedStatement.setString(8, account.getEducation() );
            preparedStatement.setString(9, account.getOrganization() );
            preparedStatement.setString(10, account.getPhoneNo() );
            preparedStatement.setString(11, account.getAgentIdNo() );
            preparedStatement.setInt(12, account.getState() );
        });
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM personal_account WHERE securities_id = ?", preparedStatement -> {
            preparedStatement.setInt(1, id);
        });
    }

    @Override
    public void update(PersonalAccount account) {
        jdbcTemplate.update("UPDATE personal_account SET " +
                "register_date = ?," +
                "name = ?," +
                "gender = ?," +
                "id_no = ?," +
                "family_add = ?," +
                "career = ?," +
                "education = ?," +
                "organization = ?," +
                "phone_no = ?," +
                "agent_id_no = ?," +
                "state = ? WHERE securities_id = ?", preparedStatement -> {
            preparedStatement.setDate(1, account.getRegisterDate());
            preparedStatement.setString(2, account.getName() );
            preparedStatement.setBoolean(3, account.getGender() );
            preparedStatement.setString(4, account.getIdNo() );
            preparedStatement.setString(5, account.getFamilyAdd() );
            preparedStatement.setString(6, account.getCareer() );
            preparedStatement.setString(7, account.getEducation() );
            preparedStatement.setString(8, account.getOrganization() );
            preparedStatement.setString(9, account.getPhoneNo() );
            preparedStatement.setString(10, account.getAgentIdNo() );
            preparedStatement.setInt(11, account.getState() );
            preparedStatement.setInt(12, account.getSecuritiesId() );
        });
    }

    @Override
    public List<PersonalAccount> select(String cond) {
        if(cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM personal_account", new PersonalAccountMapper());
        else
            return jdbcTemplate.query("SELECT * FROM personal_account WHERE " + cond, new PersonalAccountMapper());
    }

    class PersonalAccountMapper implements RowMapper<PersonalAccount> {
        @Override
        public PersonalAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            PersonalAccount account = new PersonalAccount();
            account.setSecuritiesId(resultSet.getInt("securities_id"));
            account.setRegisterDate(resultSet.getDate("register_date"));
            account.setName(resultSet.getString("name"));
            account.setGender(resultSet.getBoolean("gender"));
            account.setIdNo(resultSet.getString("id_no"));
            account.setFamilyAdd(resultSet.getString("family_add"));
            account.setCareer(resultSet.getString("career"));
            account.setEducation(resultSet.getString("education"));
            account.setOrganization(resultSet.getString("organization"));
            account.setPhoneNo(resultSet.getString("phone_no"));
            account.setAgentIdNo(resultSet.getString("agent_id_no"));
            account.setState(resultSet.getInt("state"));
            return account;
        }
    }
}

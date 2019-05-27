package bgroup.stocktradingsystem.stsserver.dao.account.securities;

import bgroup.stocktradingsystem.stsserver.domain.account.CorporateAccount;
import bgroup.stocktradingsystem.stsserver.impl.idao.account.iCorporateAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CorporateAccountDAO implements iCorporateAccountDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void insert(CorporateAccount account) {
        jdbcTemplate.update("INSERT INTO corporate_account" +
                "(securities_id, register_no, business_license_no," +
                "legal_representative_id, legal_representative_name," +
                "legal_representative_phone_no, legal_representative_add," +
                "authorizer_name, authorizer_id, authorizer_phone_no, authorizer_add," +
                "state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", preparedStatement ->
                corporatePstmtMapper(account, preparedStatement));
    }

    @Override
    public void deleteByRN(String id) {
        jdbcTemplate.update("DELETE FROM corporate_account WHERE register_id = ?", preparedStatement ->
                preparedStatement.setString(1, id));
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM corporate_account WHERE securities_id = ?", preparedStatement ->
                preparedStatement.setInt(1, id));
    }

    @Override
    public void saveDeleted(CorporateAccount account) {
        jdbcTemplate.update("INSERT INTO corporate_deleted" +
                "(securities_id, register_no, business_license_no," +
                "legal_representative_id, legal_representative_name," +
                "legal_representative_phone_no, legal_representative_add," +
                "authorizer_name, authorizer_id, authorizer_phone_no, authorizer_add," +
                "state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", preparedStatement ->
                corporatePstmtMapper(account, preparedStatement));
    }

    @Override
    public void update(CorporateAccount account) {
        jdbcTemplate.update("UPDATE corporate_account SET " +
                "register_no = ?," +
                "business_license_no = ?," +
                "legal_representative_id = ?," +
                "legal_representative_name = ?," +
                "legal_representative_phone = ?," +
                "legal_representative_add = ?," +
                "authorizer_name = ?," +
                "authorizer_id = ?," +
                "authorizer_phone_no = ?," +
                "authorizer_add = ?," +
                "state = ? WHERE securities_id = ?", preparedStatement -> {
            preparedStatement.setString(1, account.getRegisterNo());
            preparedStatement.setString(2, account.getBusinessLicenseNo() );
            preparedStatement.setString(3, account.getLegalRepresentativeId() );
            preparedStatement.setString(4, account.getLegalRepresentativeName() );
            preparedStatement.setString(5, account.getLegalRepresentativePhoneNo() );
            preparedStatement.setString(6, account.getLegalRepresentativeAdd() );
            preparedStatement.setString(7, account.getAuthorizerName() );
            preparedStatement.setString(8, account.getAuthorizerId() );
            preparedStatement.setString(9, account.getAuthorizerPhoneNo() );
            preparedStatement.setString(10, account.getAuthorizerAdd() );
            preparedStatement.setInt(11, account.getState() );
            preparedStatement.setInt(12, account.getSecuritiesId() );
        });
    }

    @Override
    public List<CorporateAccount> select(String cond) {
        if(cond.isEmpty())
            return jdbcTemplate.query("SELECT * FROM corporate_account", new CorporateAccountMapper());
        else
            return jdbcTemplate.query("SELECT * FROM corporate_account WHERE " + cond, new CorporateAccountMapper());

    }

    /**
     * 法人账户与数据库返回结果的对应关系
     */
    class CorporateAccountMapper implements RowMapper<CorporateAccount> {
        @Override
        public CorporateAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            CorporateAccount account = new CorporateAccount();
            account.setSecuritiesId(resultSet.getInt("securities_id"));
            account.setRegisterNo(resultSet.getString("register_no"));
            account.setBusinessLicenseNo(resultSet.getString("business_license_no"));
            account.setLegalRepresentativeId(resultSet.getString("legal_representative_id"));
            account.setLegalRepresentativeName(resultSet.getString("legal_representative_name"));
            account.setLegalRepresentativePhoneNo(resultSet.getString("legal_representative_phone_no"));
            account.setLegalRepresentativeAdd(resultSet.getString("legal_representative_add"));
            account.setAuthorizerName(resultSet.getString("authorizer_name"));
            account.setAuthorizerId(resultSet.getString("authorizer_id"));
            account.setAuthorizerPhoneNo(resultSet.getString("authorizer_phone_no"));
            account.setAuthorizerAdd(resultSet.getString("authorizer_add"));
            account.setState(resultSet.getInt("state"));
            return account;
        }
    }

    /**
     * @param account 要对应的法人账户
     * @param preparedStatement 预语句
     * @throws SQLException 数据库异常
     */
    private void corporatePstmtMapper(CorporateAccount account, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, account.getSecuritiesId() );
        preparedStatement.setString(2, account.getRegisterNo());
        preparedStatement.setString(3, account.getBusinessLicenseNo() );
        preparedStatement.setString(4, account.getLegalRepresentativeId() );
        preparedStatement.setString(5, account.getLegalRepresentativeName() );
        preparedStatement.setString(6, account.getLegalRepresentativePhoneNo() );
        preparedStatement.setString(7, account.getLegalRepresentativeAdd() );
        preparedStatement.setString(8, account.getAuthorizerName() );
        preparedStatement.setString(9, account.getAuthorizerId() );
        preparedStatement.setString(10, account.getAuthorizerPhoneNo() );
        preparedStatement.setString(11, account.getAuthorizerAdd() );
        preparedStatement.setInt(12, account.getState() );
    }

}

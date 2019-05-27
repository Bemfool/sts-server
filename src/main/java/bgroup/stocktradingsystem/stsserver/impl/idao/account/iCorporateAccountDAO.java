package bgroup.stocktradingsystem.stsserver.impl.idao.account;

import bgroup.stocktradingsystem.stsserver.domain.account.CorporateAccount;

import java.util.List;

public interface iCorporateAccountDAO {
    /**
     * @param account 待插入的法人账户
     */
    void insert(CorporateAccount account);

    /**
     * @param account 更新后的法人账户
     */
    void update(CorporateAccount account);

    /**
     * @param id 待删除的法人账户register_id
     */
    void deleteByRN(String id);
    void deleteById(int id);

    /**
     * @param account 要保存到已被删除的数据库中的账户
     */
    void saveDeleted(CorporateAccount account);

    /**
     * @param cond 条件
     * @return 选择的结果
     */
    List<CorporateAccount> select(String cond);
}

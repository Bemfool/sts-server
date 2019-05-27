package bgroup.stocktradingsystem.stsserver.impl.idao.account;

import bgroup.stocktradingsystem.stsserver.domain.account.AdminAccount;

import java.util.List;

public interface iAdminAccountDAO {
    /**
     * @param account 待插入的新账户
     */
    void insert(AdminAccount account);

    /**
     * @param account 新的账户
     */
    void update(AdminAccount account);

    /**
     * @param id 待删除的账户id
     */
    void delete(String id);

    /**
     * @param cond 条件
     * @return 选择的结果
     */
    List<AdminAccount> select(String cond);
}

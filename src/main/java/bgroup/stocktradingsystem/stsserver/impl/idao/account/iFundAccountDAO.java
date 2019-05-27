package bgroup.stocktradingsystem.stsserver.impl.idao.account;

import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;

import java.util.List;

public interface iFundAccountDAO {
    void insert(FundAccount account);
    void update(FundAccount account);
    void delete(int id);
    List<FundAccount> select(String cond);
    int maxId();
}

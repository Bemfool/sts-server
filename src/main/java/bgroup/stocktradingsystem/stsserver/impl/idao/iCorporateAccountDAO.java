package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.CorporateAccount;

import java.util.List;

public interface iCorporateAccountDAO {
    void insert(CorporateAccount account);
    void update(CorporateAccount account);
    void deleteByRN(String id);
    void deleteById(int id);
    void saveDeleted(CorporateAccount account);
    List<CorporateAccount> select(String cond);
}

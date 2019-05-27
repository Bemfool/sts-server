package bgroup.stocktradingsystem.stsserver.impl.idao.account;

import bgroup.stocktradingsystem.stsserver.domain.account.PersonalAccount;

import java.util.List;

public interface iPersonalAccountDAO {
    void insert(PersonalAccount account);
    void update(PersonalAccount account);
    void deleteByIN(String id);
    void deleteById(int id);
    void saveDeleted(PersonalAccount account);
    List<PersonalAccount> select(String cond);
}

package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.PersonalAccount;

import java.util.List;

public interface iPersonalAccountDAO {
    public void insert(PersonalAccount account);
    public void update(PersonalAccount account);
    public void delete(int id);
    public List<PersonalAccount> select(String cond);
}

package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.CorporateAccount;
import bgroup.stocktradingsystem.stsserver.domain.PersonalAccount;

import java.util.List;

public interface iCorporateAccountDAO {
    public void insert(CorporateAccount account);
    public void update(CorporateAccount account);
    public void delete(int id);
    public List<CorporateAccount> select(String cond);
}

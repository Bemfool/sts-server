package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.SecuritiesAccount;

import java.util.List;

public interface iSecuritiesAccountDAO {
    public Boolean insert(SecuritiesAccount account);
    public Boolean update(SecuritiesAccount account);
    public Boolean delete(String id);
    public List<SecuritiesAccount> select(String cond);
}

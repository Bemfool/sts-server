package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.FundAccount;

import java.util.List;

public interface iFundAccountDAO {
    public Boolean insert(FundAccount account);
    public Boolean update(FundAccount account);
    public Boolean delete(String id);
    public List<FundAccount> select(String cond);
}

package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.FundAccount;

import java.util.List;

public interface iFundAccountDAO {
    public void insert(FundAccount account);
    public void update(FundAccount account);
    public void delete(String id);
    public List<FundAccount> select(String cond);
}

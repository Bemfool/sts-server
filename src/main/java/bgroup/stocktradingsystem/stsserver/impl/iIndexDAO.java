package bgroup.stocktradingsystem.stsserver.impl;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.Index;

import java.util.List;

public interface iIndexDAO {
    public Boolean insert(Index index);
    public Boolean update(Index index);
    public Boolean delete(String code);
    public List<Index> select(String cond);
}

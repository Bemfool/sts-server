package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.Index;

import java.util.List;

public interface iIndexDAO {
    public void insert(Index index);
    public void update(Index index);
    public void delete(String code);
    public List<Index> select(String cond);
}

package bgroup.stocktradingsystem.stsserver.impl.idao.relation;

import bgroup.stocktradingsystem.stsserver.domain.relation.SecuritiesFund;

import java.util.List;

public interface iSFRelationDAO {
    void insert(SecuritiesFund relation);
    void delete(SecuritiesFund relation);
    List<SecuritiesFund> select(String cond);
}

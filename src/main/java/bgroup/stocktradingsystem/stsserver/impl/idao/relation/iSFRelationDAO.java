package bgroup.stocktradingsystem.stsserver.impl.idao.relation;

import bgroup.stocktradingsystem.stsserver.domain.relation.SecuritiesFund;

import java.util.List;

/**
 * iSFRelationDAO用于处理Securities(S)证券账户和Fund(F)资金账户之间的关系。
 *
 * @deprecated 在0.0.1版时设计，后被iFundAccountDAO中新增的其他方法替代。
 * 包括数据库中表格securities_fund也被取消使用。
 * @see bgroup.stocktradingsystem.stsserver.impl.idao.account.iFundAccountDAO
 */
public interface iSFRelationDAO {
    @Deprecated
    void insert(SecuritiesFund relation);
    @Deprecated
    void delete(SecuritiesFund relation);
    @Deprecated
    List<SecuritiesFund> select(String cond);
}

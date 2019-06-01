package bgroup.stocktradingsystem.stsserver.service.relation;

import bgroup.stocktradingsystem.stsserver.dao.relation.SFRelationDA0;
import bgroup.stocktradingsystem.stsserver.domain.relation.SecuritiesFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @deprecated 0.0.1版最初的设计中使用，随后被FundAccountService中的方法取代使用。
 * 包括数据库中表格securities_fund也被取消使用。
 * @see bgroup.stocktradingsystem.stsserver.service.account.FundAccountService
 */
@Service
public class SFRelationService {
    @Autowired
    SFRelationDA0 sfRelationDA0;

    @Deprecated
    public List<Integer> selectRelationWithSecurities(int securitiesId) {
        List<Integer> list = new ArrayList<>();
        List<SecuritiesFund>relations = sfRelationDA0.select("securities_id = " + securitiesId);
        for (SecuritiesFund relation : relations)
            list.add(relation.getFundId());
        return list;
    }

    @Deprecated
    public int selectRelationWithFund(int fundId) {
        return sfRelationDA0.select("fund_id = " + fundId).get(0).getSecuritiesId();
    }

    @Deprecated
    public void alterSecuritiesId(int oldId, int newId) {
        List<SecuritiesFund> relations = sfRelationDA0.select("securities_id = " + oldId);
        for(SecuritiesFund relation : relations) {
            sfRelationDA0.delete(relation);
            relation.setFundId(newId);
            sfRelationDA0.insert(relation);
        }

    }
}

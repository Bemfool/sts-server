package bgroup.stocktradingsystem.stsserver.service.relation;

import bgroup.stocktradingsystem.stsserver.dao.relation.SFRelationDA0;
import bgroup.stocktradingsystem.stsserver.domain.relation.SecuritiesFund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SFRelationService {
    @Autowired
    SFRelationDA0 sfRelationDA0;

    public List<Integer> selectRelationWithSecurities(int securitiesId) {
        List<Integer> list = null;
        List<SecuritiesFund>relations = sfRelationDA0.select("securities_id = " + securitiesId);
        for (SecuritiesFund relation : relations)
            list.add(relation.getFundId());
        return list;
    }

    public int selectRelationWithFund(int fundId) {
        return sfRelationDA0.select("fund_id = " + fundId).get(0).getSecuritiesId();
    }

    public void alterSecuritiesId(int oldId, int newId) {
        List<SecuritiesFund> relations = sfRelationDA0.select("securities_id = " + oldId);
        for(SecuritiesFund relation : relations) {
            sfRelationDA0.delete(relation);
            relation.setFundId(newId);
            sfRelationDA0.insert(relation);
        }

    }
}

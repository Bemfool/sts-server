package bgroup.stocktradingsystem.stsserver.domain.relation;

public class SecuritiesFund {
    private int securitiesId;
    private int fundId;

    public int getSecuritiesId() {
        return securitiesId;
    }

    public int getFundId() {
        return fundId;
    }

    public void setSecuritiesId(int securitiesId) {
        this.securitiesId = securitiesId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }
}

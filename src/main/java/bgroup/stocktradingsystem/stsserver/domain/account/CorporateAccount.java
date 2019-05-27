package bgroup.stocktradingsystem.stsserver.domain.account;

public class CorporateAccount {
    private int securitiesId;
    private String registerNo;
    private String businessLicenseNo;
    private String legalRepresentativeId;
    private String legalRepresentativeName;
    private String legalRepresentativePhoneNo;
    private String legalRepresentativeAdd;
    private String authorizerName;
    private String authorizerId;
    private String authorizerPhoneNo;
    private String authorizerAdd;
    private int state;

    public int getSecuritiesId() {
        return securitiesId;
    }

    public String getAuthorizerId() {
        return authorizerId;
    }

    public int getState() {
        return state;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public String getAuthorizerAdd() {
        return authorizerAdd;
    }

    public String getAuthorizerName() {
        return authorizerName;
    }

    public String getAuthorizerPhoneNo() {
        return authorizerPhoneNo;
    }

    public String getLegalRepresentativeId() {
        return legalRepresentativeId;
    }

    public String getLegalRepresentativeAdd() {
        return legalRepresentativeAdd;
    }

    public String getLegalRepresentativeName() {
        return legalRepresentativeName;
    }

    public String getLegalRepresentativePhoneNo() {
        return legalRepresentativePhoneNo;
    }

    public void setSecuritiesId(int securitiesId) {
        this.securitiesId = securitiesId;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public void setAuthorizerAdd(String authorizerAdd) {
        this.authorizerAdd = authorizerAdd;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setAuthorizerId(String authorizerId) {
        this.authorizerId = authorizerId;
    }

    public void setAuthorizerName(String authorizerName) {
        this.authorizerName = authorizerName;
    }

    public void setAuthorizerPhoneNo(String authorizerPhoneNo) {
        this.authorizerPhoneNo = authorizerPhoneNo;
    }

    public void setLegalRepresentativeAdd(String legalRepresentativeAdd) {
        this.legalRepresentativeAdd = legalRepresentativeAdd;
    }

    public void setLegalRepresentativeId(String legalRepresentativeId) {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public void setLegalRepresentativeName(String legalRepresentativeName) {
        this.legalRepresentativeName = legalRepresentativeName;
    }

    public void setLegalRepresentativePhoneNo(String legalRepresentativePhoneNo) {
        this.legalRepresentativePhoneNo = legalRepresentativePhoneNo;
    }
}

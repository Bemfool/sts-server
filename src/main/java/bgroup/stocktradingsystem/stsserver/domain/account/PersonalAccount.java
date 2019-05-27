package bgroup.stocktradingsystem.stsserver.domain.account;


import java.sql.Date;

public class PersonalAccount {
    private int securitiesId;
    private Date registerDate;
    private String name;
    private Boolean gender;
    private String idNo;
    private String familyAdd;
    private String career;
    private String education;
    private String organization;
    private String phoneNo;
    private String agentIdNo=null;
    private int state;

    public PersonalAccount() {}

    public String getName() {
        return name;
    }

    public Boolean getGender() {
        return gender;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public int getSecuritiesId() {
        return securitiesId;
    }

    public String getCareer() {
        return career;
    }

    public int getState() {
        return state;
    }

    public String getAgentIdNo() {
        return agentIdNo;
    }

    public String getEducation() {
        return education;
    }

    public String getFamilyAdd() {
        return familyAdd;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyAdd(String familyAdd) {
        this.familyAdd = familyAdd;
    }

    public void setSecuritiesId(int securitiesId) {
        this.securitiesId = securitiesId;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setAgentIdNo(String agentIdNo) {
        this.agentIdNo = agentIdNo;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setState(int state) {
        this.state = state;
    }
}

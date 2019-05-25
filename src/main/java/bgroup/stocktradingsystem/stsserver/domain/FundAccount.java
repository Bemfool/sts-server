package bgroup.stocktradingsystem.stsserver.domain;

public class FundAccount {
    String id;
    String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}

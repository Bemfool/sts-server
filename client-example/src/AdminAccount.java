public class AdminAccount {
    private String id;
    private String password;

    public AdminAccount( String id, String password) {
        this.id = id;
        this.password = password;
    }

    public boolean isExist() {
        // TODO
        return true;
    }

    public boolean isValid() {
        // TODO
        return true;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package bgroup.stocktradingsystem.stsserver.domain.response;

public class Result {
    private boolean status;
    private String cause;

    public Result(boolean status) {
        this.status = status;
        this.cause = "";
    }

    public Result(boolean status, String cause) {
        this.status = status;
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    public boolean getStatus() {
        return status;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

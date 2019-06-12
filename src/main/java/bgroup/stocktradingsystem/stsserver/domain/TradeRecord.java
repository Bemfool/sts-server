package bgroup.stocktradingsystem.stsserver.domain;

public class TradeRecord {
    private int tradeId;
    private boolean type;
    private int fundId;
    private String stockCode;
    private int amount;
    private double price;
    private String time;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public String getStockCode() {
        return stockCode;
    }

    public int getFundId() {
        return fundId;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getTradeId() {
        return tradeId;
    }

    public String getTime() {
        return time;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isType() {
        return type;
    }
}

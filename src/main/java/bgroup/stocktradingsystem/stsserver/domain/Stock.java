package bgroup.stocktradingsystem.stsserver.domain;

public class Stock {
    private String stockCode;
    private String stockName;
    private Double stockPrice;
    private String stockState;
    private Double stockLimit;
    private Double closingPrice;
    private Integer stockAmount;
    private Double stockTotal;

    public Stock() {}

    public void setStockCode(String stockCode) { this.stockCode = stockCode; }
    public void setStockName(String stockName) { this.stockName = stockName; }
    public void setStockPrice(Double stockPrice) { this.stockPrice = stockPrice; }
    public void setStockState(String stockState) { this.stockState = stockState; }
    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }
    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
    }
    public void setStockLimit(Double stockLimit) {
        this.stockLimit = stockLimit;
    }
    public void setStockTotal(Double stockTotal) {
        this.stockTotal = stockTotal;
    }

    public String getStockCode() { return stockCode; }
    public Double getStockPrice() { return stockPrice; }
    public Double getStockLimit() { return stockLimit; }
    public String getStockName() { return stockName; }
    public String getStockState() { return stockState; }
    public Double getClosingPrice() { return closingPrice; }
    public Double getStockTotal() {
        return stockTotal;
    }
    public Integer getStockAmount() {
        return stockAmount;
    }
}

package bgroup.stocktradingsystem.stsserver.domain;

public class Index {
    private String indexCode;
    private String indexName;
    private Double indexPrice;

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setIndexPrice(Double indexPrice) {
        this.indexPrice = indexPrice;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public String getIndexName() {
        return indexName;
    }

    public Double getIndexPrice() {
        return indexPrice;
    }
}

package au.com.nab.backend_challenge.model;

public class CryptoCurrencyDeal {

    private String cryptoCurrencyName;

    private CryptoCurrencyPrice buyPrice;
    private CryptoCurrencyPrice sellPrice;
    private String profit;

    public CryptoCurrencyDeal() {

    }

    public CryptoCurrencyDeal(String cryptoCurrencyName, CryptoCurrencyPrice buyPrice, CryptoCurrencyPrice sellPrice, String profit) {
        this.cryptoCurrencyName = cryptoCurrencyName;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.profit = profit;
    }

    public String getCryptoCurrencyName() {
        return cryptoCurrencyName;
    }

    public void setCryptoCurrencyName(String cryptoCurrencyName) {
        this.cryptoCurrencyName = cryptoCurrencyName;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public CryptoCurrencyPrice getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(CryptoCurrencyPrice buyPrice) {
        this.buyPrice = buyPrice;
    }

    public CryptoCurrencyPrice getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(CryptoCurrencyPrice sellPrice) {
        this.sellPrice = sellPrice;
    }
}

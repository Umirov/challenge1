package au.com.nab.backend_challenge.model;

public class CryptoCurrencyPrice {

    private String price;
    private String time;

    public CryptoCurrencyPrice() {
    }

    public CryptoCurrencyPrice(String price, String time) {
        this.price = price;
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

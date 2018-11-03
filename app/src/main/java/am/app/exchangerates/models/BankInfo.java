package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BankInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("date")
    private long date;

    @SerializedName("logo")
    private String logo;

    @SerializedName("list")
    private Map<String, Currency> currencyMap;

    public BankInfo(String title, long date, String logo, Map<String, Currency> currencyMap) {
        this.title = title;
        this.date = date;
        this.logo = logo;
        this.currencyMap = currencyMap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Map<String, Currency> getCurrencyMap() {
        return currencyMap;
    }

    public void setCurrencyMap(Map<String, Currency> currencyMap) {
        this.currencyMap = currencyMap;
    }
}

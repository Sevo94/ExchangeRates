package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BankInfo {

    @SerializedName("title")
    private double title;

    @SerializedName("date")
    private double date;

    @SerializedName("logo")
    private double logo;

    @SerializedName("list")
    private Map<String, Currency> currencyMap;

    public double getTitle() {
        return title;
    }

    public void setTitle(double title) {
        this.title = title;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getLogo() {
        return logo;
    }

    public void setLogo(double logo) {
        this.logo = logo;
    }
}

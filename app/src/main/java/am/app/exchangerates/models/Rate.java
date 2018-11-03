package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("buy")
    private double buyRate;

    @SerializedName("sell")
    private double sellRate;

    public Rate(double buyRate, double sellRate) {
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }
}

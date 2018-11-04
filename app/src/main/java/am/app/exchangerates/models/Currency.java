package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("0")
    private Rate rate;

    @SerializedName("1")
    private Rate otherRate;

    public Currency() {
    }

    public Currency(Rate rate) {
        this.rate = rate;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Rate getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(Rate otherRate) {
        this.otherRate = otherRate;
    }
}

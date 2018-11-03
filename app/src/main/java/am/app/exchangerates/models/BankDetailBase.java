package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BankDetailBase {

    @SerializedName("date")
    private String date;

    @SerializedName("list")
    private Map<String, BankDetail> bankDetailMap;

    public BankDetailBase(String date, Map<String, BankDetail> bankDetailMap) {
        this.date = date;
        this.bankDetailMap = bankDetailMap;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, BankDetail> getBankDetailMap() {
        return bankDetailMap;
    }

    public void setBankDetailMap(Map<String, BankDetail> bankDetailMap) {
        this.bankDetailMap = bankDetailMap;
    }
}

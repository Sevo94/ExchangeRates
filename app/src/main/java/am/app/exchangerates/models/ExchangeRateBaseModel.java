package am.app.exchangerates.models;

import java.util.Map;

public class ExchangeRateBaseModel {

    private Map<String, BankInfo> bankInfoMap;

    public ExchangeRateBaseModel() {

    }

    public ExchangeRateBaseModel(Map<String, BankInfo> bankInfoMap) {
        this.bankInfoMap = bankInfoMap;
    }

    public Map<String, BankInfo> getBankInfoMap() {
        return bankInfoMap;
    }

    public void setBankInfoMap(Map<String, BankInfo> bankInfoMap) {
        this.bankInfoMap = bankInfoMap;
    }
}

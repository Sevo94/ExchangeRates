package am.app.exchangerates.models;

import java.util.Map;

public class Currency {

    private Map<String, Rate> rateMap;

    public Map<String, Rate> getRateMap() {
        return rateMap;
    }

    public void setRateMap(Map<String, Rate> rateMap) {
        this.rateMap = rateMap;
    }
}

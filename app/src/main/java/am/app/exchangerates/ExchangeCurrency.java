package am.app.exchangerates;

public enum ExchangeCurrency {

    USD("USD"),
    EUR("EUR"),
    RUR("RUR"),
    GEL("GEL"),
    GBP("GBP"),
    CHF("CHF"),
    CAD("CAD"),
    AUD("AUD"),
    XAU("XAU");

    String currency;

    ExchangeCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}

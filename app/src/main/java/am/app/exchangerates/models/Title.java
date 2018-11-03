package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("en")
    private String en;

    @SerializedName("am")
    private String am;

    @SerializedName("ru")
    private String ru;

    public Title(String en, String am, String ru) {
        this.en = en;
        this.am = am;
        this.ru = ru;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }
}

package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

public class WorkHours {

    @SerializedName("days")
    private String days;

    @SerializedName("hours")
    private String hours;

    public WorkHours(String days, String hours) {
        this.days = days;
        this.hours = hours;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}

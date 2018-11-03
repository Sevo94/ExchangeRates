package am.app.exchangerates.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BankDetail {

    @SerializedName("head")
    private int head;

    @SerializedName("title")
    private Title title;

    @SerializedName("address")
    private Address address;

    @SerializedName("location")
    private Location location;

    @SerializedName("contacts")
    private String contacts;

    @SerializedName("workhours")
    private List<WorkHours> workHoursList;

    public BankDetail(int head, Title title, Address address, Location location, String contacts, List<WorkHours> workHoursList) {
        this.head = head;
        this.title = title;
        this.address = address;
        this.location = location;
        this.contacts = contacts;
        this.workHoursList = workHoursList;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public List<WorkHours> getWorkHoursList() {
        return workHoursList;
    }

    public void setWorkHoursList(List<WorkHours> workHoursList) {
        this.workHoursList = workHoursList;
    }
}

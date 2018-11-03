package am.app.exchangerates.models;

public class Organization {

    private String organizationId;
    private BankInfo bankInfo;

    public Organization(String organizationId, BankInfo bankInfo) {
        this.organizationId = organizationId;
        this.bankInfo = bankInfo;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
}

package model;

public class Supplier {

    public Supplier(String companyName, String email, String phoneNumber){
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public  Supplier(){}

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private int id;
    private String companyName;
    private String email;
    private String phoneNumber;
}

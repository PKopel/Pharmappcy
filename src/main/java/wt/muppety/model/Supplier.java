package wt.muppety.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = Supplier.TABLE_NAME)
public class Supplier {

    public static final String TABLE_NAME = "Supplier";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return getId() == supplier.getId() &&
                getCompanyName().equals(supplier.getCompanyName()) &&
                Objects.equals(getEmail(), supplier.getEmail()) &&
                Objects.equals(getPhoneNumber(), supplier.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompanyName(), getEmail(), getPhoneNumber());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;
    @Column(name = "companyName", nullable = false, length = 50)
    private String companyName;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "phoneNumber", nullable = false, length = 50)
    private String phoneNumber;
}

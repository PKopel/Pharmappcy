package wt.muppety.model;

import javax.persistence.*;
import java.util.BitSet;
import java.util.Objects;

@Entity
@Table(name=Employee.TABLE_NAME)
public class Employee {
    public static final String TABLE_NAME = "Employee";

    public Employee(String firstname, String lastname, String position, String login, String password){
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.login = login;
        this.password = password;
    }

    public Employee(){setPermissions(true, true, true, true);}

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean canBuy() {
        return permissions.canBuy;
    }

    public boolean canSell() {
        return permissions.canSell;
    }

    public boolean canBrowseDb() {
        return permissions.canBrowseDB;
    }

    public boolean canModerateDB() {
        return permissions.canModerateDB;
    }

    public BitSet permissionsBitSet(){
        return permissions.asBitSet();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getLogin().equals(employee.getLogin()) &&
                getPassword().equals(employee.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    public void setPermissions(boolean canBuy, boolean canSell, boolean canBrowseDB, boolean canModerateDB) {
        permissions.canBuy = canBuy;
        permissions.canSell = canSell;
        permissions.canBrowseDB = canBrowseDB;
        permissions.canModerateDB = canModerateDB;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;
    @Column(name = "position", nullable = false, length = 50)
    private String position;
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Embedded
    private final Permissions permissions = new Permissions();



}

package wt.muppety.model;

import java.util.Objects;

public class Employee {
    public static final String TABLE_NAME = "Employee";
    private int id;
    private String firstname;
    private String lastname;
    private String position;
    private String login;
    private String password;

    public Employee(String firstname, String lastname, String position, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.login = login;
        this.password = password;
    }

    public Employee() {
    }

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

}

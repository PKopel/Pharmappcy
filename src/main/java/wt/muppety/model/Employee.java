package wt.muppety.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Objects;

@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee {
    public static final String TABLE_NAME = "Employee";
    @Embedded
    private final Permissions permissions = new Permissions();
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastname;
    @Column(name = "position", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Employee.Position position;
    @Column(name = "login", nullable = false, length = 50)
    private String login;
    @Column(name = "password", nullable = false, length = 50)
    private byte[] password;
    @Column(name = "salt", nullable = false, length = 50)
    private byte[] salt;

    public Employee(String firstname, String lastname, Employee.Position position, String login, String password) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.login = login;
        this.setPassword(password);
    }

    public Employee() {
        setPermissions(true, true, true, true);
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

    public Employee.Position getPosition() {
        return position;
    }

    public void setPosition(Employee.Position position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    /**
     * Setter responsible for updating password and salt entries after change
     *
     * @param password new password (plain)
     */
    public void setPassword(String password) {
        //hash password
        SecureRandom random = new SecureRandom();
        byte[] hash, salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = keyFactory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException invalidKeySpecException) {
            invalidKeySpecException.printStackTrace();
            return;
        }
        //update salt and password (hashed)
        this.salt = salt;
        this.password = hash;
    }

    public byte[] getSalt() {
        return salt;
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

    public BitSet permissionsBitSet() {
        return permissions.asBitSet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return getLogin().equals(employee.getLogin()) &&
                Arrays.equals(getPassword(), employee.getPassword());
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


    public enum Position {
        Manager, Chair, Worker
    }
}

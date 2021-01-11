package wt.muppety.authentication;

import javafx.scene.control.Button;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Control;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Optional;

/**
 * Main class responsible for managing employee permissions in the system.
 * Allows to log in user and check their permissions.
 * Implements pattern Singleton.
 */
public class Authenticator {

    private static Authenticator _instance;
    private final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    private final BitSet permissions = new BitSet(Permission.values().length);
    private Employee currentUser;
    private boolean isLoggedIn = false;
    private final BitSet _permissions = new BitSet(Permission.values().length);


    private Authenticator() throws NoSuchAlgorithmException {
    }

    public static Authenticator getInstance() {
        try {
            if (_instance == null) {
                _instance = new Authenticator();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return _instance;
    }

    private static boolean hasPermissionTo(Permission p) {
        return _instance != null && _instance.isLoggedIn && _instance.permissions.get(p.value());
    }

    public static void guardControl(Control b, Permission... permissions) {
        b.visibleProperty().bind(Bindings.createBooleanBinding(() -> Arrays.stream(permissions).allMatch(Authenticator::hasPermissionTo)));
        b.managedProperty().bind(Bindings.createBooleanBinding(() -> Arrays.stream(permissions).allMatch(Authenticator::hasPermissionTo)));
    }

    private void grantPermission(Permission p) {
        _instance.permissions.set(p.value());
    }

    private void grantAllPermissions() {
        for (Permission p : Permission.values()) _instance.grantPermission(p);
    }

    public Employee getCurrentUser() {
        return _instance.currentUser;
    }

    public boolean isLoggedIn() {
        return !_instance.isLoggedIn;
    }

    public boolean logIn(LoginData data) {
        Optional<Employee> loginResult = new EmployeeDao().findByLogin(data);
        if (loginResult.isPresent()) {
            Employee match = loginResult.get();
            KeySpec keySpec = new PBEKeySpec(data.getPassword().toCharArray(), match.getSalt(), 65536, 128);
            try {
                if (Arrays.equals(match.getPassword(), keyFactory.generateSecret(keySpec).getEncoded())) {
                    isLoggedIn = true;
                    currentUser = match;
                    permissions.or(match.permissionsBitSet());
                    return true;
                }
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void logOut() {
        isLoggedIn = false;
        currentUser = null;
        permissions.clear();
    }
}

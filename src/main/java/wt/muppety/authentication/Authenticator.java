package wt.muppety.authentication;

import javafx.scene.control.Button;

import javafx.beans.binding.Bindings;

import java.util.Arrays;
import java.util.BitSet;

public class Authenticator {

    private static final Authenticator _instance = new Authenticator();
    private String login;
    private boolean isLoggedIn = true;
    private final BitSet _permissions = new BitSet(Permission.values().length);


    private Authenticator(){}

    private void grantPermission(Permission p)
    {
        _instance._permissions.set(p.value());
    }

    private void grantAllPermissions()
    {
        for(Permission p : Permission.values()) _instance.grantPermission(p);
    }

    public static void logIn()
    {
        _instance.isLoggedIn=true;
        _instance.grantAllPermissions();
    }

    private static boolean hasPermissionTo(Permission p)
    {
        return _instance.isLoggedIn && _instance._permissions.get(p.value());
    }

    public static void guardButton(Button b, Permission... permissions){
        b.visibleProperty().bind(Bindings.createBooleanBinding(()-> Arrays.stream(permissions).allMatch(Authenticator::hasPermissionTo)));
        b.managedProperty().bind(Bindings.createBooleanBinding(()-> Arrays.stream(permissions).allMatch(Authenticator::hasPermissionTo)));
    }
}

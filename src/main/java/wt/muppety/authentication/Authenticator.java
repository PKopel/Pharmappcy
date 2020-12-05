package wt.muppety.authentication;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.BitSet;

public class Authenticator {

    private static final Authenticator INSTANCE = new Authenticator();
    private boolean isLoggedIn;
    private String Login;
    private final BitSet permissions = new BitSet(PermissionValue.values().length);

    private void grantAllPermissions(){
        for(PermissionValue p : PermissionValue.values()) grantPermission(p);
    }

    private void grantPermission(PermissionValue p)
    {
        permissions.set(p.value());
    }

    private Authenticator(){
        isLoggedIn=true;
        grantAllPermissions();
    }

    public static void logIn(){
        //DB reach for log&pass check. Assigns login and permissions
    }

    public static boolean isLoggedIn(){
        return INSTANCE.isLoggedIn;
    }

    public static boolean hasPermissionTo(PermissionValue p)
    {
        return INSTANCE.isLoggedIn && INSTANCE.permissions.get(p.value());
    }

    public static void guardButton(Button b, PermissionValue... values)
    {
        b.visibleProperty().bind(Bindings.createBooleanBinding(()-> Arrays.stream(values).allMatch(Authenticator::hasPermissionTo)));
        b.managedProperty().bind(Bindings.createBooleanBinding(()-> Arrays.stream(values).allMatch(Authenticator::hasPermissionTo)));
    }
}

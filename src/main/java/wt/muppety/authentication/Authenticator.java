package wt.muppety.authentication;

import javafx.scene.control.Button;

import javafx.beans.binding.Bindings;
import wt.muppety.dao.EmployeeDao;
import wt.muppety.model.Employee;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Optional;

public class Authenticator {

    private static final Authenticator _instance = new Authenticator();
    private Employee _current;
    private boolean isLoggedIn = false;
    private final BitSet _permissions = new BitSet(Permission.values().length);


    private Authenticator(){}

    private void grantPermission(Permission p)
    {
        _instance._permissions.set(p.value());
    }

    private static Employee getCurrentUser(){
        return _instance._current;
    }

    public static boolean isLoggedIn(){
        return !_instance.isLoggedIn;
    }

    private void grantAllPermissions()
    {
        for(Permission p : Permission.values()) _instance.grantPermission(p);
    }

    public static boolean logIn(LoginData data)
    {
        Optional<Employee> login_result = new EmployeeDao().findByLogin(data);
        if(login_result.isPresent()){
            _instance.isLoggedIn=true;
            _instance._current = login_result.get();
            _instance._permissions.or(login_result.get().permissionsBitSet());
            return true;
        }
        return false;
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

package wt.muppety.dao;

import org.checkerframework.checker.units.qual.C;
import wt.muppety.model.Category;
import wt.muppety.model.Product;
import wt.muppety.model.Supplier;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

import wt.muppety.authentication.LoginData;
import wt.muppety.model.Employee;

import java.util.Optional;

public class EmployeeDao extends BaseDao<Employee> {

    public Optional<Employee> create(String firstname, String lastname, String position, String login, String password){
        Employee employee = new Employee(firstname,lastname,position,login,password);
        employee.setPermissions(true, true, true, true);
        try{
            this.save(employee);
            return findById(employee.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Employee> create(Employee employee){
        try{
            employee.setPermissions(true, true, true, true);
            this.save(employee);
            return findById(employee.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public ObservableList<Employee> listAll() {
        try{ 
            ObservableList<Employee> employees = FXCollections.observableArrayList(currentSession().createQuery("FROM Product").list());            System.out.println("list all products");
            // for(Product product : products){
            //     System.out.println("Product.getName()");
            // }
            
            return employees;
        }catch (Exception e){
            e.printStackTrace();
        }

        //return Optional.empty();
        return null;
    }

    public Optional<Employee> findById(final int indexNumber) {
        try{
            Employee employee = currentSession().createQuery("SELECT c FROM Employee c WHERE c.id = :id", Employee.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(employee);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Employee> findByLogin(final LoginData data) {
        try{
            System.out.println(currentSession().toString());
            Employee employee = currentSession().createQuery("SELECT c FROM Employee c WHERE c.login = :login AND c.password = :password", Employee.class)
                    .setParameter("login", data.getLogin()).setParameter("password",data.getPassword()).getSingleResult();
            return Optional.of(employee);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }
}

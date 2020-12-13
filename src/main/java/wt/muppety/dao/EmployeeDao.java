package wt.muppety.dao;

import wt.muppety.authentication.LoginData;
import wt.muppety.model.Employee;

import java.util.Optional;

public class EmployeeDao extends BaseDao<Employee> {
    public Optional<Employee> create(String firstname, String lastname, String position, String login, String password){
        Employee employee = new Employee(firstname,lastname,position,login,password);
        try{
            this.save(employee);
            return findById(employee.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
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

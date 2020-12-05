package wt.muppety.dao;

import wt.muppety.model.Employee;

import java.util.Optional;

public class EmployeeDao extends BaseDao<Employee> {
    public Optional<Employee> create(String firstname){
        Employee employee = new Employee();
        try{
            this.save(employee);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(employee);
    }
}
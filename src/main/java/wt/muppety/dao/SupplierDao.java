package wt.muppety.dao;

import wt.muppety.model.Employee;
import wt.muppety.model.Supplier;

import java.util.List;
import java.util.Optional;

public class SupplierDao extends BaseDao<Supplier> {
    public Optional<Supplier> create(String companyName, String email, String phoneNumber){
        Supplier supplier = new Supplier(companyName,email,phoneNumber);
        try{
            this.save(supplier);
            return findById(supplier.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Supplier> create(Supplier supplier){
        try{
            this.save(supplier);
            return findById(supplier.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Supplier> findById(final int indexNumber) {
        try{
            Supplier supplier = currentSession().createQuery("SELECT c FROM Supplier c WHERE c.id = :id", Supplier.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(supplier);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<List<Supplier>> listAll() {
        try{ 
            List<Supplier> suppliers = currentSession().createQuery("FROM Supplier").list();
            System.out.println("list all suppliers");
            for(Supplier supplier : suppliers){
                System.out.println(supplier.getCompanyName());
            }
            
            return Optional.of(suppliers);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
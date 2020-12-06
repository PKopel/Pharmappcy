package wt.muppety.dao;

import org.checkerframework.checker.units.qual.C;
import wt.muppety.model.Category;
import wt.muppety.model.Product;
import wt.muppety.model.Supplier;

import java.util.Optional;

public class ProductDao extends BaseDao<Product> {
    public Optional<Product> create(Supplier supplier, String name, double unitPrice, boolean onPrescription, String manufacturer) {
        Product product = new Product(supplier, name, unitPrice, onPrescription, manufacturer);
        try {
            this.save(product);
            return findById(product.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Product> findById(final int indexNumber) {
        try {
            Product product = currentSession().createQuery("SELECT c FROM Product c WHERE c.id = :id", Product.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    //Methods such as addCategory or changeSupplier were not deemed necessary to implement
    // as this task can easily by acomplished using DAO update method

}
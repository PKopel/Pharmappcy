package wt.muppety.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wt.muppety.model.Product;
import wt.muppety.model.Supplier;

import java.util.Optional;

/**
 * Base class for DAO objects used in application. Allows for querying, listing, creating and updating entries in
 * database. Methods such as addCategory or changeSupplier were not deemed necessary to implement
 * as this task can easily by accomplished using DAO update method
 */

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

    public Optional<Product> create(Product product) {
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
            Product product = currentSession()
                    .createQuery("SELECT c FROM Product c WHERE c.id = :id", Product.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ObservableList<Product> listAll() {
        try {
            return FXCollections.observableArrayList(
                    currentSession().createQuery("FROM Product", Product.class).list()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
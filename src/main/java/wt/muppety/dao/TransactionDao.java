package wt.muppety.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wt.muppety.model.Category;
import wt.muppety.model.Employee;
import wt.muppety.model.Product;
import wt.muppety.model.Transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class TransactionDao extends BaseDao<Transaction> {

    public Optional<Transaction> create(Product product, Employee employee, int quantity, double value, LocalDateTime datetime) {
        Transaction transaction = new Transaction(product, employee, quantity, value, datetime);
        try {
            this.save(transaction);
            return findById(transaction.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Transaction> create(Transaction transaction) {
        try {
            this.save(transaction);
            return findById(transaction.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Transaction> findById(final int indexNumber) {
        try {
            Transaction transaction = currentSession()
                    .createQuery("SELECT c FROM Transaction c WHERE c.id = :id", Transaction.class)
                    .setParameter("id", indexNumber).getSingleResult();
            return Optional.of(transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public ObservableList<Transaction> listAll() {

        try {
            return FXCollections.observableArrayList(
                    currentSession().createQuery("FROM Transaction", Transaction.class).list()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

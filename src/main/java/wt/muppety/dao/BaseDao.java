package wt.muppety.dao;

import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wt.muppety.session.SessionService;

import javax.persistence.PersistenceException;

public abstract class BaseDao<T> {
    public void save(final T object) throws PersistenceException {
        final Session session = SessionService.getSession();

        final Transaction tx = session.beginTransaction();
        session.save(object);
        session.merge(object);
        tx.commit();
    }

    public void update(final T object) throws PersistenceException {
        final Session session = SessionService.getSession();
        final Transaction tx = session.beginTransaction();
        session.update(object);
        session.merge(object);
        tx.commit();
    }

    /**
     * Method to delete item from database by id.
     * Should be used as boolean result = deleteById(Product.class,product.getId)
     *
     * @param type Class object of type of item to delete
     * @param id   Id of object to delete
     * @return     true if object of given id was deleted, false otherwise
     */
    public boolean deleteById(Class<T> type, int id) {
        System.out.println(id);
        final Session session = SessionService.getSession();
        final Transaction tx = session.beginTransaction();
        Object persistentInstance = session.load(type, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            tx.commit();
            return true;
        }
        return false;
    }

    public abstract ObservableList<T> listAll();

    public Session currentSession() {
        return SessionService.getSession();
    }

}

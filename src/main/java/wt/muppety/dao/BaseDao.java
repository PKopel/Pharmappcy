package wt.muppety.dao;

import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import wt.muppety.session.SessionService;

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

    // should be used as boolean result = deleteById(Product.class,product.getId)
    private boolean deleteById(Class<?> type, int id) {
        final Session session = SessionService.getSession();
        Object persistentInstance = session.load(type, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            return true;
        }
        return false;
    }

    public Session currentSession() {
        return SessionService.getSession();
    }

}
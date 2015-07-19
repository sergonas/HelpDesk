package me.oque.dao;

import me.oque.entity.DataObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dmitry Smorzhok on 18.07.15.
 */
@Transactional
@Repository
public class SelectionDaoImpl implements SelectionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public <T extends DataObject> void save(T object) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public <T extends DataObject> void delete(T object) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public <T extends DataObject> void deleteById(Class<T> clazz, Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz)
                .add(Restrictions.eq("id", id));
        T object = (T) criteria.getExecutableCriteria(session).uniqueResult();
        session.delete(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public <T extends DataObject> List<T> getAll(Class<T> clazz) {
        Session session = sessionFactory.openSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        session.beginTransaction();
        List<T> list = criteria.getExecutableCriteria(session).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public <T extends DataObject> T getObjectByQuery(DetachedCriteria query) {
        Session session = sessionFactory.openSession();
        T object = (T) query.getExecutableCriteria(session).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return object;
    }
}

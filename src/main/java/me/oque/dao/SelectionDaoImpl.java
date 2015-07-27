package me.oque.dao;

import me.oque.entity.DataObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Dmitry Smorzhok on 18.07.15.
 */
@Transactional
@Repository
public class SelectionDaoImpl implements SelectionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T extends DataObject> void save(T object) {
        entityManager.persist(object);
    }

    @Override
    public <T extends DataObject> void delete(T object) {
        entityManager.remove(object);
    }

    @Override
    public <T extends DataObject> void deleteById(Class<T> clazz, Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> c = query.from(clazz);
        ParameterExpression<Long> p = cb.parameter(Long.class);
        query.select(c).where(cb.equal(c.get("id"), p));

        TypedQuery<T> entityManagerQuery = entityManager.createQuery(query);
        entityManagerQuery.setParameter(p, id);
        List<T> result = entityManagerQuery.getResultList();
        result.forEach(entityManager::remove);
    }

    @Override
    public <T extends DataObject> List<T> getAll(Class<T> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> c = query.from(clazz);
        query.select(c);

        TypedQuery<T> entityManagerQuery = entityManager.createQuery(query);
        return entityManagerQuery.getResultList();
    }

    @Override
    public <T extends DataObject> T getObjectByQuery(Class<T> clazz, String query) {
        TypedQuery<T> entityManagerQuery = entityManager.createQuery(query, clazz);
        return entityManagerQuery.getSingleResult();
    }

    @Override
    public <T extends DataObject> List<T> listObjectByPage(Class<T> clazz, int page, int pageSize) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> c = query.from(clazz);
        query.select(c);

        TypedQuery<T> entityManagerQuery = entityManager.createQuery(query);
        entityManagerQuery.setFirstResult((page - 1) * pageSize);
        entityManagerQuery.setMaxResults(pageSize);
        return entityManagerQuery.getResultList();
    }

    @Override
    public <T extends DataObject> long countAll(Class<T> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        query.select(cb.count(query.from(clazz)));

        TypedQuery<Long> entityManagerQuery = entityManager.createQuery(query);
        return entityManagerQuery.getSingleResult();
    }
}

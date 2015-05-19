package ca.lorenz.controller;

import ca.lorenz.model.PersistenceUnit;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.RollbackException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;

public class CommonService<T extends PersistenceUnit, ID extends Serializable> implements Service<T, ID> {

	private static final Logger logger = Logger.getLogger(CommonService.class.getName());

	private Class<T> persistentClass;
	protected EntityManager em;

	public enum Order {
        ASC, DESC
    };

	public CommonService(EntityManager em) {
		this.em = em;
	}

	@Override
    public T findByID(ID id) {
        T entity = null;
        if (id != null) {
            entity = em.find(getPersistentClass(), id);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getPersistentClass());
        TypedQuery<T> tq = em.createQuery(cq);
        return tq.getResultList();
    }

    public List<T> findAllOrderBy(String field, Order order) {
        CriteriaQuery<T> select = createFindAllOrderBy(field, order);
        TypedQuery<T> tq = em.createQuery(select);
        return tq.getResultList();
    }

    @Override
    public T save(final T entity) throws Exception {
        T savedEntity = null;
        try {
            final T temp = findByID((ID) entity.getId());
            if (temp != null) {
                savedEntity = em.merge(entity);
            } else {
                em.persist(entity);
                em.flush();
                savedEntity = entity;
            }
        } catch (Exception pEx) {
            logger.error("Erro", pEx);
            throw pEx;
        }
        return savedEntity;
    }

    @Override
    public void remove(T entity) throws PersistenceException {
        try {
            entity = findByID((ID) entity.getId());
            em.remove(entity);
        } catch (Exception ex) {
            logger.error("Erro", ex);;
            throw new PersistenceException(ex.getMessage(), ex.getCause());
        }
    }

    private Class<T> getPersistentClass() {
        if (persistentClass == null) {
            Type type = getClass().getGenericSuperclass();
            
            if (type instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) type;
                persistentClass = (Class<T>) paramType.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("Could not guess entity class by reflection");
            }
        }
        return persistentClass;
    }

    private CriteriaQuery<T> createFindAllOrderBy(String field, Order order) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(getPersistentClass());
        Root<T> from = query.from(getPersistentClass());
        CriteriaQuery<T> select = query.select(from);
        switch (order) {
            case ASC:
                select.orderBy(cb.asc(from.get(field)));
                break;
            case DESC:
                select.orderBy(cb.desc(from.get(field)));
                break;
            default:
                break;
        }
        return select;
    }
}
package ca.lorenz.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;

public interface Service<E, ID extends Serializable> {

	E findByID(final ID id);

	List<E> findAll();

	E save(final E entity) throws Exception;

	void remove(final E entity) throws PersistenceException;

}
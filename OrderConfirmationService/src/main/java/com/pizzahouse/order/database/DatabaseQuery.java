package com.pizzahouse.order.database;

import java.util.List;

import org.slf4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pizzahouse.common.exception.DatabaseUnavailableException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.persistence.RollbackException;

@Service
public class DatabaseQuery<T> {
	@Autowired
	protected org.hibernate.Session dbSession;
	@Autowired
	protected org.slf4j.Logger logger;

	/**
	 * Check the DB connection is established or not
	 * @return CriteriaBuilder object
	 * @throws DatabaseUnavailableException 
	 * 
	 */
	public CriteriaBuilder getCriteriaBuilder() throws DatabaseUnavailableException {
		if (dbSession != null) {
		    return dbSession.getCriteriaBuilder();
		} else {
			throw new DatabaseUnavailableException("Unable to obtain DB session");
		}
	}
	
	
	/**
	 * Check the DB connection is established or not
	 * @return Boolean concerning the DB connection is established or not
	 */
	public boolean checkConnection () {
		return dbSession.isConnected();
	}

	/**
	 * Common function in selecting object Criteria Query
	 * @param query Query condition supplimented
	 * @return List of generic T object specified from creation
	 */
	public List<T> query(CriteriaQuery<T> query) {
		try {
			logger.debug("Start query");
			dbSession.beginTransaction();
			List<T> result = dbSession.createQuery(query).getResultList();
			dbSession.getTransaction().commit();
			return result;
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}	
	
	/**
	 * Select item by the field specified using "@id" annotation in the entity
	 * @param clazz Class object itself as entity mapping identifier
	 * @param id The ID to be query, using the column specified using "@id" annotation in the entity
	 * @return Generic T object specified from creation
	 */
	public T selectById(Class<T> clazz, int id){
		try {
			logger.debug("Start select by id");
			dbSession.beginTransaction();
			T result = (T) dbSession.load(clazz, id);
			dbSession.getTransaction().commit();
			return result;
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}

	/**
	 * Select all items in the table by specifying the target entity class
	 * @param clazz Class object itself as entity mapping identifier
	 * @return List of generic T object specified from creation
	 * @throws DatabaseUnavailableException 
	 */
	public List<T> selectAll(Class<T> clazz) throws DatabaseUnavailableException{
		try {
			logger.debug("Start select all");
			CriteriaQuery<T> cq = getCriteriaBuilder().createQuery(clazz);
			Root<T> root = cq.from(clazz);
			CriteriaQuery<T> query = cq.select(root);
			return query(query);
		} catch (DatabaseUnavailableException e) {
			dbSession.getTransaction().rollback();
			throw new DatabaseUnavailableException("Database transaction rolled back " + e.getMessage());
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}


	
	/**
	 * Insert the entity to the database
	 * @param entity Entity to insert
	 * @return ID of the record (The field which annotates @id specified in the entity)
	 */
	public int insert(T entity) {
		try {
			logger.debug("Start insert");
			dbSession.beginTransaction();
			int id = (Integer) dbSession.save(entity);
			dbSession.getTransaction().commit();
			return id;
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}
	
	/**
	 * Insert the entityList to the database
	 * @param entityList List of entity to insert
	 * @return ID of the record (The field which annotates @id specified in the entity)
	 */
	public boolean insertList(List<T> entityList) {
		try {
			logger.debug("Start insert");
			dbSession.beginTransaction();
			for(Object entity : entityList) {
				int id = (Integer) dbSession.save(entity);
			}
			dbSession.getTransaction().commit();
			return true;
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}
	
	/**
	 * Insert or update the entity to the database
	 * @param entity Entity to insert or update to the database
	 * @return True if the entity is updated or inserted
	 */	
	public boolean insertOrUpdate(Object entity) {
		try {
			logger.debug("Start insert or update");
			dbSession.beginTransaction();
			dbSession.saveOrUpdate(entity);
			dbSession.getTransaction().commit();
			return true;
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}

	
	/**
	 * Delete the entity to the database
	 * @param entity Entity to delete
	 * @return True if the database record in deleted
	 */	
	public boolean delete(Object entity) {
		try {
			logger.debug("Start delete");
	
			dbSession.beginTransaction();
			dbSession.delete(entity);
			dbSession.getTransaction().commit();
			return true;
		} catch (Exception e) {
			dbSession.getTransaction().rollback();
			throw new RollbackException("Database transaction rolled back " + e.getMessage());
		}
	}
}

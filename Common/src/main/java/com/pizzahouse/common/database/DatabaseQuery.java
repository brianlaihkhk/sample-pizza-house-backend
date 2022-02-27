package com.pizzahouse.common.database;

import java.util.List;

import org.slf4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
public class DatabaseQuery<T> {
	@Autowired
	protected Session dbSession;
	@Autowired
	protected Logger logger;

	private CriteriaBuilder criteriaBuilder = dbSession.getCriteriaBuilder();
	
	public void setDbSession(Session dbSession) {
		// for Junit initialization
		this.dbSession = dbSession;
	}

	public void setLogger(Logger logger) {
		// for Junit initialization
		this.logger = logger;
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
		logger.debug("Start query");
		dbSession.beginTransaction();
		List<T> result = dbSession.createQuery(query).getResultList();
		dbSession.getTransaction().commit();
		return result;
	}	
	
	/**
	 * Select item by the field specified using "@id" annotation in the entity
	 * @param clazz Class object itself as entity mapping identifier
	 * @param id The ID to be query, using the column specified using "@id" annotation in the entity
	 * @return Generic T object specified from creation
	 */
	public T selectById(Class<T> clazz, int id){
		logger.debug("Start select by id");
		dbSession.beginTransaction();
		T result = (T) dbSession.load(clazz, id);
		dbSession.getTransaction().commit();
		return result;
	}

	/**
	 * Select all items in the table by specifying the target entity class
	 * @param clazz Class object itself as entity mapping identifier
	 * @return List of generic T object specified from creation
	 */
	public List<T> selectAll(Class<T> clazz){
		logger.debug("Start select all");
		CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		CriteriaQuery<T> query = cq.select(root);
		return query(query);
	}


	/**
	 * Function to call shared CriteriaBuilder object
	 * @return CriteriaBuilder
	 */
	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

	
	/**
	 * Insert the entity to the database
	 * @param entity Entity to insert
	 * @return ID of the record (The field which annotates @id specified in the entity)
	 */
	public int insert(Object entity) {
		logger.debug("Start insert");
		dbSession.beginTransaction();
		int id = (Integer) dbSession.save(entity);
		dbSession.getTransaction().commit();
		return id;
	}
	
	
	/**
	 * Insert or update the entity to the database
	 * @param entity Entity to insert or update to the database
	 * @return True if the entity is updated or inserted
	 */	
	public boolean insertOrUpdate(Object entity) {
		logger.debug("Start insert or update");
		dbSession.beginTransaction();
		dbSession.saveOrUpdate(entity);
		dbSession.getTransaction().commit();
		return true;
	}

	
	/**
	 * Delete the entity to the database
	 * @param entity Entity to delete
	 * @return True if the database record in deleted
	 */	
	public boolean delete(Object entity) {
		logger.debug("Start delete");

		dbSession.beginTransaction();
		dbSession.delete(entity);
		dbSession.getTransaction().commit();
		return true;
	}
}

package com.pizzahouse.common.database;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public class DatabaseQuery<T> extends DatabaseHandler {
	CriteriaBuilder criteriaBuilder = dbSession.getCriteriaBuilder();

	/**
	 * Select item by the field specified using "@id" annotation in the entity
	 * @param clazz Class object itself as entity mapping identifier
	 * @param id The ID to be query, using the column specified using "@id" annotation in the entity
	 * @return Generic T object specified from creation
	 */
	public T select(Class<T> clazz, int id){
		dbSession.beginTransaction();
		T result = (T) dbSession.load(clazz, id);
		dbSession.getTransaction().commit();
		return result;
	}
	
	/**
	 * Select item by using predicates and JPQL
	 * @param clazz Class object itself as entity mapping identifier
	 * @param predicates Conditions for the selection
	 * @return List of generic T object specified from creation
	 */
	public List<T> selectByCriteria(Class<T> clazz, Predicate[] predicates){
	    Root<T> root = getRoot(clazz);
		return selectByCriteria(clazz, root, predicates);
	}

	/**
	 * Common function in selecting object using predicates, selection and JPQL
	 * @param clazz Class object itself as entity mapping identifier
	 * @param selection What object needs to be queried and place into the entity
	 * @param predicates Conditions for the selection
	 * @return List of generic T object specified from creation
	 */
	public List<T> selectByCriteria(Class<T> clazz, Selection<T> selection, Predicate[] predicates) {
	    CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
	    CriteriaQuery<T> query = cq.select(selection).where(predicates);

		dbSession.beginTransaction();
		List<T> result = (List<T>) dbSession.createQuery(query);
		dbSession.getTransaction().commit();
		return result;
	}

	/**
	 * Select all items in the table by specifying the target entity class
	 * @param clazz Class object itself as entity mapping identifier
	 * @return List of generic T object specified from creation
	 */
	public List<T> selectAll(Class<T> clazz){
	    Root<T> root = getRoot(clazz);
		return selectByCriteria(clazz, root, new Predicate[0]);
	}

	/**
	 * Function to call shared CriteriaBuilder object
	 * @return CriteriaBuilder
	 */
	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}
	
	/**
	 * Obtain the base object/entity that needs to be queried and place into the entity
	 * @return Root object which implements Selection object
	 */
	public Root<T> getRoot(Class<T> clazz){
	    CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
		return cq.from(clazz);
	}
	
	/**
	 * Insert the entity to the database
	 * @param entity Entity to insert
	 * @return ID of the record (The field which annotates @id specified in the entity)
	 */
	public int insert(Object entity) {
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
		dbSession.beginTransaction();
		dbSession.delete(entity);
		dbSession.getTransaction().commit();
		return true;
	}
}

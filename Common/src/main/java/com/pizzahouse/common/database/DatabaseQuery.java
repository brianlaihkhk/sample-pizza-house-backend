package com.pizzahouse.common.database;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.query.Query;

import com.pizzahouse.common.entity.User;
import com.pizzahouse.common.model.OrderConfirmation;

public class DatabaseQuery<T> extends DatabaseHandler {
	CriteriaBuilder criteriaBuilder = dbSession.getCriteriaBuilder();

	public T select(Class<T> clazz, int id){
		dbSession.beginTransaction();
		T result = (T) dbSession.load(clazz, id);
		dbSession.getTransaction().commit();
		return result;
	}
	
//	public List<T> selectByParameter(String namedQuery, Map<String, Object> parameterList) {
//		dbSession.beginTransaction();
//		Query select = dbSession.getNamedQuery(namedQuery);
//		for (Map.Entry<String,Object> parameter : parameterList.entrySet()) {
//			select.setParameter(parameter.getKey(), parameter.getValue());
//		}		
//		return (List<T>) select.getResultList();
//	}
	
	public List<T> selectByCriteria(Class<T> clazz, Predicate[] predicates){
	    Root<T> root = getRoot(clazz);
		return selectByCriteria(clazz, root, predicates);
	}
	
	public List<T> selectByCriteria(Class<T> clazz, Selection<T> selection, Predicate[] predicates) {
	    CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
	    CriteriaQuery<T> query = cq.select(selection).where(predicates);

		dbSession.beginTransaction();
		List<T> result = (List<T>) dbSession.createQuery(query);
		dbSession.getTransaction().commit();
		return result;
	}
	
	public List<T> selectAll(Class<T> clazz){
	    Root<T> root = getRoot(clazz);
		return selectByCriteria(clazz, root, new Predicate[0]);
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}
	
	public Root<T> getRoot(Class<T> clazz){
	    CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
		return cq.from(clazz);
	}
	
	public int insert(Object entity) {
		dbSession.beginTransaction();
		int id = (Integer) dbSession.save(entity);
		dbSession.getTransaction().commit();
		return id;
	}
	
	public boolean insertOrUpdate(Object entity) {
		dbSession.beginTransaction();
		dbSession.saveOrUpdate(entity);
		dbSession.getTransaction().commit();
		return true;
	}
	
	public boolean delete(Object entity) {
		dbSession.beginTransaction();
		dbSession.delete(entity);
		dbSession.getTransaction().commit();
		return true;
	}
}

package com.pizzahouse.common.database;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;

public class DatabaseTransaction<T> extends DatabaseHandler {
	
	public T select(T entity, int id){
		dbSession.beginTransaction();
		T result = (T) dbSession.load(entity.getClass(), id);
		dbSession.getTransaction().commit();
		return result;
	}
	
	public List<T> selectByParameter(String namedQuery, Map<String, Object> parameterList) {
		dbSession.beginTransaction();
		Query select = dbSession.getNamedQuery(namedQuery);
		for (Map.Entry<String,Object> parameter : parameterList.entrySet()) {
			select.setParameter(parameter.getKey(), parameter.getValue());
		}		
		return (List<T>) select.getResultList();
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

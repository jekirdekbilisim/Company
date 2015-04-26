package com.jekirdek.server.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.jekirdek.server.entity.AbstractEntity;

public abstract class AbstractDAOImpl<K, E extends AbstractEntity> {

	Logger				logger	= LoggerFactory.getLogger(getClass().getName());

	protected Class<E>	entityClass;

	@PersistenceContext(name = "companyUnit")
	EntityManager		entityManager;

	@SuppressWarnings("unchecked")
	public AbstractDAOImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	public E persistOrUpdate(E entity) {
		if (StringUtils.isEmpty(entity.getObjid()))
			persist(entity);
		else
			merge(entity);
		return entity;
	}

	public void persist(E entity) {
		getEntityManager().persist(entity);
	}

	public void remove(E entity) {
		getEntityManager().remove(entity);
	}

	public void refresh(E entity) {
		getEntityManager().refresh(entity);
	}

	public E merge(E entity) {
		return getEntityManager().merge(entity);
	}

	public E findByOid(K id) {
		return getEntityManager().find(entityClass, id);
	}

	public E flush(E entity) {
		getEntityManager().flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		String queryStr = "SELECT h FROM " + entityClass.getName() + " h";
		Query query = getEntityManager().createQuery(queryStr, entityClass);
		List<E> resultList = query.getResultList();
		return resultList;
	}

	public Integer removeAll() {
		String queryStr = "DELETE FROM " + entityClass.getName() + " h";
		Query query = getEntityManager().createQuery(queryStr);
		return query.executeUpdate();
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
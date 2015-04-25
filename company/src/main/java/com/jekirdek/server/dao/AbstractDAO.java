package com.jekirdek.server.dao;

import java.util.List;

public interface AbstractDAO<K, E> {

	public void remove(E entity);

	public void refresh(E entity);

	public E findByOid(K id);

	public E flush(E entity);

	public List<E> findAll();

	public Integer removeAll();

	public E persistOrUpdate(E entity);
}

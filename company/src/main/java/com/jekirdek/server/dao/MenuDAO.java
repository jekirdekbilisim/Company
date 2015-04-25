package com.jekirdek.server.dao;

import java.util.List;

import com.jekirdek.server.entity.Menu;

public interface MenuDAO extends AbstractDAO<String, Menu> {

	List<Menu> findMenuByRole(String string);

}

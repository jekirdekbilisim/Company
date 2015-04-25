package com.jekirdek.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.client.controller.UserController;
import com.jekirdek.client.dto.UserDTO;
import com.jekirdek.server.dao.UserDAO;
import com.jekirdek.server.entity.User;

/**
 * The server side implementation of the RPC service.
 */
@Service("userController")
@Transactional
public class UserControllerImpl extends AbstractController implements UserController {

	@Autowired
	private transient UserDAO userDAO;

	@Override
	@Transactional
	public void createUser(UserDTO userDTO) {
		userDAO.persistOrUpdate(new User(userDTO.getUserTCKN(), userDTO.getUserFirstName(), userDTO.getUserLastName(), userDTO
				.getUserEmail(), userDTO.getUserCellPhone(), userDTO.getUserPhone(), userDTO.getUserPhoneInternal(), userDTO
				.getUserCountry(), userDTO.getUserCity(), userDTO.getUserDistrict(), userDTO.getUserAddress()));
	}
}

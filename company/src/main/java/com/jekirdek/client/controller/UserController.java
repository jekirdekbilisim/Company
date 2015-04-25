package com.jekirdek.client.controller;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.dto.UserDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proxyServlet/userController")
public interface UserController extends RemoteService {

	@Authorization(roles = { RoleType.ADMIN })
	public void createUser(UserDTO userDTO);
}

package com.jekirdek.client.controller;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jekirdek.client.dto.UserDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface UserControllerAsync {
	public void createUser(UserDTO userDTO, AsyncCallback<Void> callback);
}

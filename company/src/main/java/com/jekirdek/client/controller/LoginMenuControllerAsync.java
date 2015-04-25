package com.jekirdek.client.controller;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jekirdek.client.dto.AdminLoginDTO;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.MemberLoginDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface LoginMenuControllerAsync {

	void controlAndLoginWithTckn(MemberLoginDTO dto, AsyncCallback<AuthDataDTO> callback);

	void logout(MemberLoginDTO dto, AsyncCallback<AuthDataDTO> asyncCallback);

	void loadMenuItemsAndPrivileges(String input, AsyncCallback<AuthDataDTO> callback);

	void authCompanySelected(CompanySearchDTO searchDTO, AsyncCallback<AuthDataDTO> asyncCallback);

	void adminLogin(AdminLoginDTO dto, AsyncCallback<AuthDataDTO> asyncCallback);
}

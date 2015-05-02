package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jekirdek.client.dto.UserCompanyAuthDTO;
import com.jekirdek.client.dto.UserCompanyAuthDTO;

public interface AuthorizationControllerAsync {
	public void loadUserCompany(UserCompanyAuthDTO dto, AsyncCallback<UserCompanyAuthDTO> callback);

	public void saveUserAuthCompany(UserCompanyAuthDTO dto, AsyncCallback<List<String>> callback);

	public void changeSelectedCompanyWithOid(String companyOid, AsyncCallback<Void> callback);

	public void changeSelectedCompanyWithAlias(String companyOid, AsyncCallback<Void> callback);

}

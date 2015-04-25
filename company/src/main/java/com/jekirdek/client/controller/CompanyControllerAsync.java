package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jekirdek.client.dto.CompanyInfoDTO;
import com.jekirdek.client.dto.CompanyLogoDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.CompanySearchSuggestDTO;
import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.util.ListItem;

public interface CompanyControllerAsync {
	public void saveUpdateCompany(CompanyInfoDTO companyDTO, AsyncCallback<Void> callback);

	public void logoUpload(CompanyLogoDTO dto, AsyncCallback<Void> callback);

	public void getLogoByte(String companyOid, AsyncCallback<byte[]> callback);

	public void companyHasLogo(String companyOid, AsyncCallback<Boolean> asyncCallback);

	public void loadCompanyInfo(CompanySearchDTO searchDTO, AsyncCallback<CompanyInfoDTO> asyncCallback);

	public void searchCompanySuggest(CompanySearchSuggestDTO dto, AsyncCallback<List<ListItem>> callback);

	public void loadAuthCompany(String string, AsyncCallback<List<CompanySelectData>> asyncCallback);

}

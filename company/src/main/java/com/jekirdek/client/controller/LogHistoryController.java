package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.jekirdek.client.annotation.Authorization;
import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.dto.LogHistoryDTO;
import com.jekirdek.client.dto.LogHistoryData;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("proxyServlet/logHistoryController")
public interface LogHistoryController extends RemoteService {

	@Authorization(roles = { RoleType.MEMBER, RoleType.ADMIN })
	List<LogHistoryData> searchLogHistoryByDate(LogHistoryDTO dto) throws Exception;

}

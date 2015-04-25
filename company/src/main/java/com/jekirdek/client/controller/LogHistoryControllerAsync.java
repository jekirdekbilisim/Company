package com.jekirdek.client.controller;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jekirdek.client.dto.LogHistoryDTO;
import com.jekirdek.client.dto.LogHistoryData;

public interface LogHistoryControllerAsync {

	void searchLogHistoryByDate(LogHistoryDTO dto, AsyncCallback<List<LogHistoryData>> asyncCall);
}

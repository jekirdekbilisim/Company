package com.jekirdek.server.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.client.controller.LogHistoryController;
import com.jekirdek.client.dto.LogHistoryDTO;
import com.jekirdek.client.dto.LogHistoryData;
import com.jekirdek.server.dao.ActionLogDAO;
import com.jekirdek.server.dao.CompanyDAO;
import com.jekirdek.server.dao.DocumentDAO;
import com.jekirdek.server.dao.UserDAO;
import com.jekirdek.server.entity.ActionLog;
import com.jekirdek.server.util.ResourceBundleUtil;
import com.jekirdek.server.util.SessionUtil;

/**
 * The server side implementation of the RPC service.
 */
@Service("logHistoryController")
@Transactional
public class LogHistoryControllerImpl extends AbstractController implements LogHistoryController {

	@Autowired
	private transient ActionLogDAO	actionLogDAO;
	@Autowired
	private transient CompanyDAO	companyDAO;
	@Autowired
	private transient UserDAO		userDAO;
	@Autowired
	private transient DocumentDAO	documentDAO;

	@Override
	public List<LogHistoryData> searchLogHistoryByDate(LogHistoryDTO dto) throws Exception {
		if (dto.getStartDate() == null)
			throw new Exception("Başlangıç tarihi boş geçilemez");
		if (dto.getFinishDate() == null)
			throw new Exception("Bitiş tarihi boş geçilemez");

		Date startDate = minimazeHourMinuteSecond(dto.getStartDate());
		Date finishDate = maximazeHourMinuteSecond(dto.getFinishDate());
		List<ActionLog> actionLogList = actionLogDAO.searchActionHistoryByDate(startDate, finishDate, SessionUtil.getSessionUser()
				.getSelectedCompanyOid());

		List<LogHistoryData> logHistoryList = new ArrayList<LogHistoryData>();
		if (actionLogList != null) {
			for (ActionLog actionLog : actionLogList) {
				LogHistoryData historyData = new LogHistoryData();
				historyData
						.setActionLogType(ResourceBundleUtil.instance().getString("ActionLogType_".concat(actionLog.getActionLogType())));
				historyData.setCompany(companyDAO.findCompanyNameByOid(actionLog.getCompanyOid()));
				historyData.setManager(companyDAO.findManagerNameByOid(actionLog.getManagerOid()));
				historyData.setInspector(companyDAO.findInspectorNameByOid(actionLog.getInspectorOid()));
				historyData.setDocumentDbStoreOid(actionLog.getDocumentDbStoreOid());
				historyData.setDocumentType(documentDAO.findDocumentTypeNameByOid(actionLog.getDocumentTypeOid()));
				historyData.setEffectedUser(userDAO.findUserNameByOid(actionLog.getEffectedUserOid()));
				historyData.setUser(userDAO.findUserNameByOid(actionLog.getUserOid()));

				logHistoryList.add(historyData);
			}
		}
		return logHistoryList;
	}

	private Date maximazeHourMinuteSecond(Date finishDate) {
		if (finishDate == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(finishDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	private Date minimazeHourMinuteSecond(Date startDate) {
		if (startDate == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 1);
		calendar.set(Calendar.SECOND, 1);
		return calendar.getTime();
	}

}

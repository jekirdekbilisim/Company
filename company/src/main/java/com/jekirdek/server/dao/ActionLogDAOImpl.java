package com.jekirdek.server.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jekirdek.server.constant.ActionLogTypeConstant;
import com.jekirdek.server.entity.ActionLog;
import com.jekirdek.server.entity.Company;
import com.jekirdek.server.util.SessionUtil;

@Repository("actionLogTypeDAO")
@Transactional
public class ActionLogDAOImpl extends AbstractDAOImpl<String, ActionLog> implements ActionLogDAO {

	@Override
	public void logCompanySaveOrUpdate(Boolean newCompany, Company company, String signableInput, String sign) {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(company.getObjid());
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setSignableInput(signableInput);
		actionLog.setSign(sign);
		if (newCompany)
			actionLog.setActionLogType(ActionLogTypeConstant.COMPANY_INSERT.toString());
		else
			actionLog.setActionLogType(ActionLogTypeConstant.COMPANY_INFO_UPDATE.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public void deleteDiretorLog(String managerOid, String companyOid) {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(companyOid);
		actionLog.setManagerOid(managerOid);
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setActionLogType(ActionLogTypeConstant.MANAGER_DELETE.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public void addDiretorLog(String managerOid, String companyOid) {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(companyOid);
		actionLog.setManagerOid(managerOid);
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setActionLogType(ActionLogTypeConstant.MANAGER_INSERT.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public void deleteInspectorLog(String inspectorOid, String companyOid) {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(companyOid);
		actionLog.setInspectorOid(inspectorOid);
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setActionLogType(ActionLogTypeConstant.INSPECTOR_DELETE.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public void addInspectorLog(String inspectorOid, String companyOid) {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(companyOid);
		actionLog.setInspectorOid(inspectorOid);
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setActionLogType(ActionLogTypeConstant.INSPECTOR_INSERT.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public void updateLogo() {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(SessionUtil.getSessionUser().getSelectedCompanyOid());
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setActionLogType(ActionLogTypeConstant.LOGO_UPLOAD.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public void documentInsert(String documentTypeOid, String documentDbStoreOid) {
		ActionLog actionLog = new ActionLog();
		actionLog.setCompanyOid(SessionUtil.getSessionUser().getSelectedCompanyOid());
		actionLog.setUserOid(SessionUtil.getSessionUser().getObjid());
		actionLog.setDocumentTypeOid(documentTypeOid);
		actionLog.setDocumentDbStoreOid(documentDbStoreOid);
		actionLog.setActionLogType(ActionLogTypeConstant.DOCUMENT_INSERT.toString());
		persistOrUpdate(actionLog);
	}

	@Override
	public List<ActionLog> searchActionHistoryByDate(Date startDate, Date finishDate, String companyOid) {
		String sqlQuery = "select a from ActionLog a where a.createDate > :startDate and a.createDate < :finishDate and a.companyOid = :companyOid";
		Query query = getEntityManager().createQuery(sqlQuery);
		query.setParameter("startDate", startDate);
		query.setParameter("finishDate", finishDate);
		query.setParameter("companyOid", companyOid);
		List<ActionLog> result = query.getResultList();
		return result;
	}
}

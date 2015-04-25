package com.jekirdek.server.dao;

import java.util.Date;
import java.util.List;

import com.jekirdek.server.entity.ActionLog;
import com.jekirdek.server.entity.Company;

public interface ActionLogDAO extends AbstractDAO<String, ActionLog> {

	void logCompanySaveOrUpdate(Boolean newCompany, Company company, String signableInput, String sign);

	void deleteDiretorLog(String managerOid, String companyOid);

	void addDiretorLog(String managerOid, String companyOid);

	void deleteInspectorLog(String inspectorOid, String companyOid);

	void addInspectorLog(String inspectorOid, String companyOid);

	void updateLogo();

	void documentInsert(String documentTypeOid, String documentDbStoreOid);

	List<ActionLog> searchActionHistoryByDate(Date startDate, Date finishDate, String companyOid);

}

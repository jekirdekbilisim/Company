package com.jekirdek.server.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jekirdek.client.constant.RoleType;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.dto.CompanyDTO;
import com.jekirdek.client.dto.CompanyInfoDTO;
import com.jekirdek.client.dto.CompanyLogoDTO;
import com.jekirdek.client.dto.CompanySearchDTO;
import com.jekirdek.client.dto.CompanySearchSuggestDTO;
import com.jekirdek.client.dto.CompanySelectData;
import com.jekirdek.client.dto.InspectorDTO;
import com.jekirdek.client.dto.ManagerDTO;
import com.jekirdek.client.util.ListItem;
import com.jekirdek.server.dao.ActionLogDAO;
import com.jekirdek.server.dao.CompanyDAO;
import com.jekirdek.server.entity.Company;
import com.jekirdek.server.entity.Inspector;
import com.jekirdek.server.entity.Logo;
import com.jekirdek.server.entity.Manager;
import com.jekirdek.server.util.LobUtil;
import com.jekirdek.server.util.SessionUtil;

/**
 * The server side implementation of the RPC service.
 */
@Service("companyController")
@Transactional
public class CompanyControllerImpl extends AbstractController implements CompanyController {

	@Autowired
	private transient CompanyDAO	companyDAO;
	@Autowired
	private transient ActionLogDAO	actionLogDAO;

	@Override
	@Transactional
	public void saveUpdateCompany(CompanyInfoDTO dto) throws Exception {
		// company exist control
		Company company = companyDAO.findByAlias(SessionUtil.getSessionUser().getSelectedCompanyAlias());

		// yetkisiz islem kontrolu
		if (!SessionUtil.getSessionUser().getAuthorizedCompanyOidList().contains(company.getObjid())) {
			throw new Exception("İşlem yapmaya çalıştığınız şirket yetkili olduğunuz şirketler arasında değildir.");
		}

		company.setCommittedCapital(dto.getCompanyDTO().getCommittedCapital());
		company.setCompanyCenter(dto.getCompanyDTO().getCompanyCenter());
		company.setCompanyType(dto.getCompanyDTO().getCompanyType());
		company.setContactAddress(dto.getCompanyDTO().getContactAddress());
		company.setFaxNo(dto.getCompanyDTO().getFaxNo());
		company.setPhoneNo(dto.getCompanyDTO().getPhoneNo());
		company.setInternetAddress(dto.getCompanyDTO().getInternetAddress());
		company.setMersisNo(dto.getCompanyDTO().getMersisNo());
		company.setPaidCapital(dto.getCompanyDTO().getPaidCapital());
		company.setTradeName(dto.getCompanyDTO().getTradeName());
		company.setAlias(dto.getCompanyDTO().getAlias());

		deleteAndAddManager(dto, company);
		deleteAndAddInspector(dto, company);

		company = companyDAO.persistOrUpdate(company);
		// log for this process
		actionLogDAO.logCompanySaveOrUpdate(false, company, dto.getSignableInput(), dto.getSign());
	}

	private void deleteAndAddManager(CompanyInfoDTO dto, Company company) {
		// first remove from db deleted record
		processDeletedManager(dto, company);
		// then add or update record to db
		processAddedManager(dto, company);
	}

	private void processAddedManager(CompanyInfoDTO dto, Company company) {
		if (dto.getManagerDtoList() != null && !dto.getManagerDtoList().isEmpty()) {
			// manager exist control
			for (ManagerDTO managerDTO : dto.getManagerDtoList()) {
				Manager manager = null;
				if (managerDTO.getObjid() != null) {
					for (Manager curManager : company.getManagerList()) {
						// if manager exist, find it
						if (curManager.getObjid().equals(managerDTO.getObjid()))
							manager = curManager;
					}
				}
				// if does not exist, initialize
				if (manager == null)
					manager = new Manager();

				manager.setManagerTye(managerDTO.getManagerType() == null ? null : managerDTO.getManagerType().toString());
				manager.setObjid(managerDTO.getObjid());
				manager.setMersisNo(managerDTO.getMersisNo());
				manager.setTitle(managerDTO.getTitle());
				manager.setName(managerDTO.getName());
				manager.setSurname(managerDTO.getSurname());
				manager.setTradeName(managerDTO.getTradeName());
				manager.setCompanyCenter(managerDTO.getCompanyCenter());
				manager.setProfile(managerDTO.getProfile());

				manager.setCompany(company);
				company.getManagerList().add(manager);
				// log for this process
				actionLogDAO.addDiretorLog(null, company.getObjid());
			}
		}

	}

	private void processDeletedManager(CompanyInfoDTO dto, Company company) {
		Set<Manager> deletedManagerSet = new HashSet<Manager>();
		// silinmis olanlar tespit ediliyor
		for (Manager manager : company.getManagerList()) {
			boolean isDeleted = true;
			if (dto.getManagerDtoList() != null) {
				for (ManagerDTO managerDTO : dto.getManagerDtoList()) {
					if (manager.getObjid().equals(managerDTO.getObjid())) {
						isDeleted = false;
						break;
					}
				}
			}
			if (isDeleted) {
				companyDAO.deleteManagerByOid(manager.getObjid());
				actionLogDAO.deleteDiretorLog(manager.getObjid(), company.getObjid());
				deletedManagerSet.add(manager);
			}
		}
		if (deletedManagerSet.size() > 0)
			company.getManagerList().removeAll(deletedManagerSet);
	}

	private void deleteAndAddInspector(CompanyInfoDTO dto, Company company) {
		// first remove from db deleted record
		processDeletedInspector(dto, company);
		// then add or update record to db
		processAddedInspector(dto, company);
	}

	private void processAddedInspector(CompanyInfoDTO dto, Company company) {
		if (dto.getInspectorDtoList() != null && !dto.getInspectorDtoList().isEmpty()) {
			for (InspectorDTO inspectorDTO : dto.getInspectorDtoList()) {
				// inspector exist control
				Inspector inspector = null;
				if (inspectorDTO.getObjid() != null) {
					for (Inspector inspectorOld : company.getInspectorList()) {
						// if inspector exist, find it
						if (inspectorOld.getObjid().equals(inspectorDTO.getObjid()))
							inspector = inspectorOld;
					}
				}
				// if does not exist, initialize
				if (inspector == null)
					inspector = new Inspector();
				inspector.setName(inspectorDTO.getName());
				inspector.setSurname(inspectorDTO.getSurname());
				inspector.setTitle(inspectorDTO.getTitle());
				inspector.setRegisteredBranch(inspectorDTO.getRegisteredBranch());
				inspector.setAddress(inspectorDTO.getAddress());

				inspector.setCompany(company);
				company.getInspectorList().add(inspector);
				actionLogDAO.addInspectorLog(null, company.getObjid());
			}
		}
	}

	private void processDeletedInspector(CompanyInfoDTO dto, Company company) {
		Set<Inspector> deletedInspectorSet = new HashSet<Inspector>();
		// silinmis olanlar tespit ediliyor
		for (Inspector inspector : company.getInspectorList()) {
			boolean isDeleted = true;
			if (dto.getInspectorDtoList() != null) {
				for (InspectorDTO inspectorDTO : dto.getInspectorDtoList()) {
					if (inspector.getObjid().equals(inspectorDTO.getObjid())) {
						isDeleted = false;
						break;
					}
				}
			}
			if (isDeleted) {
				companyDAO.deleteInspectorByOid(inspector.getObjid());
				deletedInspectorSet.add(inspector);
				actionLogDAO.deleteInspectorLog(inspector.getObjid(), company.getObjid());
			}
		}
		if (deletedInspectorSet.size() > 0)
			company.getInspectorList().removeAll(deletedInspectorSet);
	}

	@Override
	@Transactional
	public void logoUpload(CompanyLogoDTO dto) {
		Object fileObject = SessionUtil.session.getAttribute(dto.getLogoFileSessionKey());
		if (fileObject != null && fileObject instanceof FileItem) {
			FileItem uploadedFile = (FileItem) fileObject;
			Company company = companyDAO.findByOid(SessionUtil.getSessionUser().getSelectedCompanyOid());
			byte[] fileByte;
			Blob logoBlob = null;
			try {
				fileByte = IOUtils.toByteArray(uploadedFile.getInputStream());
				logoBlob = new SerialBlob(fileByte);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			catch (Exception e3) {
				e3.printStackTrace();
			}
			if (logoBlob != null) {
				Logo logo = new Logo();
				if (company.getLogo() != null)
					logo = company.getLogo();
				logo.setLogo(logoBlob);
				company.setLogo(logo);
				companyDAO.persistOrUpdate(company);
				actionLogDAO.updateLogo();
				// delete file from session
				SessionUtil.session.removeAttribute(dto.getLogoFileSessionKey());
			}
		}
	}

	@Override
	@Transactional
	public byte[] getLogoByte(String companyOid) throws Exception {
		if (!StringUtils.isEmpty(companyOid)) {
			Blob companyLogo = companyDAO.findLogoByCompanyOid(companyOid);
			if (companyLogo != null)
				return LobUtil.blobToByte(companyLogo);
			else
				return null;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean companyHasLogo(String companyOid) {
		String selectedCompanyOid = companyOid;
		if (StringUtils.isEmpty(selectedCompanyOid))
			selectedCompanyOid = SessionUtil.getSessionUser().getSelectedCompanyOid();
		return companyDAO.companyHasLogo(selectedCompanyOid);
	}

	@Override
	@Transactional
	public CompanyInfoDTO loadCompanyInfo(CompanySearchDTO searchDTO) {

		Company company = null;
		if (!StringUtils.isEmpty(searchDTO.getCompanyOid()))
			company = companyDAO.findByAlias(SessionUtil.getSessionUser().getSelectedCompanyOid());
		else
			company = companyDAO.findByOid(SessionUtil.getSessionUser().getSelectedCompanyOid());

		if (company != null) {
			CompanyInfoDTO infoDTO = new CompanyInfoDTO();

			// set company info
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setObjid(company.getObjid());
			companyDTO.setCommittedCapital(company.getCommittedCapital());
			companyDTO.setCompanyCenter(company.getCompanyCenter());
			companyDTO.setCompanyType(company.getCompanyType());
			companyDTO.setContactAddress(company.getContactAddress());
			companyDTO.setFaxNo(company.getFaxNo());
			companyDTO.setInternetAddress(company.getInternetAddress());
			companyDTO.setMersisNo(company.getMersisNo());
			companyDTO.setPaidCapital(company.getPaidCapital());
			companyDTO.setPhoneNo(company.getPhoneNo());
			companyDTO.setTradeName(company.getTradeName());
			companyDTO.setAlias(company.getAlias());
			infoDTO.setCompanyDTO(companyDTO);

			infoDTO.setManagerDtoList(new ArrayList<ManagerDTO>());
			if (company.getManagerList() != null) {
				for (Manager manager : company.getManagerList()) {
					ManagerDTO managerDTO = new ManagerDTO();
					managerDTO.setObjid(manager.getObjid());
					managerDTO.setTradeName(manager.getTradeName());
					managerDTO.setCompanyCenter(manager.getCompanyCenter());
					managerDTO.setManagerType(manager.getManagerTye());
					managerDTO.setMersisNo(manager.getMersisNo());
					managerDTO.setTitle(manager.getTitle());
					managerDTO.setName(manager.getName());
					managerDTO.setSurname(manager.getSurname());
					managerDTO.setProfile(manager.getProfile());
					infoDTO.getManagerDtoList().add(managerDTO);
				}
			}

			infoDTO.setInspectorDtoList(new ArrayList<InspectorDTO>());
			if (company.getInspectorList() != null) {
				for (Inspector inspector : company.getInspectorList()) {
					InspectorDTO inspectorDTO = new InspectorDTO();
					inspectorDTO.setObjid(inspector.getObjid());
					inspectorDTO.setAddress(inspector.getAddress());
					inspectorDTO.setName(inspector.getName());
					inspectorDTO.setRegisteredBranch(inspector.getRegisteredBranch());
					inspectorDTO.setSurname(inspector.getSurname());
					inspectorDTO.setTitle(inspector.getTitle());

					infoDTO.getInspectorDtoList().add(inspectorDTO);
				}
			}

			return infoDTO;
		}
		return null;
	}

	@Override
	public List<ListItem> searchCompanySuggest(CompanySearchSuggestDTO dto) throws Exception {
		return companyDAO.searchAllCompanyForSuggest();
	}

	@Override
	public List<CompanySelectData> loadAuthCompany(String str) {
		return companyDAO.findUserAuthorizedCompanyListByRole(SessionUtil.getSessionUser().getObjid(), RoleType.MEMBER);
	}
}

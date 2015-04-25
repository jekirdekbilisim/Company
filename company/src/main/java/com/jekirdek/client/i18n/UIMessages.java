package com.jekirdek.client.i18n;

import com.google.gwt.i18n.client.Messages;

public interface UIMessages extends Messages {

	@DefaultMessage(value = "Company Info")
	public String CompanyInfo_pageName();

	@DefaultMessage(value = "User Register")
	public String UserRegister_pageName();

	@DefaultMessage(value = "User Company Assign")
	public String UserCompanyAssign_pageName();

	@DefaultMessage(value = "Test Page")
	public String TestPage_pageName();

	@DefaultMessage(value = "Member Login")
	public String MemberLogin_pageName();

	@DefaultMessage(value = "Inspector Register")
	public String InspectorRegister_pageName();

	@DefaultMessage(value = "Document Type Create")
	public String DocumentTypeCreate_pageName();

	@DefaultMessage(value = "Company Logo Upload")
	public String CompanyLogoUpload_pageName();

	@DefaultMessage(value = "Document Meta Param Create")
	public String DocumentMetaParamCreate_pageName();

	@DefaultMessage(value = "Document Meta Create")
	public String DocumentMetaCreate_pageName();

	@DefaultMessage(value = "D		ocument Match Create")
	public String DocumentMatchCreate_pageName();

	@DefaultMessage(value = "Document Class Create")
	public String DocumentClassCreate_pageName();

	@DefaultMessage(value = "Gerçek Kişi Tanımla")
	public String RealManagerRegister_pageName();

	@DefaultMessage(value = "Tüzel Kişi Tanımla")
	public String CorpManagerRegister_pageName();

	@DefaultMessage(value = "Company Register")
	public String CompanyRegister_pageName();

	@DefaultMessage(value = "Company Document Insert")
	public String CompanyDocumentInsert_pageName();

	@DefaultMessage(value = "Company Document")
	public String CompanyDocument_pageName();

	@DefaultMessage(value = "Company Auth Select")
	public String CompanyAuthSelect_pageName();

	@DefaultMessage(value = "Admin Login")
	public String AdminLogin_pageName();

	@DefaultMessage(value = "Real")
	public String ManagerType_real();

	@DefaultMessage(value = "Corporate")
	public String ManagerType_corporate();

	@DefaultMessage(value = "Logout")
	public String logout();

	@DefaultMessage(value = "Log History")
	public String LogHistory_pageName();

	@DefaultMessage(value = "User Name")
	public String LogHistory_userName();

	@DefaultMessage(value = "Effected User Name")
	public String LogHistory_effectedUserName();

	@DefaultMessage(value = "Action Log Type")
	public String LogHistory_actionLogType();

	@DefaultMessage(value = "Company Name")
	public String LogHistory_companyName();

	@DefaultMessage(value = "Document Type Name")
	public String LogHistory_documentTypeName();

	@DefaultMessage(value = "Manager Name")
	public String LogHistory_managerName();

	@DefaultMessage(value = "Inspector Name")
	public String LogHistory_inspectorName();

	@DefaultMessage(value = "Limited")
	public String Company_Limited();

	@DefaultMessage(value = "Anonim")
	public String Company_Anonim();

	@DefaultMessage(value = "Komandit")
	public String Company_Komandit();

	@DefaultMessage(value = "Kolektif")
	public String Company_Kolektif();

	@DefaultMessage(value = "İrtibat Bürosu")
	public String Company_IrtibatBurosu();

	@DefaultMessage(value = "Şube")
	public String Company_Sube();

	@DefaultMessage(value = "Yönetim Kurulu Başkanı")
	public String Title_Baskan();

	@DefaultMessage(value = "Yönetim Kurulu Başkan Vekili")
	public String Title_BaskanVekili();

	@DefaultMessage(value = "Yönetim Kurulu Üyesi")
	public String Title_KurulUyesi();

	@DefaultMessage(value = "Bağımsız Yönetim Kurulu Başkan Vekili")
	public String Title_BagimsizBaskanVekili();

	@DefaultMessage(value = "Bağımsız Yönetim Kurul Üyesi")
	public String Title_BagimsizKurulUyesi();

}

insert into mths.privilege (OBJID,NAME) values ('d9a43657-ac61-11e3-b4a4-5c514f0e0550','CompanyInfo_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9a5d555-ac61-11e3-b4a4-5c514f0e0550','CompanyRegister_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9a71b64-ac61-11e3-b4a4-5c514f0e0550','DirectorRegister_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9aad524-ac61-11e3-b4a4-5c514f0e0550','DocumentMetaCreate_SELECT');
insert into mths.privilege (OBJID,NAME) values ('1dbc601a-c98d-11e3-b029-5c514f0e0550','DocumentMetaParamCreate_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9a84aaa-ac61-11e3-b4a4-5c514f0e0550','DocumentClassCreate_SELECT');
insert into mths.privilege (OBJID,NAME) values ('2715a127-c98d-11e3-b029-5c514f0e0550','DocumentTypeCreate_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9a9983c-ac61-11e3-b4a4-5c514f0e0550','DocumentMatchCreate_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9ac0d9e-ac61-11e3-b4a4-5c514f0e0550','InspectorRegister_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9acf2fb-ac61-11e3-b4a4-5c514f0e0550','MemberLogin_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9ae12b5-ac61-11e3-b4a4-5c514f0e0550','TestPage_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d9aef2af-ac61-11e3-b4a4-5c514f0e0550','UserRegister_SELECT');
insert into mths.privilege (OBJID,NAME) values ('f3040a71-b6bc-11e3-9276-5c514f0e0550','CompanyLogoUpload_SELECT');
insert into mths.privilege (OBJID,NAME) values ('d42a1de0-c58e-11e3-b384-5c514f0e0550','UserCompanyAssign_SELECT');
insert into mths.privilege (OBJID,NAME) values ('e062ef87-c59f-11e3-b384-5c514f0e0550','CompanyAuthSelect_SELECT');
insert into mths.privilege (OBJID,NAME) values ('e28a53b3-c805-11e3-a094-5c514f0e0550','CompanyDocument_SELECT');
insert into mths.privilege (OBJID,NAME) values ('9773bc4b-c875-11e3-a094-5c514f0e0550','CompanyDocumentInsert_SELECT');
insert into mths.privilege (OBJID,NAME) values ('fcb144ed-c89f-11e3-a094-5c514f0e0550','AdminLogin_SELECT');
insert into mths.privilege (OBJID,NAME) values ('e7aa3737-f19d-11e3-9455-5c514f0e0550','LogHistory_SELECT');


insert into mths.role(OBJID,NAME) values ('d9afe36a-ac61-11e3-b4a4-5c514f0e0550','NOT_LOGIN');
insert into mths.role(OBJID,NAME) values ('d9b0ce02-ac61-11e3-b4a4-5c514f0e0550','MEMBER');
insert into mths.role(OBJID,NAME) values ('f5c1757a-c5a5-11e3-b384-5c514f0e0550','MEMBER_COMPANY_SELECT');
insert into mths.role(OBJID,NAME) values ('d9b1c47d-ac61-11e3-b4a4-5c514f0e0550','ADMIN');

insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('c71763eb-ad3e-11e3-b4a4-5c514f0e0550','d9a43657-ac61-11e3-b4a4-5c514f0e0550','d9afe36a-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('c717f097-ad3e-11e3-b4a4-5c514f0e0550','d9acf2fb-ac61-11e3-b4a4-5c514f0e0550','d9afe36a-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('2e526bd3-9314-11e4-968e-5c514f0e0550','d9a43657-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('660fbf9c-c58f-11e3-b384-5c514f0e0550','d42a1de0-c58e-11e3-b384-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('2e526bd3-9314-11e4-968e-5c514f0e0550','d9a5d555-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('cbcc0519-ad3e-11e3-b4a4-5c514f0e0550','d9a71b64-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('cbcc06e4-ad3e-11e3-b4a4-5c514f0e0550','d9ac0d9e-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('a0d9f22c-b6bd-11e3-9276-5c514f0e0550','f3040a71-b6bc-11e3-9276-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('ccf5bb9e-ad3e-11e3-b4a4-5c514f0e0550','d9aef2af-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('ccf5dbef-ad3e-11e3-b4a4-5c514f0e0550','d9a84aaa-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('ccf5deb5-ad3e-11e3-b4a4-5c514f0e0550','d9a9983c-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('ccf5e0cc-ad3e-11e3-b4a4-5c514f0e0550','d9aad524-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('f126fe20-c59f-11e3-b384-5c514f0e0550','e062ef87-c59f-11e3-b384-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('23f5b4a2-c5a6-11e3-b384-5c514f0e0550','e062ef87-c59f-11e3-b384-5c514f0e0550','f5c1757a-c5a5-11e3-b384-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('4c1e7418-c806-11e3-a094-5c514f0e0550','e28a53b3-c805-11e3-a094-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('f72b06b3-c875-11e3-a094-5c514f0e0550','9773bc4b-c875-11e3-a094-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('01c0e036-c884-11e3-a094-5c514f0e0550','e28a53b3-c805-11e3-a094-5c514f0e0550','d9afe36a-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('38ae34ef-c8a0-11e3-a094-5c514f0e0550','fcb144ed-c89f-11e3-a094-5c514f0e0550','d9afe36a-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('a335e55d-c98d-11e3-b029-5c514f0e0550','1dbc601a-c98d-11e3-b029-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('a9118b77-c98d-11e3-b029-5c514f0e0550','2715a127-c98d-11e3-b029-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege(OBJID,PRIVILEGE_OID,ROLE_OID) values ('7cf21f9e-f19e-11e3-9455-5c514f0e0550','e7aa3737-f19d-11e3-9455-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');

insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('9770b708-ad3b-11e3-b4a4-5c514f0e0550','Şirket Bilgileri','d9a43657-ac61-11e3-b4a4-5c514f0e0550','company-info','com.mths.client.page.CompanyInfo',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('a03e6eac-ad3b-11e3-b4a4-5c514f0e0550','Şirket Kayıt','d9a5d555-ac61-11e3-b4a4-5c514f0e0550','company-register','com.mths.client.page.CompanyRegister',0);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('a1c75d1f-ad3b-11e3-b4a4-5c514f0e0550','Yönetici Ekle','d9a71b64-ac61-11e3-b4a4-5c514f0e0550','director-register','com.mths.client.page.DirectorRegister',0);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('a2f047f4-ad3b-11e3-b4a4-5c514f0e0550','Döküman Oluşturma','d9a84aaa-ac61-11e3-b4a4-5c514f0e0550','document-create-doc','com.mths.client.page.DocumentCreateDoc',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('ac7a5b4a-ad3b-11e3-b4a4-5c514f0e0550','Döküman Oluşturma','d9a9983c-ac61-11e3-b4a4-5c514f0e0550','document-create-match','com.mths.client.page.DocumentCreateMatch',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('b02a818e-ad3b-11e3-b4a4-5c514f0e0550','Döküman Oluşturma','d9aad524-ac61-11e3-b4a4-5c514f0e0550','document-create-meta','com.mths.client.page.DocumentCreateMeta',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('b555a2f6-ad3b-11e3-b4a4-5c514f0e0550','Denetçi Ekle','d9ac0d9e-ac61-11e3-b4a4-5c514f0e0550','inspector-register','com.mths.client.page.InspectorRegister',0);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('bb2e4a7b-ad3b-11e3-b4a4-5c514f0e0550','Sisteme Giriş','d9acf2fb-ac61-11e3-b4a4-5c514f0e0550','member-login','com.mths.client.page.MemberLogin',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('c07f13de-ad3b-11e3-b4a4-5c514f0e0550','Test Sayfası','d9ae12b5-ac61-11e3-b4a4-5c514f0e0550','test-page','com.mths.client.page.TestPage',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('c4ea1d07-ad3b-11e3-b4a4-5c514f0e0550','Kullanıcı Kayıt','d9aef2af-ac61-11e3-b4a4-5c514f0e0550','user-register','com.mths.client.page.UserRegister',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('4300b517-b6bd-11e3-9276-5c514f0e0550','Şirket Logo','f3040a71-b6bc-11e3-9276-5c514f0e0550','company-logo-upload','com.mths.client.page.CompanyLogoUpload',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('ca344297-c58f-11e3-b384-5c514f0e0550','Şirket Yetki Atama','d42a1de0-c58e-11e3-b384-5c514f0e0550','user-company-assign','com.mths.client.page.UserCompanyAssign',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('020153a7-c5a0-11e3-b384-5c514f0e0550','Yetkili Şirket Seçme','e062ef87-c59f-11e3-b384-5c514f0e0550','company-auth-select','com.mths.client.page.CompanyAuthSelect',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('0124d2f5-c806-11e3-a094-5c514f0e0550','Şirket Belgeleri','e28a53b3-c805-11e3-a094-5c514f0e0550','company-document','com.mths.client.page.CompanyDocument',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('ac940d7c-c875-11e3-a094-5c514f0e0550','Şirket Belgeleri Ekle','9773bc4b-c875-11e3-a094-5c514f0e0550','company-document-insert','com.mths.client.page.CompanyDocumentInsert',1);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('17ab54c2-c8a0-11e3-a094-5c514f0e0550','Admin Login','fcb144ed-c89f-11e3-a094-5c514f0e0550','admin-login','com.mths.client.page.AdminLogin',0);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('2897af65-c98e-11e3-b029-5c514f0e0550','Döküman Tipi','2715a127-c98d-11e3-b029-5c514f0e0550','document-type-create','com.mths.client.page.DocumentTypeCreate',0);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('d8ddd360-c98e-11e3-b029-5c514f0e0550','Döküman Metadata Param','1dbc601a-c98d-11e3-b029-5c514f0e0550','document-meta-param-create','com.mths.client.page.DocumentMetaParamCreate',0);
insert into mths.menu(OBJID,NAME,PRIVILEGE_OID,FRIENDLY_URL,MENU_CLASS,VISIBLE) values ('27c72e35-f19e-11e3-9455-5c514f0e0550','Log Geçmişi','e7aa3737-f19d-11e3-9455-5c514f0e0550','log-history','com.mths.client.page.LogHistory',10);


insert into mths.user(OBJID,EMAIL,FIRST_NAME,LAST_NAME,PHONE,TCKN) values ('9eae18d2-af9a-11e3-a8d6-5c514f0e0550','ilyasbesli@gmail.com','ilyas','beşli','05556239079','45178302594');

insert into mths.user_role(OBJID,ROLE_OID,USER_OID) values ('ceb8320c-af9b-11e3-a8d6-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550','9eae18d2-af9a-11e3-a8d6-5c514f0e0550');


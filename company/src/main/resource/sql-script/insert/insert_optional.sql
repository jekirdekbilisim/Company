insert into mths.privilege (objid,name) values (uuid(),'CompanyInfo_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'CompanyRegister_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'DirectorRegister_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'DocumentCreateDoc_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'DocumentCreateMatch_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'DocumentCreateMeta_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'InspectorRegister_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'Login_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'TestPage_SELECT');
insert into mths.privilege (objid,name) values (uuid(),'UserRegister_SELECT');

insert into mths.role (objid,name) values (uuid(),'NOT_LOGIN');
insert into mths.role (objid,name) values (uuid(),'MEMBER');
insert into mths.role (objid,name) values (uuid(),'ADMIN');

insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Şirket Bilgileri','d9a43657-ac61-11e3-b4a4-5c514f0e0550','company-info','com.mths.client.page.CompanyInfo',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Şirket Kayıt','d9a5d555-ac61-11e3-b4a4-5c514f0e0550','company-register','com.mths.client.page.CompanyRegister',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Yönetici Ekle','d9a71b64-ac61-11e3-b4a4-5c514f0e0550','director-register','com.mths.client.page.DirectorRegister',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Döküman Oluşturma','d9a84aaa-ac61-11e3-b4a4-5c514f0e0550','document-create-doc','com.mths.client.page.DocumentCreateDoc',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Döküman Oluşturma','d9a9983c-ac61-11e3-b4a4-5c514f0e0550','document-create-match','com.mths.client.page.DocumentCreateMatch',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Döküman Oluşturma','d9aad524-ac61-11e3-b4a4-5c514f0e0550','document-create-meta','com.mths.client.page.DocumentCreateMeta',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Denetçi Ekle','d9ac0d9e-ac61-11e3-b4a4-5c514f0e0550','inspector-register','com.mths.client.page.InspectorRegister',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Sisteme Giriş','d9acf2fb-ac61-11e3-b4a4-5c514f0e0550','login','com.mths.client.page.Login',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Test Sayfası','d9ae12b5-ac61-11e3-b4a4-5c514f0e0550','test-page','com.mths.client.page.TestPage',1);
insert into mths.menu (objid,name,privilege_oid,friendly_url,menu_class,visible) 
values (uuid(),'Kullanıcı Kayıt','d9aef2af-ac61-11e3-b4a4-5c514f0e0550','user-register','com.mths.client.page.UserRegister',1);

insert into mths.role_privilege (objid,privilege_oid,role_oid)
values (uuid(),'d9a43657-ac61-11e3-b4a4-5c514f0e0550','d9afe36a-ac61-11e3-b4a4-5c514f0e0550'),
(uuid(),'d9acf2fb-ac61-11e3-b4a4-5c514f0e0550','d9afe36a-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege (objid,privilege_oid,role_oid)
values (uuid(),'d9a5d555-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550'),
(uuid(),'d9a71b64-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550'),
(uuid(),'d9ac0d9e-ac61-11e3-b4a4-5c514f0e0550','d9b0ce02-ac61-11e3-b4a4-5c514f0e0550');
insert into mths.role_privilege (objid,privilege_oid,role_oid)
values (uuid(),'d9aef2af-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550'),
(uuid(),'d9a84aaa-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550'),
(uuid(),'d9a9983c-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550'),
(uuid(),'d9aad524-ac61-11e3-b4a4-5c514f0e0550','d9b1c47d-ac61-11e3-b4a4-5c514f0e0550');

package com.jekirdek.client.template;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.html.Paragraph;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.jekirdek.client.constant.URLConstant;
import com.jekirdek.client.controller.CompanyController;
import com.jekirdek.client.controller.CompanyControllerAsync;
import com.jekirdek.client.controller.LoginMenuController;
import com.jekirdek.client.controller.LoginMenuControllerAsync;
import com.jekirdek.client.dto.AuthDataDTO;
import com.jekirdek.client.dto.MemberLoginDTO;
import com.jekirdek.client.page.AbstractPage;
import com.jekirdek.client.page.IPage;
import com.jekirdek.client.util.AsyncCall;
import com.jekirdek.client.util.ClientCacheUtil;
import com.jekirdek.client.util.PageUtil;

public class DashboardHeader extends AbstractPage implements IPage {

	private final LoginMenuControllerAsync	loginMenuController	= GWT.create(LoginMenuController.class);
	private final CompanyControllerAsync	companyController	= GWT.create(CompanyController.class);

	Anchor									popupLoginLogoutAnc, pushMenuAnc;
	Span									pageLoginUserSpan;
	Paragraph								popupLoginUser;

	// @UiField
	// Image logoImg;
	// @UiField
	// Div logoImgDiv;

	public DashboardHeader() {
		createComponents();
		initEventHandler();
		renderCompanyLogo();
		userLoginprocess();
	}

	private void createComponents() {

		// popupLoginLogoutAnc
		popupLoginLogoutAnc = new Anchor();
		popupLoginLogoutAnc.setStyleName("btn btn-default btn-flat");
		RootPanel.get("_popupLoginLogoutAnc").add(popupLoginLogoutAnc);

		// popupLoginUser
		popupLoginUser = new Paragraph();
		RootPanel.get("_popupLoginUser").add(popupLoginUser);

		// pageLoginUserSpan
		pageLoginUserSpan = new Span();
		RootPanel.get("_pageLoginUserSpan").add(pageLoginUserSpan);

		// pushMenuAnc
		pushMenuAnc = new Anchor();
		pushMenuAnc.setStyleName("sidebar-toggle");
		Span span = new Span();
		span.setStyleName("sr-only");
		span.getElement().setInnerText("Toggle navigation");
		pushMenuAnc.add(span);
		RootPanel.get("_pushMenuAnc").insert(pushMenuAnc, 0);

	}

	private void loadComponent() {
		final String companyOid = ClientCacheUtil.instance().getSessionUser().getSelectedCompanyOid();
		companyController.companyHasLogo(companyOid, new AsyncCall<Boolean>() {
			public void successCall(Boolean result) {
				// if (result) {
				// logoImg.setUrl(UrlUtil.generateLogoViewUrl(companyOid));
				// } else {
				// logoImg.setUrl("images/company_logo.png");
				// }
				// logoImgDiv.add(logoImg);
			};

		});
	}

	public void userLoginprocess() {
		String pageLoginUserTxt;
		if (ClientCacheUtil.instance().getUserLogin()) {
			popupLoginLogoutAnc.setText(messages.logout());
			pageLoginUserTxt = ClientCacheUtil.instance().getLoginUserName();
			popupLoginUser.setText(pageLoginUserTxt);
		} else {
			popupLoginLogoutAnc.setText("Üye Girişi");
			pageLoginUserTxt = "Üye Girişi";
			popupLoginUser.setText("Lütfen Giriş Yapınız.");
		}
		pageLoginUserSpan.clear();
		pageLoginUserSpan.setText(pageLoginUserTxt);
		// pageLoginUserSpan.add(new Icon(IconType.CARET_DOWN));
	}

	private void initEventHandler() {
		popupLoginLogoutAnc.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (ClientCacheUtil.instance().getUserLogin()) {
					loginMenuController.logout(new MemberLoginDTO(), new AsyncCall<AuthDataDTO>() {

						@Override
						public void successCall(AuthDataDTO result) {
							ClientCacheUtil.instance().loginProcess(result, null);
						}

					});
				} else {
					PageUtil.navigationManager.controlOpenPageByFUrl(URLConstant.MEMBER_LOGIN);
				}
			}
		});

		pushMenuAnc.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				pushMenu(Window.getClientWidth());
			}
		});
	}

	public native void pushMenu(int bwidth) /*-{
		//var screenSizes = this.options.screenSizes;
		//screenSizes.sm=768

		//Enable sidebar push menu
		if (bwidth > (767)) {
			$wnd.$("body").toggleClass('sidebar-collapse');
		}
		//Handle sidebar push menu for small screens
		else {
			if ($wnd.$("body").hasClass('sidebar-open')) {
				$wnd.$("body").removeClass('sidebar-open');
				$wnd.$("body").removeClass('sidebar-collapse')
			} else {
				$wnd.$("body").addClass('sidebar-open');
			}
		}
	}-*/;

	public void renderCompanyLogo() {
		// logoImgDiv.clear();
		loadComponent();
	}

	@Override
	public String pageName() {
		return null;
	}
}
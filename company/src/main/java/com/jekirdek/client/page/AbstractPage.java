package com.jekirdek.client.page;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.Modal;
import org.gwtbootstrap3.client.ui.ModalBody;
import org.gwtbootstrap3.client.ui.ModalHeader;
import org.gwtbootstrap3.client.ui.ModalSize;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.i18n.UIMessages;
import com.jekirdek.client.util.PageUtil;

public abstract class AbstractPage extends Composite {

	// protected DialogBox pagePopupDialog;

	public Logger		logger		= Logger.getLogger(this.getClass().getName());
	public UIMessages	messages	= GWT.create(UIMessages.class);

	Modal				modal		= null;

	public AbstractPage() {
		super();
		setPageName();
	}

	private void setPageName() {
		String pageName = pageName();
		if (pageName != null && PageUtil.pageName != null) {
			PageUtil.pageName.setFieldValue(pageName);
		}
	}

	public abstract String pageName();

	public Widget popupPage(String pageName) {
		GWT.log(pageName + " page open by the popup");
		Widget openedPage = PageUtil.navigationManager.createPage(pageName);

		modal = new Modal();
		modal.setId("modalId");
		modal.setClosable(true);
		modal.setDataBackdrop(ModalBackdrop.TRUE);
		modal.setFade(true);
		modal.setTitle(pageName);
		modal.setSize(ModalSize.MEDIUM);

		String title = "";
		if (openedPage instanceof AbstractPage)
			title = ((AbstractPage) openedPage).pageName();
		ModalHeader header = new ModalHeader();
		header.setTitle(title);

		ModalBody body = new ModalBody();
		body.add(openedPage);

		modal.add(header);
		modal.add(body);

		modal.show();

		return openedPage;
	}

	public void closeActivePopup() {
		if (modal != null)
			modal.hide();
	}

	@Override
	protected void onLoad() {
		logger.log(Level.INFO, logger.getName() + " Page loaded");
		super.onLoad();
	}

	@Override
	protected void onUnload() {
		logger.log(Level.INFO, logger.getName() + " Page unloaded");
		super.onUnload();
	}
}

package com.jekirdek.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class BlankPage extends AbstractPage implements IPage {

	private static BlankPageUiBinder	uiBinder	= GWT.create(BlankPageUiBinder.class);

	interface BlankPageUiBinder extends UiBinder<Widget, BlankPage> {
	}

	public BlankPage() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public String pageName() {
		return null;
	}
}
package com.jekirdek.client.template;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.jekirdek.client.component.Image;

public class SpinnerManager extends FlowPanel {

	PopupPanel popupPanel;

	private int asyncCallTimer = 0;

	@Override
	protected void onLoad() {
		super.onLoad();
	}

	public void showSpinner() {
		if (asyncCallTimer == 0) {
			popupPanel = new PopupPanel();
			popupPanel.setAutoHideEnabled(false);
			popupPanel.setGlassEnabled(true);
			popupPanel.setModal(true);
			Image spinner = new Image("images/spinner.gif");
			popupPanel.add(spinner);
			popupPanel.center();
		}
		asyncCallTimer++;
	}

	public void hideSpinner() {
		asyncCallTimer--;
		if (asyncCallTimer == 0)
			popupPanel.hide();
	}
}

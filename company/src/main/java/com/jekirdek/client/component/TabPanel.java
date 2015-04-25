package com.jekirdek.client.component;

public class TabPanel extends com.google.gwt.user.client.ui.TabPanel {

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}
}

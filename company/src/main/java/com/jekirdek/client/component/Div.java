package com.jekirdek.client.component;

public class Div extends com.google.gwt.user.client.ui.FlowPanel {

	public void setAddStyleName(String style) {
		super.addStyleName(style);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}
}

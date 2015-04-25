package com.jekirdek.client.component;

public class Anchor extends com.google.gwt.user.client.ui.Anchor {

	@Override
	public void setStyleName(String style) {
		super.setStyleName(style);
	}

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

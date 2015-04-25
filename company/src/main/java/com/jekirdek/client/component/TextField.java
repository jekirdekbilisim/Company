package com.jekirdek.client.component;

public class TextField extends com.google.gwt.user.client.ui.TextBox {

	public String getFieldValue() {
		return getText();
	}

	public void setFieldValue(String val) {
		setText(val);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}

	public void setAddStyleName(String style) {
		super.addStyleName(style);
	}
}

package com.jekirdek.client.component;

public class TextArea extends com.google.gwt.user.client.ui.TextArea {

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
}
package com.jekirdek.client.component;

import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordTextField extends PasswordTextBox {

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

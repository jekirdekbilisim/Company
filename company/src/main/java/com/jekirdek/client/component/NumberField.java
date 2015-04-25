package com.jekirdek.client.component;

import com.jekirdek.client.util.NumbersFieldKeyHandler;

public class NumberField extends TextField {

	public NumberField() {
		super();
		addKeyPressHandler(new NumbersFieldKeyHandler());
	}

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

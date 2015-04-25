package com.jekirdek.client.component;

public class Label extends com.google.gwt.user.client.ui.Label {

	private final String	defaultStyleName	= "default-label colorTitle";

	public Label() {
		super();
		addDefaultStyle();
	}

	public Label(String text) {
		super(text);
		addDefaultStyle();
	}

	private void addDefaultStyle() {
		addStyleName(defaultStyleName);
	}

	public String getFieldValue() {
		return getText();
	}

	public void setFieldValue(String text) {
		super.setText(text);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}
}

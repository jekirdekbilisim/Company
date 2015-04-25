package com.jekirdek.client.component;

import com.google.gwt.user.client.ui.SuggestOracle;

public class SuggestBox extends com.google.gwt.user.client.ui.SuggestBox {

	public SuggestBox() {
		super(new SuggestBoxData());
	}

	public SuggestBox(SuggestOracle oracle) {
		super(oracle);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}

}
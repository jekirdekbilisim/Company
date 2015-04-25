package com.jekirdek.client.component;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.InlineLabel;

public class Button extends com.google.gwt.user.client.ui.Button {

	public Button(String text, com.google.gwt.event.dom.client.ClickHandler clickHandler) {
		super("", clickHandler);
		addSpanName(text);
		setDefaulStyle();
	}

	public Button() {
		super();
		setDefaulStyle();
	}

	private void setDefaulStyle() {
		setStyleName("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only default-button");
	}

	private void addSpanName(String text) {
		InlineLabel span = new InlineLabel(text);
		span.setStyleName("ui-button-text");

		// Physical attach.
		DOM.appendChild(getElement(), span.getElement());
	}

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

	@Override
	public void setText(String text) {
		addSpanName(text);
	}

}

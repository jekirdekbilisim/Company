package com.jekirdek.client.component;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class Row extends ComplexPanel {

	private TableRowElement	row;

	public Row() {
		row = Document.get().createTRElement();
		setElement(row);
	}

	@Override
	public void add(Widget child) {
		add(child, (Element) row.cast());
	}

	public void setAddStyle(String style) {
		addStyleName(style);
	}

	public void setStyleName(String styleName) {
		super.setStyleName(styleName);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}
}

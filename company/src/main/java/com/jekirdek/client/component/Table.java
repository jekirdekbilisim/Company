package com.jekirdek.client.component;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class Table extends ComplexPanel {

	private TableElement	table;

	public Table() {
		table = Document.get().createTableElement();
		setElement(table);
		cellSpacing("5");
	}

	@Override
	public void add(Widget child) {
		add(child, (Element) table.cast());
	}

	public void setStyleName(String styleName) {
		super.setStyleName(styleName);
	}

	public void setAddStyleName(String style) {
		super.addStyleName(style);
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
	}

	public void cellSpacing(String space) {
		table.setAttribute("cellspacing", space);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}
}

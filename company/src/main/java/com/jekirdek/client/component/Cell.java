package com.jekirdek.client.component;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class Cell extends ComplexPanel {

	private TableCellElement	cell;

	public Cell() {
		cell = Document.get().createTDElement();
		setElement(cell);
		// vertical align middle
		setAddStyle("vAm");
	}

	@Override
	public void add(Widget child) {
		add(child, (Element) cell.cast());
	}

	public void setColSpan(String colSpan) {
		cell.setAttribute("colspan", colSpan);
	}

	public void setAddStyle(String style) {
		addStyleName(style);
	}
}

package com.jekirdek.client.test;

import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.user.client.ui.Composite;

public class FileInputGroupButton extends Composite {

	private Span	wrapper;

	public FileInputGroupButton(FileInputButton input) {

		wrapper = new Span();

		wrapper.setStyleName(Styles.INPUT_GROUP_BTN);

		wrapper.add(input);

		initWidget(wrapper);

	}

}

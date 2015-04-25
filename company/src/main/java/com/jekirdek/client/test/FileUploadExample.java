package com.jekirdek.client.test;

import org.gwtbootstrap3.client.ui.InputGroup;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.jekirdek.client.component.FileUploaded;

public class FileUploadExample {

	public void example() {
		FileInputButton one = new FileInputButton(ButtonType.PRIMARY, false);
		one.setText("Select ONE");
		one.setIcon(IconType.UPLOAD);

		FileInputButton two = new FileInputButton(ButtonType.DANGER, true);
		two.setIcon(IconType.PLUS);
		two.setText("Select MANY");

		InputGroup ig = new InputGroup();

		final TextBox tb = new TextBox();

		tb.setEnabled(false);

		FileInputButton three = new FileInputButton(ButtonType.DEFAULT, false);
		three.setText("Browse...");
		three.addValueChangeHandler(new ValueChangeHandler<JsArray<FileUploaded>>() {

			@Override
			public void onValueChange(ValueChangeEvent<JsArray<FileUploaded>> event) {

				tb.setText("");

				if (event.getValue() != null && event.getValue().length() > 0) {

					tb.setText(event.getValue().get(0).getName());

				}

			}

		});

		ig.add(new FileInputGroupButton(three));
		ig.add(tb);

		RootPanel.get().add(one);
		RootPanel.get().add(two);
		RootPanel.get().add(ig);

	}
}

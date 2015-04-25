package com.jekirdek.client.test;

import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Span;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.jekirdek.client.component.FileUploaded;

public class FileInputButton extends Composite implements HasValueChangeHandlers<JsArray<FileUploaded>> {

	private Span		wrapper;

	private Span		textSpan;

	private FileUpload	upload;

	private Icon		icon;

	public FileInputButton(boolean isMultiple) {

		this(null, isMultiple);

	}

	public FileInputButton(ButtonType additionalStyle, boolean isMultiple) {

		wrapper = new Span();

		wrapper.addStyleName(Styles.BTN);

		if (additionalStyle != null) {

			wrapper.addStyleName(additionalStyle.getCssName());

		}

		wrapper.addStyleName("btn-file");

		initWidget(wrapper);

		upload = new FileUpload();

		if (isMultiple) {

			upload.getElement().setPropertyBoolean("multiple", true);

		}

		upload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				fireChanged();

			}

		});

		wrapper.add(upload);

	}

	public void setText(String text) {

		if (textSpan == null) {

			textSpan = new Span(text);

			wrapper.add(textSpan);

		} else {

			textSpan.setText(text);

		}

	}

	public void setIcon(IconType type) {

		if (icon == null) {

			icon = new Icon(type);

			wrapper.add(icon);

		} else {

			icon.setType(type);

		}

	}

	private void fireChanged() {

		ValueChangeEvent.fire(this, getFiles(upload.getElement()));

	}

	private native JsArray<FileUploaded> getFiles(Element el) /*-{

		if (el.files) {

			return el.files;

		} else {

			return null;

		}

	}-*/;

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<JsArray<FileUploaded>> handler) {

		return super.addHandler(handler, ValueChangeEvent.getType());
	}
}
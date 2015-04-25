package com.jekirdek.client.component;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Widget;
import com.jekirdek.client.util.FileAsyncCall;
import com.jekirdek.client.util.UrlUtil;

public class FileInput extends Composite implements HasValueChangeHandlers<JsArray<FileUploaded>> {

	private static FileInputUiBinder	uiBinder	= GWT.create(FileInputUiBinder.class);

	interface FileInputUiBinder extends UiBinder<Widget, FileInput> {
	}

	@UiField
	FormPanel		formPanel;
	@UiField
	TextBox			fileInputTxt;
	@UiField
	FileUpload		fileUpload;
	@UiField
	Button			uploadBtn;

	private String	fileSessionKey;

	public FileInput() {
		initWidget(uiBinder.createAndBindUi(this));

		fileUpload.setName("fileUpload");

		formPanel.setAction(UrlUtil.generateFileUploadUrl(generateFileSessionKey()));
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);

		formPanel.addSubmitHandler(new SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				if (!controlFileBeforeSubmit(event)) {
					event.cancel();
				}
			}
		});

		uploadBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				formPanel.submit();
			}
		});

		fileUpload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				fireChanged();
			}
		});

	}

	private String generateFileSessionKey() {
		return fileSessionKey = System.currentTimeMillis() + "";
	}

	public HandlerRegistration addCompletedCallback(final FileAsyncCall<SubmitCompleteEvent> callback) {
		return formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				callback.successCall(event);
			}
		});
	}

	private boolean controlFileBeforeSubmit(SubmitEvent event) {
		if ("".equals(fileUpload.getFilename())) {
			Window.alert("No file selected");
			return false;
		}
		return true;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<JsArray<FileUploaded>> handler) {
		return super.addHandler(handler, ValueChangeEvent.getType());
	}

	private void fireChanged() {

		JsArray<FileUploaded> fileArray = getFiles(fileUpload.getElement());
		if (fileArray != null && fileArray.length() > 0) {
			uploadBtn.setEnabled(true);
			fileInputTxt.setText(fileArray.get(0).getName());
		} else {
			fileInputTxt.setText("");
			uploadBtn.setEnabled(false);
		}
		ValueChangeEvent.fire(this, fileArray);
	}

	private native JsArray<FileUploaded> getFiles(Element el) /*-{

		if (el.files) {

			return el.files;

		} else {

			return null;

		}

	}-*/;

	public String getFileSessionKey() {
		return fileSessionKey;
	}

	public void setFileSessionKey(String fileSessionKey) {
		this.fileSessionKey = fileSessionKey;
	}

	public FormPanel getUploadFileForm() {
		return formPanel;
	}

	public Button getUploadBtn() {
		return uploadBtn;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}
}

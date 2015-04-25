package com.jekirdek.client.component;

public class Image extends com.google.gwt.user.client.ui.Image {

	public Image() {
		super();
	}

	public Image(String url) {
		super(url);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}

	public void setAddStyleName(String style) {
		super.addStyleName(style);
	}
}

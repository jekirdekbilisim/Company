package com.jekirdek.client.component;

import com.google.gwt.uibinder.client.UiConstructor;

public class ImageButton extends Button {

	private Image image;
	private String src;
	private String height;
	private String width;

	public ImageButton() {
		super();
		createAndAppendImage();
	}

	private void createAndAppendImage() {
		image = new Image();
		getElement().appendChild(image.getElement());
	}

	@UiConstructor
	public ImageButton(String src) {
		this();
		setSrc(src);
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		image.setUrl(src);
		this.src = src;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		image.setHeight(height);
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		image.setWidth(width);
		this.width = width;
	}
}

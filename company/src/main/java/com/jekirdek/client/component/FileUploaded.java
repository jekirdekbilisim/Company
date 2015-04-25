package com.jekirdek.client.component;

import com.google.gwt.core.client.JavaScriptObject;

public class FileUploaded extends JavaScriptObject {

	protected FileUploaded() {

	}

	public final native int getSize() /*-{

		return this.fileSize != null ? this.fileSize : this.size;

	}-*/;

	public final native String getName() /*-{

		return this.fileName != null ? this.fileName : this.name;

	}-*/;

	public final native String getType() /*-{

		return this.type;

	}-*/;

}
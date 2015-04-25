package com.jekirdek.client.component;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;

public class DialogBox extends com.google.gwt.user.client.ui.DialogBox {

	public DialogBox() {
		super();
		setAnimationEnabled(false);
		setGlassEnabled(true);
	}

	@Override
	protected void onPreviewNativeEvent(NativePreviewEvent event) {
		if (!event.isCanceled() && event.getTypeInt() == Event.ONKEYDOWN && event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			this.hide();
		}
		super.onPreviewNativeEvent(event);
	}

}

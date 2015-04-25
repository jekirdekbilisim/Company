package com.jekirdek.client.util;

import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlPosition;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

public abstract class FileAsyncCall<T> {

	public abstract void successCall(T result);

	public void failCall(Throwable caught) {
		GrowlOptions go = new GrowlOptions();
		go.setPosition(GrowlPosition.TOP_CENTER);
		go.setType(GrowlType.WARNING);
		go.setIconType(IconType.WARNING.getCssName());
		Growl.growl("HATA", "İşlem sırasında bir hata ile karşılaşıldı. Lütfen Tekrar deneyiniz.", go);
		// Bootbox.alert("İşlem sırasında bir hata ile karşılaşıldı. Lütfen Tekrar deneyiniz.");
	}
}

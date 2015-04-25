package com.jekirdek.client.component;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public interface SuggestBoxComponent extends Suggestion {

	/**
	 * 
	 * @return key of listitem
	 */
	String getSelectedInputId();
}
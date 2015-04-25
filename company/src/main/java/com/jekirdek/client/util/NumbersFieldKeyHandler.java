package com.jekirdek.client.util;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.jekirdek.client.component.NumberField;

public class NumbersFieldKeyHandler implements KeyPressHandler {

	@Override
	public void onKeyPress(KeyPressEvent event) {

		if (event.getNativeEvent().getKeyCode() != KeyCodes.KEY_DELETE && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE
				&& event.getNativeEvent().getKeyCode() != KeyCodes.KEY_LEFT && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_RIGHT) {
			String c = event.getCharCode() + "";
			if (RegExp.compile("[^0-9]").test(c))
				((NumberField) event.getSource()).cancelKey();
		}
	}
}

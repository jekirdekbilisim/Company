package com.jekirdek.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jekirdek.client.component.Button;
import com.jekirdek.client.component.DialogBox;
import com.jekirdek.client.component.Label;

public class MessageBox extends DialogBox {

	public MessageBox(String content) {
		setText("Bilgi Mesajı");
		final VerticalPanel panel = new VerticalPanel();
		panel.add(new Label(content));
		final Button buttonClose = new Button("Close", new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		buttonClose.setWidth("90px");
		panel.add(buttonClose);
		panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_RIGHT);
		add(panel);
		center();
	}

}
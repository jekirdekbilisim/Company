package com.jekirdek.client.component;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class ButtonImageCell extends ButtonCell {

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, SafeHtml data, SafeHtmlBuilder sb) {
		// super.render(context, data, sb);
		SafeHtml html = SafeHtmlUtils.fromTrustedString(new Image(data.asString()).toString());
		sb.append(html);
	}

}

package com.jekirdek.client.res;

import com.google.gwt.user.cellview.client.CellTable;

public interface GridDataResource extends CellTable.Resources {
	@Source({ CellTable.Style.DEFAULT_CSS, "com/jekirdek/client/res/grid-data.css" })
	GridDataStyle cellTableStyle();

	interface GridDataStyle extends CellTable.Style {
	}
}

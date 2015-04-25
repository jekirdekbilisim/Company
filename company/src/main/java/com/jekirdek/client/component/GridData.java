package com.jekirdek.client.component;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.jekirdek.client.res.GridDataResource;

public class GridData<T> extends com.google.gwt.user.cellview.client.CellTable<T> {

	private T t;
	private ListDataProvider<T> dataProvider = new ListDataProvider<T>();

	// CellTable custom UI resource
	private static CellTable.Resources gridDataRes = GWT.create(GridDataResource.class);

	private String DEFAULT_EMPTY_TEXT = "Kayıt Bulunmamaktadır";
	private static int DEFAULT_PAGING_SIZE = 10;

	public GridData() {
		super(DEFAULT_PAGING_SIZE, gridDataRes);
		initialGrid();
		setEmptyGridLabel(DEFAULT_EMPTY_TEXT);
	}

	public GridData(int pagingSize) {

	}

	private void initialGrid() {
		dataProvider.addDataDisplay(this);
	}

	@Override
	public void setEmptyTableWidget(Widget widget) {
		super.setEmptyTableWidget(widget);
	}

	private void setEmptyGridLabel(String DEFAULT_EMPTY_TEXT) {
		setEmptyTableWidget(new Label(DEFAULT_EMPTY_TEXT));
	}

	public void putData(T t1) {
		dataProvider.getList().add(t1);
	}

	public void putDataList(List<T> tList) {
		if (tList != null && !tList.isEmpty())
			dataProvider.getList().addAll(tList);
	}

	public void clear() {
		dataProvider.getList().clear();
	}

	public void remove(T t1) {
		dataProvider.getList().remove(t1);
	}

	public ListDataProvider<T> getDataProvider() {
		return dataProvider;
	}

	public List<T> getList() {
		return dataProvider.getList();
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}

	public T getT() {
		return t;
	}

}

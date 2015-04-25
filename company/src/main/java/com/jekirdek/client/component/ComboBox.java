package com.jekirdek.client.component;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.uibinder.client.UiConstructor;
import com.jekirdek.client.util.ListItem;

public class ComboBox extends com.google.gwt.user.client.ui.ListBox {

	public ComboBox() {
		super();
		super.addItem("Lütfen Seçiniz", "");
	}

	@UiConstructor
	public ComboBox(boolean isMultipleSelect) {
		super(isMultipleSelect);

		if (!isMultipleSelect)
			super.addItem("Lütfen Seçiniz", "");
	}

	public List<ListItem> getAllItem() {
		List<ListItem> itemList = new ArrayList<ListItem>();
		for (int i = 0; i < getItemCount(); i++) {
			itemList.add(new ListItem(getValue(i), getItemText(i)));
		}
		return itemList;
	}

	public void setFieldValue(String key) {
		for (int i = 0; i < getItemCount(); i++) {
			if (getValue(i).equals(key))
				setSelectedIndex(i);
		}
	}

	public String getFieldValue() {
		return getValue(getSelectedIndex());
	}

	@Override
	public void addItem(String value, String key) {
		addItem(value, key, true);
	}

	public void addItem(String value, String key, boolean fireChangeHandler) {
		super.addItem(value, key);
		if (fireChangeHandler) {
			fireChangeHandler();
		}
	}

	public void addItemList(List<ListItem> result) {

		if (result != null && !result.isEmpty()) {
			// Push the data into the widget.
			for (ListItem listItem : result) {
				addItem(listItem.getValue(), listItem.getKey(), false);
			}
			fireChangeHandler();
		}

	}

	private void fireChangeHandler() {
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
	}

	public List<ListItem> getSelectedItemList() {
		List<ListItem> itemList = new ArrayList<ListItem>();
		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i))
				itemList.add(new ListItem(getValue(i), getItemText(i)));
		}
		return itemList;
	}

	public void removeItemList(List<ListItem> listItemList) {
		if (listItemList == null) {
			return;
		}
		for (ListItem listItem : listItemList) {
			removeItem(listItem);
		}
	}

	public void removeItem(ListItem listItem) {
		if (listItem == null) {
			return;
		}
		int selectedIndex = -1;
		for (int i = 0; i < getItemCount(); i++) {
			if (listItem.getKey().equals(getValue(i)))
				selectedIndex = i;
		}
		if (selectedIndex != -1)
			removeItem(selectedIndex);
	}

	@Override
	public void clear() {
		super.clear();

		if (!isMultipleSelect())
			super.addItem("Lütfen Seçiniz", "");
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}
}

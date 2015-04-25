package com.jekirdek.client.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gwtbootstrap3.extras.select.client.ui.Option;
import org.gwtbootstrap3.extras.select.client.ui.Select;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.DomEvent;
import com.jekirdek.client.util.ListItem;

public class Combo extends Select {

	HashMap<String, ListItem>	sourceMap	= new HashMap<>();

	public Combo() {
		super();
	}

	public ListItem getSelectedItem() {
		return sourceMap.get(getValue());
	}

	public String getSelectedItemKey() {
		ListItem item = getSelectedItem();
		return item != null ? item.getKey() : null;
	}

	public void setSelectedItem(ListItem item) {
		if (item == null || item.getValue() == null)
			GWT.log("Select component could not be set null value");
		setValue(item.getValue());
	}

	public void setSelectedItem(String key) {

		if (key == null)
			GWT.log("Select component could not be set null key");

		ListItem item = null;
		for (String keyStr : sourceMap.keySet()) {
			if (key.equals(sourceMap.get(keyStr).getKey()))
				item = sourceMap.get(keyStr);
		}

		if (item == null) {
			GWT.log("Could not found item with key : " + key);
			return;
		}
		setValue(item.getValue());
	}

	public void addItemList(List<ListItem> itemList) {
		if (itemList == null)
			return;
		for (ListItem listItem : itemList) {
			addItem(listItem);
		}
	}

	public void addItem(ListItem item) {
		addItem(item.getValue(), item.getKey());
	}

	public void addItem(String value, String key) {
		addItem(value, key, true);
	}

	public void addItem(String value, String key, boolean fireChangeHandler) {
		sourceMap.put(value, new ListItem(key, value));
		Option option = new Option();
		option.setText(value);
		add(option);
		if (fireChangeHandler) {
			fireChangeHandler();
		}
		refresh();
	}

	public List<ListItem> getSelectedItemList() {
		List<ListItem> itemList = new ArrayList<ListItem>();
		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i))
				itemList.add(sourceMap.get(getValue(i)));
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
		sourceMap.remove(listItem);
		int selectedIndex = -1;
		for (int i = 0; i < getItemCount(); i++) {
			if (listItem.getKey().equals(getValue(i)))
				selectedIndex = i;
		}
		if (selectedIndex != -1)
			remove(selectedIndex);
		refresh();
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}

	private void fireChangeHandler() {
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), this);
	}
}

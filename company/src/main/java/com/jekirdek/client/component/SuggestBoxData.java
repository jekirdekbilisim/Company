package com.jekirdek.client.component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.SuggestOracle;
import com.jekirdek.client.util.ClientConvertUtil;
import com.jekirdek.client.util.ListItem;

public class SuggestBoxData extends SuggestOracle {

	private List<ListItem> dataItems = new ArrayList<ListItem>();

	public SuggestBoxData() {
		super();
	}

	public SuggestBoxData(List<ListItem> data) {
		this.dataItems = data;
	}

	@Override
	public void requestSuggestions(final Request request, final Callback callback) {

		final String userInput = ClientConvertUtil.convertUpperCaseTurkish(request.getQuery());
		List<SuggestBoxComponent> suggestions = new LinkedList<SuggestBoxComponent>();
		for (final ListItem item : dataItems) {
			if (item.getValue().contains(userInput)) {
				suggestions.add(new SuggestBoxComponent() {

					@Override
					public String getSelectedInputId() {
						return item.getKey();
					}

					@Override
					public String getDisplayString() {
						return item.getValue();
					}

					@Override
					public String getReplacementString() {
						return item.getValue();
					}
				});
			}
		}
		Response response = new Response(suggestions);
		callback.onSuggestionsReady(request, response);
	}

	public void clear() {
		dataItems.clear();
	}

	public void add(ListItem item) {
		dataItems.add(item);
	}

	public void add(List<ListItem> items) {
		dataItems.addAll(items);
	}

}

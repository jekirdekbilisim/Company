package com.jekirdek.client.page;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class TestPage extends AbstractPage implements IPage {

	private static testPageUiBinder	uiBinder	= GWT.create(testPageUiBinder.class);

	interface testPageUiBinder extends UiBinder<Widget, TestPage> {
	}

	@UiField
	CellTable<Contact>	cellTable, cellTable2;

	public TestPage() {
		initWidget(uiBinder.createAndBindUi(this));

		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		// Add a text column to show the name.
		TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.getName();
			}
		};
		cellTable.addColumn(nameColumn, "Name");

		// Last name.
		Column<Contact, String> lastNameColumn = new Column<Contact, String>(new EditTextCell()) {
			@Override
			public String getValue(Contact object) {
				return object.getSurname();
			}
		};
		lastNameColumn.setSortable(true);

		cellTable.addColumn(lastNameColumn, "Surname");
		cellTable.setColumnWidth(lastNameColumn, 20, Unit.PCT);

		// Add a date column to show the birthday.
		DateCell dateCell = new DateCell();
		Column<Contact, Date> dateColumn = new Column<Contact, Date>(dateCell) {
			@Override
			public Date getValue(Contact object) {
				return object.getBirthday();
			}
		};
		cellTable.addColumn(dateColumn, "Birthday");

		// Add a text column to show the address.
		TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
			@Override
			public String getValue(Contact object) {
				return object.getAddress();
			}
		};
		cellTable.addColumn(addressColumn, "Address");

		// Add a selection model to handle user selection.
		final SingleSelectionModel<Contact> selectionModel = new SingleSelectionModel<Contact>();
		cellTable.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				Contact selected = selectionModel.getSelectedObject();
				if (selected != null) {
					// Window.alert("You selected: " + selected.name);
				}
			}
		});

		// Set the total row count. This isn't strictly necessary,
		// but it affects paging calculations, so its good habit to
		// keep the row count up to date.
		cellTable.setRowCount(CONTACTS.size(), true);

		// Push the data into the widget.
		cellTable.setRowData(CONTACTS);

		final ProvidesKey<Contact> KEY_PROVIDER = new ProvidesKey<Contact>() {
			@Override
			public Object getKey(Contact item) {
				return item == null ? null : item.getName();
			}
		};

		Column<Contact, Boolean> checkColumn = new Column<Contact, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(Contact object) {
				// Get the value from the selection model.
				return selectionModel.isSelected(object);
			}
		};

		// Add a selection model to handle user selection.
		final MultiSelectionModel<Contact> multiSelectionModel = new MultiSelectionModel<Contact>();
		multiSelectionModel.addSelectionChangeHandler(new Handler() {

			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				multiSelectionModel.getSelectedSet();
			}
		});
		cellTable2.setSelectionModel(multiSelectionModel);
		cellTable2.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
		cellTable2.setColumnWidth(checkColumn, 40, Unit.PX);
		cellTable2.addColumn(nameColumn, "Name");

		// Push the data into the widget.
		cellTable2.setRowData(CONTACTS);
	}

	private static class Contact {
		private String	address;
		private Date	birthday;
		private String	name;
		private String	surname;

		public Contact(String name, String surname, Date birthday, String address) {
			this.name = name;
			this.birthday = birthday;
			this.address = address;
			this.surname = surname;
		}

		public String getAddress() {
			return address;
		}

		public Date getBirthday() {
			return birthday;
		}

		public String getName() {
			return name;
		}

		public String getSurname() {
			return surname;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((address == null) ? 0 : address.hashCode());
			result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((surname == null) ? 0 : surname.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Contact other = (Contact) obj;
			if (address == null) {
				if (other.address != null)
					return false;
			} else if (!address.equals(other.address))
				return false;
			if (birthday == null) {
				if (other.birthday != null)
					return false;
			} else if (!birthday.equals(other.birthday))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (surname == null) {
				if (other.surname != null)
					return false;
			} else if (!surname.equals(other.surname))
				return false;
			return true;
		}

	}

	/**
	 * The list of data to display.
	 */
	private static final List<Contact>	CONTACTS	= Arrays.asList(
															new Contact("John", "testsur", new Date(80, 4, 12), "123 Fourth Avenue"),
															new Contact("Joe", "deep", new Date(85, 2, 22), "22 Lance Ln"), new Contact(
																	"George", "upper", new Date(46, 6, 6), "1600 Pennsylvania Avenue"));

	@Override
	public String pageName() {
		return messages.TestPage_pageName();
	}
}
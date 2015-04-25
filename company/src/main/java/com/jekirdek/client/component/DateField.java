package com.jekirdek.client.component;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.datepicker.client.DateBox;
import com.jekirdek.client.constant.DocumentConstant;

public class DateField extends DateBox {

	public DateField() {
		super();
		addValueChangeHandler();
		setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(DocumentConstant.META_DATE_FORMAT)));

	}

	@UiConstructor
	public DateField(boolean timeDetailFormat) {
		super();
		addValueChangeHandler();

		if (timeDetailFormat)
			setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(DocumentConstant.META_DATE_TIME_FORMAT)));
		else
			setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat(DocumentConstant.META_DATE_FORMAT)));

	}

	private void addValueChangeHandler() {
		addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				setValue(setHourMinuteSecond(event.getValue()));
			}
		});
	}

	public String getFieldValueFormat() {
		if (getFieldValue() == null)
			return null;
		DateTimeFormat format = DateTimeFormat.getFormat(DocumentConstant.META_DATE_TIME_FORMAT);
		return format.format(getFieldValue());
	}

	public Date getFieldValue() {
		return getValue();
	}

	public void setFieldValue(Date date) {
		setValue(date);
	}

	public String getComponentId() {
		return getElement().getId();
	}

	public void setComponentId(String id) {
		getElement().setId(id);
	}

	private Date setHourMinuteSecond(Date selectedDate) {
		Date date = selectedDate;
		date.setHours((new Date()).getHours());
		date.setMinutes((new Date()).getMinutes());
		date.setSeconds((new Date()).getSeconds());
		return date;
	}

	public void setAddStyleName(String style) {
		super.addStyleName(style);
	}
}

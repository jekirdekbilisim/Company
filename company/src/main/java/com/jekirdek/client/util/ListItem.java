package com.jekirdek.client.util;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ListItem implements IsSerializable, Serializable {

	private static final long	serialVersionUID	= -2951797343627542453L;

	private String				key;
	private String				value;

	public ListItem(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public ListItem() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ListItem other = (ListItem) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ListItem [key=" + key + ", value=" + value + "]";
	}

}

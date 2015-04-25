package com.jekirdek.client.util;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MenuItem implements IsSerializable, Serializable {

	private static final long serialVersionUID = 8580769526692916183L;

	private String name;
	private String friendlyUrl;
	private String className;
	private Boolean visible;

	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(String name, String friendlyUrl, String className, Boolean visible) {
		super();
		this.name = name;
		this.friendlyUrl = friendlyUrl;
		this.className = className;
		this.visible = visible;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFriendlyUrl() {
		return friendlyUrl;
	}

	public void setFriendlyUrl(String friendlyUrl) {
		this.friendlyUrl = friendlyUrl;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((friendlyUrl == null) ? 0 : friendlyUrl.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
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
		MenuItem other = (MenuItem) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (friendlyUrl == null) {
			if (other.friendlyUrl != null)
				return false;
		} else if (!friendlyUrl.equals(other.friendlyUrl))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuItem [name=" + name + ", friendlyUrl=" + friendlyUrl + ", className=" + className + ", visible=" + visible + "]";
	}

}

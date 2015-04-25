package com.jekirdek.client.dto;

public class UserDTO extends BasicDTO {

	private static final long serialVersionUID = 6122600192633209619L;

	private String userTCKN;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userCellPhone;
	private String userPhone;
	private String userPhoneInternal;
	private String userCountry;
	private String userCity;
	private String userDistrict;
	private String userAddress;

	public String getUserTCKN() {
		return userTCKN;
	}

	public void setUserTCKN(String userTCKN) {
		this.userTCKN = userTCKN;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCellPhone() {
		return userCellPhone;
	}

	public void setUserCellPhone(String userCellPhone) {
		this.userCellPhone = userCellPhone;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPhoneInternal() {
		return userPhoneInternal;
	}

	public void setUserPhoneInternal(String userPhoneInternal) {
		this.userPhoneInternal = userPhoneInternal;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserDistrict() {
		return userDistrict;
	}

	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userTCKN == null) ? 0 : userTCKN.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userCellPhone == null) ? 0 : userCellPhone.hashCode());
		result = prime * result + ((userPhone == null) ? 0 : userPhone.hashCode());
		result = prime * result + ((userPhoneInternal == null) ? 0 : userPhoneInternal.hashCode());
		result = prime * result + ((userCountry == null) ? 0 : userCountry.hashCode());
		result = prime * result + ((userCity == null) ? 0 : userCity.hashCode());
		result = prime * result + ((userDistrict == null) ? 0 : userDistrict.hashCode());
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (userTCKN == null) {
			if (other.userTCKN != null)
				return false;
		} else if (!userTCKN.equals(other.userTCKN))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userCellPhone == null) {
			if (other.userCellPhone != null)
				return false;
		} else if (!userCellPhone.equals(other.userCellPhone))
			return false;
		if (userPhone == null) {
			if (other.userPhone != null)
				return false;
		} else if (!userPhone.equals(other.userPhone))
			return false;
		if (userPhoneInternal == null) {
			if (other.userPhoneInternal != null)
				return false;
		} else if (!userPhoneInternal.equals(other.userPhoneInternal))
			return false;
		if (userCountry == null) {
			if (other.userCountry != null)
				return false;
		} else if (!userCountry.equals(other.userCountry))
			return false;
		if (userCity == null) {
			if (other.userCity != null)
				return false;
		} else if (!userCity.equals(other.userCity))
			return false;
		if (userDistrict == null) {
			if (other.userDistrict != null)
				return false;
		} else if (!userDistrict.equals(other.userDistrict))
			return false;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
			return false;
		return true;
	}

}

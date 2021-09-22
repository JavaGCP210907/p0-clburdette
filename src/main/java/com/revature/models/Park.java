package com.revature.models;

public class Park {

	int park_id;
	String parkName;
	String streetName;
	String city;
	String zipcode;
	boolean shady;
	boolean barkPark;
	boolean playFields;
	boolean walkingTrails;
	
	public Park() {
		super();
	}

	public Park(String parkName, String streetName, String city, String zipcode, boolean shady, boolean barkPark,
			boolean playFields, boolean walkingTrails) {
		super();
		this.parkName = parkName;
		this.streetName = streetName;
		this.city = city;
		this.zipcode = zipcode;
		this.shady = shady;
		this.barkPark = barkPark;
		this.playFields = playFields;
		this.walkingTrails = walkingTrails;
	}

	public Park(int park_id, String parkName, String streetName, String city, String zipcode, boolean shady,
			boolean barkPark, boolean playFields, boolean walkingTrails) {
		super();
		this.park_id = park_id;
		this.parkName = parkName;
		this.streetName = streetName;
		this.city = city;
		this.zipcode = zipcode;
		this.shady = shady;
		this.barkPark = barkPark;
		this.playFields = playFields;
		this.walkingTrails = walkingTrails;
	}

	public int getPark_id() {
		return park_id;
	}

	public void setPark_id(int park_id) {
		this.park_id = park_id;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public boolean isShady() {
		return shady;
	}

	public void setShady(boolean shady) {
		this.shady = shady;
	}

	public boolean isBarkPark() {
		return barkPark;
	}

	public void setBarkPark(boolean barkPark) {
		this.barkPark = barkPark;
	}

	public boolean isPlayFields() {
		return playFields;
	}

	public void setPlayFields(boolean playFields) {
		this.playFields = playFields;
	}

	public boolean isWalkingTrails() {
		return walkingTrails;
	}

	public void setWalkingTrails(boolean walkingTrails) {
		this.walkingTrails = walkingTrails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (barkPark ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + park_id;
		result = prime * result + (playFields ? 1231 : 1237);
		result = prime * result + (shady ? 1231 : 1237);
		result = prime * result + ((parkName == null) ? 0 : parkName.hashCode());
		result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
		result = prime * result + (walkingTrails ? 1231 : 1237);
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
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
		Park other = (Park) obj;
		if (barkPark != other.barkPark)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (park_id != other.park_id)
			return false;
		if (playFields != other.playFields)
			return false;
		if (shady != other.shady)
			return false;
		if (parkName == null) {
			if (other.parkName != null)
				return false;
		} else if (!parkName.equals(other.parkName))
			return false;
		if (streetName == null) {
			if (other.streetName != null)
				return false;
		} else if (!streetName.equals(other.streetName))
			return false;
		if (walkingTrails != other.walkingTrails)
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Park [park_id=" + park_id + ", parkName=" + parkName + ", streetName=" + streetName
				+ ", city=" + city + ", zipcode=" + zipcode + ", shady=" + shady + ", barkPark=" + barkPark
				+ ", playFields=" + playFields + ", walkingTrails=" + walkingTrails + "]";
	}
	
	
}

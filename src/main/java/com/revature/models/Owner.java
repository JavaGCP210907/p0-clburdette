package com.revature.models;

public class Owner {
	int owner_id;
	String f_name;
	String l_name;
	int zipcode;
	int homepark_fk;
	
	public Owner() {
		super();
	}

	public Owner(String f_name, String l_name, int zipcode, int homepark_fk) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
		this.zipcode = zipcode;
		this.homepark_fk = homepark_fk;
	}

	public Owner(int owner_id, String f_name, String l_name, int zipcode, int homepark_fk) {
		super();
		this.owner_id = owner_id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.zipcode = zipcode;
		this.homepark_fk = homepark_fk;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public int getHomepark_fk() {
		return homepark_fk;
	}

	public void setHomepark_fk(int homepark_fk) {
		this.homepark_fk = homepark_fk;
	}

	@Override
	public String toString() {
		return "Owner [owner_id=" + owner_id + ", f_name=" + f_name + ", l_name=" + l_name + ", zipcode=" + zipcode
				+ ", homepark_fk=" + homepark_fk + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f_name == null) ? 0 : f_name.hashCode());
		result = prime * result + homepark_fk;
		result = prime * result + ((l_name == null) ? 0 : l_name.hashCode());
		result = prime * result + owner_id;
		result = prime * result + zipcode;
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
		Owner other = (Owner) obj;
		if (f_name == null) {
			if (other.f_name != null)
				return false;
		} else if (!f_name.equals(other.f_name))
			return false;
		if (homepark_fk != other.homepark_fk)
			return false;
		if (l_name == null) {
			if (other.l_name != null)
				return false;
		} else if (!l_name.equals(other.l_name))
			return false;
		if (owner_id != other.owner_id)
			return false;
		if (zipcode != other.zipcode)
			return false;
		return true;
	}
	
	
}

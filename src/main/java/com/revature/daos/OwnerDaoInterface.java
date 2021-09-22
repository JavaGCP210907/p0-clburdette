package com.revature.daos;

import java.util.List;

import com.revature.models.Owner;

public interface OwnerDaoInterface {
	public List <Owner> getOwners();
	public List <Owner> getOwnersByName(String fname, String lname);
	public List <Owner> getOwnersByZip(String zipcode);
	public List <Owner> getOwnersByHomePark(int park);
	public void addOwner(Owner owner);
}

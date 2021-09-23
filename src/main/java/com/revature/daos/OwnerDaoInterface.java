package com.revature.daos;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Owner;

public interface OwnerDaoInterface {
	public List <Owner> getOwners();
	public Owner getOwnerByID(int id);
	public List <Owner> getOwnersByName(String fname, String lname);
	public List <Owner> getOwnersByZip(String zipcode);
	public List <Owner> getOwnersByHomePark(int park);
	public void addOwner(Owner owner);
	public void updateOwner(Owner owner);
	public void deleteOwner(Scanner scan);
}

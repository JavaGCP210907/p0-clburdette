package com.revature.daos;

import java.util.List;

import com.revature.models.Park;

public interface ParkDaoInterface {
	public List <Park> getParks();
	public List <Park> getParksByName(String name);
	public List <Park> getParksByCity(String city);
	public List <Park> getParksByZip(String zipcode);
	public List <Park> getParksByAmenities(boolean[] choices);
	public void addPark(Park park);
}

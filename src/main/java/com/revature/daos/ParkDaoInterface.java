package com.revature.daos;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Park;

public interface ParkDaoInterface {
	public List <Park> getParks();
	public Park getParkByID(int id);
	public List <Park> getParksByName(String name);
	public List <Park> getParksByCity(String city);
	public List <Park> getParksByZip(String zipcode);
	public List <Park> getParksByAmenities(boolean[] choices);
	public void addPark(Park park);
	public void updatePark(Park park);
	public void deletePark(Scanner scan);
}

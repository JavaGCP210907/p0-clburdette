package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.ParkDao;

public class FindParksMenu implements FindMenuInterface{

	private boolean running = true;
	private enum State{
		CITY, ZIPCODE, AMENITIES, ALL, MENU, EXIT
	}
	State state = State.MENU;
	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("---------------------- search parks by... ----------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| City ------------------------------------------------  1  ---|");
		System.out.println("| Zipcode ---------------------------------------------  2  ---|");
		System.out.println("| Amenities -------------------------------------------  3  ---|");
		System.out.println("| See all parks ---------------------------------------  4  ---|");
		System.out.println("| Return to main menu ---------------------------------  5  ---|");
		System.out.println("|==============================================================|");
		System.out.println("");
	}
	@Override
	public void setState(int option) {
		
		switch(option) {
		case 1:
			this.state = State.CITY;
			break;
		case 2:
			this.state = State.ZIPCODE;
			break;
		case 3:
			this.state = State.AMENITIES;
			break;
		case 4:
			this.state = State.ALL;
			break;
		case 5:
			this.state = State.EXIT;
			break;
		default:
			System.out.println("Input error. Repeating park search choices.");
			this.state = State.MENU;
			break;
		}
	}
	@Override
	public void loop() {
		
		ParkDao pDao = new ParkDao();
		Scanner scan = new Scanner(System.in);
		
		while(this.running) {

			switch(this.state) {
			case CITY:
				System.out.println("Enter the name of the city you would like to search by:");
				String cityToSearchBy = scan.nextLine();
				List<Park> parksCitySearchResult = pDao.getParksByCity(cityToSearchBy);
				printParkList(parksCitySearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case ZIPCODE:
				System.out.println("Enter the zipcode you would like to search by:");
				int zipcodeToSearchBy = scan.nextInt();
				List<Park> parksZipSearchResult = pDao.getParksByZip(zipcodeToSearchBy);
				printParkList(parksZipSearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case AMENITIES:
				System.out.println("Please respond 'y' or 'n' to search for the following amenities:");
				System.out.println("");
				boolean[] desiredAmenities = getParkAmenities(scan);
				List<Park> parksAmenitiesSearchResult = pDao.getParksByAmenities(desiredAmenities);
				printParkList(parksAmenitiesSearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case ALL:
				List<Park> parksSearchResult = pDao.getParks();
				printParkList(parksSearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case MENU:
				menu();
				setState(scan.nextInt());
				break;
			case EXIT:
				running = false;
				break;
			default:
				System.out.println("Internal error. Returning to main menu.");
				running = false;
				break;
			}
		}
	}
	
	public void printParkList(List<Park> parks) {
		System.out.println("");
		for(Park park: parks) {
			System.out.println(park);
		}
	}
	
	public State handleParksMenuReturn(Scanner s) {
		State parkMenuState;
		System.out.println("Press '1' to return to parks search. Press '2' to return to main menu.");
		int response = s.nextInt();
		switch(response) {
		case 1:
			parkMenuState = State.MENU;
			break;
		case 2:
			parkMenuState = State.EXIT;
			break;
		default:
			parkMenuState = State.EXIT;
			System.out.println("Input error. Returning to main menu.");
			break;	
		}
		return parkMenuState;
		
	}
	
	public boolean[] getParkAmenities(Scanner s) {
		final int NUM_AMENITIES = 4;
		boolean[] desiredAmenities = {false, false, false, false};
		for (int i = 0; i < NUM_AMENITIES; i++) {
			String question = "";
			switch(i) {
			case 0:
				question = "Lots of shade";
				break;
			case 1:
				question = "Dedicated bark park";
				break;
			case 2:
				question = "Open areas for play";
				break;
			case 3:
				question = "Trails for walking/running";
				break;
			}
			String questionSuffix = "? (y/n)";
			System.out.println(question + questionSuffix);
			String answer = s.nextLine();
			if(answer == "y") {
				desiredAmenities[i] = true;
			}else if(answer == "n") {
				desiredAmenities[i] = false;
			}else{
				System.out.println("Incorrect input.  Please respond again with 'y' or 'n'.");
				i--;
				continue;
			}	
		}
		return desiredAmenities;
	}
}

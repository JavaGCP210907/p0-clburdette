package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.ParkDao;

public class FindParksMenu implements MenuInterface{

	private boolean running = true;
	private enum State{
		NAME, CITY, ZIPCODE, AMENITIES, ALL, MENU, EXIT
	}
	State state = State.MENU;
	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("---------------------- search parks by... ----------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Name ------------------------------------------------  1  ---|");
		System.out.println("| City ------------------------------------------------  2  ---|");
		System.out.println("| Zipcode ---------------------------------------------  3  ---|");
		System.out.println("| Amenities -------------------------------------------  4  ---|");
		System.out.println("| See all parks ---------------------------------------  5  ---|");
		System.out.println("| Return to Parks Menu --------------------------------  6  ---|");
		System.out.println("|==============================================================|");
		System.out.println("");
	}
	@Override
	public void setState(int option) {
		
		switch(option) {
		case 1:
			this.state = State.NAME;
			break;
		case 2:
			this.state = State.CITY;
			break;
		case 3:
			this.state = State.ZIPCODE;
			break;
		case 4:
			this.state = State.AMENITIES;
			break;
		case 5:
			this.state = State.ALL;
			break;
		case 6:
			this.state = State.EXIT;
			break;
		default:
			System.out.println("Input error. Repeating park search choices.");
			this.state = State.MENU;
			break;
		}
	}
	@Override
	public void loop(Scanner scan) {
		
		ParkDao pDao = new ParkDao();
		
		while(this.running) {

			switch(this.state) {
			case NAME:
				scan.nextLine();
				System.out.println("Enter the name of the park you would like to search for:");
				String parkToSearchFor = scan.nextLine();
				List<Park> parkNameSearchResult = pDao.getParksByName(parkToSearchFor);
				printParkList(parkNameSearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case CITY:
				scan.nextLine();
				System.out.println("Enter the name of the city you would like to search by:");
				String cityToSearchBy = scan.nextLine();
				List<Park> parksCitySearchResult = pDao.getParksByCity(cityToSearchBy);
				printParkList(parksCitySearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case ZIPCODE:
				scan.nextLine();
				System.out.println("Enter the zipcode you would like to search by:");
				String zipcodeToSearchBy = scan.nextLine();
				List<Park> parksZipSearchResult = pDao.getParksByZip(zipcodeToSearchBy);
				printParkList(parksZipSearchResult);
				state = handleParksMenuReturn(scan);
				break;
			case AMENITIES:
				scan.nextLine();
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
				System.out.println("Internal error. Returning to Parks Menu.");
				running = false;
				break;
			}
		}
		state = State.MENU;
		running = true;
	}
	
	public void printParkList(List<Park> parks) {
		System.out.println("");
		for(Park park: parks) {
			System.out.println(park);
		}
	}
	
	public State handleParksMenuReturn(Scanner s) {
		State parkMenuState;
		System.out.println("");
		System.out.println("Press '1' to return to parks search. Press '2' to return to Parks Menu.");
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
			System.out.println("Input error. Returning to Parks Menu.");
			break;	
		}
		s.nextLine();
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
			char answer = s.next().charAt(0);
			if(answer == 'y') {
				desiredAmenities[i] = true;
			}else if(answer == 'n') {
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

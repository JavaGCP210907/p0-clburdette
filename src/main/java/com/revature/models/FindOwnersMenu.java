package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.OwnerDao;

public class FindOwnersMenu implements FindMenuInterface{
	
	private boolean running = true;
	private enum State{
		NAME, ZIPCODE, HOMEPARK, ALL, MENU, EXIT
	}
	State state = State.MENU;

	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("---------------------- search owners by... ---------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Name ------------------------------------------------  1  ---|");
		System.out.println("| Zipcode ---------------------------------------------  2  ---|");
		System.out.println("| Home park -------------------------------------------  3  ---|");
		System.out.println("| See all owners --------------------------------------  4  ---|");
		System.out.println("| Return to main menu ---------------------------------  5  ---|");
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
			this.state = State.ZIPCODE;
			break;
		case 3:
			this.state = State.HOMEPARK;
			break;
		case 4:
			this.state = State.ALL;
			break;
		case 5:
			this.state = State.EXIT;
			break;
		default:
			System.out.println("Input error. Repeating owner search choices.");
			this.state = State.MENU;
			break;
		}
	}

	@Override
	public void loop(Scanner scan) {
		
		OwnerDao oDao = new OwnerDao();
		
		while(this.running) {

			switch(this.state) {
			case NAME:
				scan.nextLine();
				System.out.println("Enter the first name of the owner to search for:(leave blank if unknown)");
				String firstName = scan.nextLine();
				System.out.println("Enter the last name of the owner to search for:(leave blank if unknown)");
				String lastName = scan.nextLine();
				List<Owner> ownerNameSearchResult = oDao.getOwnersByName(firstName, lastName);
				printOwnerList(ownerNameSearchResult);
				state = handleOwnersMenuReturn(scan);
				break;
			case ZIPCODE:
				scan.nextLine();
				System.out.println("Enter the zipcode you would like to search by:");
				String zipcodeToSearchBy = scan.nextLine();
				List<Owner> ownersZipSearchResult = oDao.getOwnersByZip(zipcodeToSearchBy);
				printOwnerList(ownersZipSearchResult);
				state = handleOwnersMenuReturn(scan);
				break;
			case HOMEPARK:
				scan.nextLine();
				System.out.println("Enter a park id to see owners associated with it:");
				int homeParkToSearchBy = scan.nextInt();
				List<Owner> ownersParkSearchResult = oDao.getOwnersByHomePark(homeParkToSearchBy);
				printOwnerList(ownersParkSearchResult);
				state = handleOwnersMenuReturn(scan);
				break;
			case ALL:
				scan.nextLine();
				List<Owner> parksSearchResult = oDao.getOwners();
				printOwnerList(parksSearchResult);
				state = handleOwnersMenuReturn(scan);
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
		state = State.MENU;
		running = true;
	}
	
	public void printOwnerList(List<Owner> owners) {
		System.out.println("");
		for(Owner owner: owners) {
			System.out.println(owner);
		}
	}
	
	public State handleOwnersMenuReturn(Scanner s) {
		State ownerMenuState;
		System.out.println("Press '1' to return to owners search. Press '2' to return to main menu.");
		int response = s.nextInt();
		switch(response) {
		case 1:
			ownerMenuState = State.MENU;
			break;
		case 2:
			ownerMenuState = State.EXIT;
			break;
		default:
			ownerMenuState = State.EXIT;
			System.out.println("Input error. Returning to main menu.");
			break;	
		}
		return ownerMenuState;
		
	}

}

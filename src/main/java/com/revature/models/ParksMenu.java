package com.revature.models;

import java.util.Scanner;

import com.revature.daos.ParkDao;

public class ParksMenu implements MenuInterface{

	
	private boolean running = true;
	private enum State{
		SEARCH, UPDATE, ADD, DELETE, MENU, EXIT
	}
	
	State state = State.MENU;
	
	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("------------------ Choose a Parks Menu option ------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Search for parks ------------------------------------  1  ---|");
		System.out.println("| Update a park ---------------------------------------  2  ---|");
		System.out.println("| Add a park ------------------------------------------  3  ---|");
		System.out.println("| Delete a park----------------------------------------  4  ---|");
		System.out.println("| Return to main menu ---------------------------------  5  ---|");
		System.out.println("|==============================================================|");
		System.out.println("");
	}

	@Override
	public void setState(int option) {
		switch(option) {
		case 1:
			this.state = State.SEARCH;
			break;
		case 2:
			this.state = State.UPDATE;
			break;
		case 3:
			this.state = State.ADD;
			break;
		case 4:
			this.state = State.DELETE;
			break;
		case 5:
			this.state = State.EXIT;
			break;
		default:
			System.out.println("Input error. Repeating Parks Menu choices.");
			this.state = State.MENU;
			break;
		}
	}

	@Override
	public void loop(Scanner scan) {
		ParkDao pDao = new ParkDao();
		
		while(this.running) {

			switch(this.state) {
			case SEARCH:
				scan.nextLine();
				FindParksMenu findParks = new FindParksMenu();
				findParks.loop(scan);
				this.state = State.MENU;
				break;
			case UPDATE:
				scan.nextLine();
				Park parkToUpdate = parkUpdatePrompts(pDao,scan);
				pDao.updatePark(parkToUpdate);
				state = State.MENU;
				break;
			case ADD:
				System.out.println("start of add");
				scan.nextLine();
				System.out.println("after scan");
				PromptsForAddingObjects addPromptsTo = new PromptsForAddingObjects();
				System.out.println("create prompts");
				Park parkToAdd = addPromptsTo.getPark(scan);
				System.out.println("after 2nd scan into park object");
				pDao.addPark(parkToAdd);
				state = State.MENU;
				break;
			case DELETE:
				scan.nextLine();
				pDao.deletePark(scan);
				state = State.MENU;
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
	
	public Park parkUpdatePrompts(ParkDao parkDao, Scanner s) {
		System.out.println("Enter the park ID number of the park you would like to update");
		int choice = s.nextInt();
		Park p = parkDao.getParkByID(choice);
		System.out.println("You have chosen the following park to update:");
		System.out.println(p);
		s.nextLine();
		Park park = new Park();
		System.out.println("Please provide the following information to update this park: (ALL fields are required)");
		System.out.println("");
		System.out.println("Park name:");
		park.setParkName(s.nextLine());
		System.out.println("");
		System.out.println("Park street name:");
		park.setStreetName(s.nextLine());
		System.out.println("");
		System.out.println("City where the park is located:");
		park.setCity(s.nextLine());
		System.out.println("");
		System.out.println("Zipcode where the park is located:");
		park.setZipcode(s.nextLine());
		System.out.println("");
		System.out.println("=------------------------- AMENITIES --------------------------=");
		System.out.println("=------ please answer 'y' or 'n' to the following prompts -----=");		
		System.out.println("");
		System.out.println("Does this park have abundant shade?(y/n)");
		if(s.next().charAt(0) == 'y') {
			park.setShady(true);
		}
		else { park.setShady(false); }
		System.out.println("");
		System.out.println("Does this park have a dedicated bark park?(y/n)");
		if(s.next().charAt(0) == 'y') {
			park.setBarkPark(true);
		}
		else { park.setBarkPark(false); }
		System.out.println("");
		System.out.println("Does this park have open spaces where dogs can play?(y/n)");
		if(s.next().charAt(0) == 'y') {
			park.setPlayFields(true);
		}
		else { park.setPlayFields(false); }
		System.out.println("");
		System.out.println("Does this park have trails where dogs can walk or run?(y/n)");
		if(s.next().charAt(0) == 'y') {
			park.setWalkingTrails(true);
		}
		else { park.setWalkingTrails(false); }

		return park;
	}

}

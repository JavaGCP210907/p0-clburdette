package com.revature.models;

import java.util.Scanner;

import com.revature.daos.OwnerDao;

public class OwnersMenu implements MenuInterface{

	private boolean running = true;
	private enum State{
		SEARCH, UPDATE, ADD, DELETE, MENU, EXIT
	}
	
	private State state = State.MENU;
	
	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("----------------- Choose an Owners Menu option -----------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Search for Owners -----------------------------------  1  ---|");
		System.out.println("| Update an Owner -------------------------------------  2  ---|");
		System.out.println("| Add an Owner ----------------------------------------  3  ---|");
		System.out.println("| Delete an Owner -------------------------------------  4  ---|");
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
			System.out.println("Input error. Repeating Owners Menu choices.");
			this.state = State.MENU;
			break;
		}
	}

	@Override
	public void loop(Scanner scan) {
		OwnerDao oDao = new OwnerDao();
		
		while(this.running) {

			switch(this.state) {
			case SEARCH:
				scan.nextLine();
				FindOwnersMenu findOwners = new FindOwnersMenu();
				findOwners.loop(scan);
				this.state = State.MENU;
				break;
			case UPDATE:
				scan.nextLine();
				Owner ownerToUpdate = ownerUpdatePrompts(oDao,scan);
				oDao.updateOwner(ownerToUpdate);
				state = State.MENU;
				break;
			case ADD:
				scan.nextLine();
				PromptsForAddingObjects addPromptsTo = new PromptsForAddingObjects();
				Owner ownerToAdd = addPromptsTo.getOwner(scan);
				oDao.addOwner(ownerToAdd);
				state = State.MENU;
				break;
			case DELETE:
				scan.nextLine();
				oDao.deleteOwner(scan);
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
	
	public Owner ownerUpdatePrompts(OwnerDao oDao, Scanner s) {
		System.out.println("Enter the owner ID number of the owner you would like to update:");
		int choice = s.nextInt();
		Owner o = oDao.getOwnerByID(choice);
		System.out.println("You have chosen the following owner to update:");
		System.out.println(o);
		s.nextLine();
		Owner owner = new Owner();
		System.out.println("Please provide the following information to update this owner: (ALL fields are required)");
		System.out.println("");
		System.out.println("Owner first name:");
		owner.setF_name(s.nextLine());
		System.out.println("");
		System.out.println("Owner last name:");
		owner.setL_name(s.nextLine());
		System.out.println("");
		System.out.println("Owner's zipcode:");
		owner.setZipcode(s.nextLine());
		System.out.println("");
		System.out.println("Owner's home park ID:");
		owner.setHomepark_fk(s.nextInt());
		System.out.println("");

		return owner;
	}

}

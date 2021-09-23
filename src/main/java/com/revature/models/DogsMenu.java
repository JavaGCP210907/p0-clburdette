package com.revature.models;

import java.util.Scanner;

import com.revature.daos.DogDao;


public class DogsMenu implements MenuInterface{

	private boolean running = true;
	private enum State{
		SEARCH, UPDATE, ADD, DELETE, MENU, EXIT
	}
	State state = State.MENU;
	
	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("------------------ Choose a Dogs Menu option ------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Search for dogs -------------------------------------  1  ---|");
		System.out.println("| Update a dog ----------------------------------------  2  ---|");
		System.out.println("| Add a dog -------------------------------------------  3  ---|");
		System.out.println("| Delete a dog-----------------------------------------  4  ---|");
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
			System.out.println("Input error. Repeating Dogs Menu choices.");
			this.state = State.MENU;
			break;
		}
	}

	@Override
	public void loop(Scanner scan) {
		DogDao dDao = new DogDao();
		
		while(this.running) {

			switch(this.state) {
			case SEARCH:
				scan.nextLine();
				FindDogsMenu findDogs = new FindDogsMenu();
				findDogs.loop(scan);
				this.state = State.MENU;
				break;
			case UPDATE:
				scan.nextLine();
				Dog dogToUpdate = dogUpdatePrompts(dDao,scan);
				dDao.updateDog(dogToUpdate);
				state = State.MENU;
				break;
			case ADD:
				scan.nextLine();
				PromptsForAddingObjects addPromptsTo = new PromptsForAddingObjects();
				Dog dogToAdd = addPromptsTo.getDog(scan);
				dDao.addDog(dogToAdd);
				state = State.MENU;
				break;
			case DELETE:
				scan.nextLine();
				dDao.deleteDog(scan);
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
	
	public Dog dogUpdatePrompts(DogDao dDao, Scanner s) {
		System.out.println("Enter the dog ID number of the dog you would like to update");
		int choice = s.nextInt();
		Dog d = dDao.getDogByID(choice);
		System.out.println("You have chosen the following dog to update:");
		System.out.println(d);
		s.nextLine();
		Dog dog = new Dog();
		System.out.println("Please provide the following information to update this dog: (ALL fields are required)");
		System.out.println("");
		System.out.println("Dog name:");
		dog.setName(s.nextLine());
		System.out.println("");
		System.out.println("Dog breed:");
		dog.setBreed(s.nextLine());
		System.out.println("");
		System.out.println("Dog age:");
		dog.setAge(s.nextInt());
		System.out.println("");
		System.out.println("Is this a small dog (y/n):");
		char test = s.nextLine().charAt(0);
		boolean small = false;
		if(test == 'y'){small = true;}
		dog.setSmall(small);
		System.out.println("");
		System.out.println("What is the owner ID of the owner associated with this dog?");
		dog.setOwner_fk(s.nextInt());

		return dog;
	}
}


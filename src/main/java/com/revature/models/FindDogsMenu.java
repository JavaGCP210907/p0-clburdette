package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.daos.DogDao;
import com.revature.daos.ParkDao;

public class FindDogsMenu implements FindMenuInterface{
	private boolean running = true;
	private enum State{
		NAME, BREED, AGE, SIZE, ALL, MENU, EXIT
	}
	State state = State.MENU;

	@Override
	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("---------------------- search dogs by... ----------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Name ------------------------------------------------  1  ---|");
		System.out.println("| Breed -----------------------------------------------  2  ---|");
		System.out.println("| Age -------------------------------------------------  3  ---|");
		System.out.println("| Size ------------------------------------------------  4  ---|");
		System.out.println("| See all dogs ----------------------------------------  5  ---|");
		System.out.println("| Return to main menu ---------------------------------  6  ---|");
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
			this.state = State.BREED;
			break;
		case 3:
			this.state = State.AGE;
			break;
		case 4:
			this.state = State.SIZE;
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
	public void loop() {
		DogDao dDao = new DogDao();
		Scanner scan = new Scanner(System.in);
		
		while(this.running) {

			switch(this.state) {
			case NAME:
				System.out.println("Enter the name of the city you would like to search by:");
				String nameToSearchBy = scan.nextLine();
				List<Dog> dogsNameSearchResult = dDao.getDogsByName(nameToSearchBy);
				printDogList(dogsNameSearchResult);
				state = handleDogssMenuReturn(scan);
				break;
			case BREED:
				System.out.println("Enter the zipcode you would like to search by:");
				int breedToSearchBy = scan.nextInt();
				List<Dog> dogsBreedSearchResult = dDao.getDogsByBreed(breedToSearchBy);
				printDogList(dogsBreedSearchResult);
				state = handleDogsMenuReturn(scan);
				break;
			case AGE:
				System.out.println("Please enter the start of the age range to search for:");
				int startAge = scan.nextInt();
				System.out.println("Please enter the end of the age range to search for:");
				int endAge = scan.nextInt();
				List<Dog> dogsAgeSearchResult = dDao.getDogsByAge(startAge, endAge);
				printDogList(dogsAgeSearchResult);
				state = handleDogsMenuReturn(scan);
				break;
			case SIZE:
				System.out.println("To search for small dogs, enter 'y'. Enter 'n' for medium and large dogs.");
				String size = scan.nextLine();
				List<Dog> dogsSizeSearchResult = dDao.getDogsBySize(size);
				printDogList(dogsSizeSearchResult);
				state = handleDogsMenuReturn(scan);
				break;
			case ALL:
				List<Dog> dogsSearchResult = dDao.getDogs();
				printDogList(dogssSearchResult);
				state = handleDogsMenuReturn(scan);
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

}

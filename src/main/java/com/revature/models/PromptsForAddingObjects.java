package com.revature.models;

import java.util.Scanner;

public class PromptsForAddingObjects {
	

	
	public PromptsForAddingObjects() {

	}
	public Park getPark(Scanner scan) {
		Park park = new Park();
		System.out.println("Please provide the following information to add a valid park:(ALL fields are required)");
		System.out.println("");
		System.out.println("Park name:");
		park.setParkName(scan.nextLine());
		System.out.println("");
		System.out.println("Park street name:");
		park.setStreetName(scan.nextLine());
		System.out.println("");
		System.out.println("City where the park is located:");
		park.setCity(scan.nextLine());
		System.out.println("");
		System.out.println("Zipcode where the park is located:");
		park.setZipcode(scan.nextLine());
		System.out.println("");
		System.out.println("=------------------------- AMENITIES --------------------------=");
		System.out.println("=------ please answer 'y' or 'n' to the following prompts -----=");		
		System.out.println("");
		System.out.println("Does this park have abundant shade?(y/n)");
		if(scan.next().charAt(0) == 'y') {
			park.setShady(true);
		}
		else { park.setShady(false); }
		System.out.println("");
		System.out.println("Does this park have a dedicated bark park?(y/n)");
		if(scan.next().charAt(0) == 'y') {
			park.setBarkPark(true);
		}
		else { park.setBarkPark(false); }
		System.out.println("");
		System.out.println("Does this park have open spaces where dogs can play?(y/n)");
		if(scan.next().charAt(0) == 'y') {
			park.setPlayFields(true);
		}
		else { park.setPlayFields(false); }
		System.out.println("");
		System.out.println("Does this park have trails where dogs can walk or run?(y/n)");
		if(scan.next().charAt(0) == 'y') {
			park.setWalkingTrails(true);
		}
		else { park.setWalkingTrails(false); }

		return park;
		
	}
	
	public Owner getOwner(Scanner scan) {
		Owner owner = new Owner();
		System.out.println("Please provide the following information to add yourself as a dog owner:(ALL fields are required)");
		System.out.println("");
		System.out.println("Your first name:");
		owner.setF_name(scan.nextLine());
		System.out.println("");
		System.out.println("Your last name:");
		owner.setL_name(scan.nextLine());
		System.out.println("");
		System.out.println("Your zipcode:");
		owner.setZipcode(scan.nextLine());
		System.out.println("");
		System.out.println("ID number of your home park:");
		owner.setHomepark_fk(scan.nextInt());

		return owner;
		
	}
	public Dog getDog(Scanner scan) {
		Dog dog = new Dog();
		System.out.println("Please provide the following information to add your dog:(ALL fields are required)");
		System.out.println("");
		System.out.println("Your dog's name:");
		dog.setName(scan.nextLine());
		System.out.println("");
		System.out.println("Your dog's breed:");
		dog.setBreed(scan.nextLine());
		System.out.println("");
		System.out.println("Your dog's age:");
		dog.setAge(scan.nextInt());
		System.out.println("");
		System.out.println("Your dog's size:(enter 'y' for small, enter 'n' for medium or large)");
		if(scan.next().charAt(0) == 'y') {
			dog.setSmall(true);
		}
		else { dog.setSmall(false); }
		System.out.println("");
		System.out.println("Your owner ID number:(to associate your dog with you)");
		dog.setOwner_fk(scan.nextInt());

		return dog;
	}

}

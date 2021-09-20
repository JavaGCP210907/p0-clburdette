package com.revature.models;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.DogDao;
import com.revature.daos.OwnerDao;
import com.revature.daos.ParkDao;

public class Menu {
	
	private Logger log;
	private Scanner scan;
	private PromptsForAddingObjects promptsThat;
	private FindParksMenu findParksMenu;
	private FindOwnersMenu findOwnersMenu;
	private FindDogsMenu findDogsMenu;
	private boolean running = true;
	private enum State{
		MAIN,FIND_PARKS,FIND_OWNERS,FIND_DOGS,ADD_PARK,ADD_OWNER,ADD_DOG,EXIT
	}
	private State state;
	
	public Menu() {
		this.log = LogManager.getLogger(Menu.class);
		this.scan = new Scanner(System.in);
		this.promptsThat =  new PromptsForAddingObjects();
		this.findParksMenu = new FindParksMenu();
		this.findOwnersMenu = new FindOwnersMenu();
		this.findDogsMenu = new FindDogsMenu();
		this.state = State.MAIN;
		log.info("main menu initiated");
	}

	public void menuIntro() {
		System.out.println("================================================================");
		System.out.println("=------------------- Welcome to BarkPark ----------------------=");
		System.out.println("=-------------- a hub for the best dog parks ------------------=");
		System.out.println("================================================================");
		System.out.println("");
	}
	public void loop() {
		
		while(this.running) {
			switch(this.state) {
			case MAIN:
				mainMenuHeader();
				mainMenuPrompts();
				handleInputMain(scan.nextInt());
				break;
			case FIND_PARKS:
				this.findParksMenu.loop();
				this.state = State.MAIN;
				break;
			case FIND_OWNERS:
				this.findOwnersMenu.loop();
				this.state = State.MAIN;
				break;
			case FIND_DOGS:
				this.findDogsMenu.loop();
				this.state = State.MAIN;
				break;
			case ADD_PARK:
				Park parkToAdd = this.promptsThat.getParkToAdd();
				ParkDao pDao = new ParkDao();
				pDao.addPark(parkToAdd);
				this.state = State.MAIN;
				break;
			case ADD_OWNER:
				Owner ownerToAdd = this.promptsThat.getOwnerToAdd();
				OwnerDao oDao = new OwnerDao();
				oDao.addOwner(ownerToAdd);
				this.state = State.MAIN;
				break;
			case ADD_DOG:
				Dog dogToAdd = this.promptsThat.getDogToAdd();
				DogDao dDao = new DogDao();
				dDao.addDog(dogToAdd);
				this.state = State.MAIN;
				break;
			case EXIT:
				scan.close();
				menuOutro();
				this.running = false;
				log.info("main menu successfully closed");
				break;
			default:
				System.out.println("There has been an input error.  Returning the main menu");
				this.state = State.MAIN;
				break;	
			}
		}
		
	}
	public void mainMenuHeader() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("------------------ choose a main menu option -------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("");
	}
	public void mainMenuPrompts() {
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Find Parks ------------------------------------------  1  ---|");
		System.out.println("| Find Owners -----------------------------------------  2  ---|");
		System.out.println("| Find Dogs -------------------------------------------  3  ---|");
		System.out.println("| Add a park ------------------------------------------  4  ---|");
		System.out.println("| Add yourself as an owner ----------------------------  5  ---|");
		System.out.println("| Add a dog -------------------------------------------  6  ---|");
		System.out.println("| exit ------------------------------------------------  7  ---|");
		System.out.println("|==============================================================|");
		System.out.println("");
	}

	public void handleInputMain(int option) {
		switch(option) {
		case 1:
			this.state = State.FIND_PARKS;
			break;
		case 2:
			this.state = State.FIND_OWNERS;
			break;
		case 3:
			this.state = State.FIND_DOGS;
			break;
		case 4:
			this.state = State.ADD_PARK;
			break;
		case 5:
			this.state = State.ADD_OWNER;
			break;
		case 6:
			this.state = State.ADD_DOG;
			break;
		case 7:
			this.state = State.EXIT;
			break;
		default:
			System.out.println("There has been an input error.  Returning to the main menu.");
			this.state = State.MAIN;
			break;
		}
	}
	public void menuOutro() {
		System.out.println("================================================================");
		System.out.println("=---------------- thanks for using BarkPark -------------------=");
		System.out.println("=------------------ come back again soon ----------------------=");
		System.out.println("================================================================");
		//maybe pause?
	}

}

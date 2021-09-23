package com.revature.models;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainMenu implements MenuInterface{
	
	private Logger log;
	private ParksMenu ParksMenu;
	private OwnersMenu OwnersMenu;
	private DogsMenu DogsMenu;
	private boolean running = true;
	private enum State{
		MAIN, PARKS, OWNERS, DOGS,EXIT
	}
	private State state;
	
	public MainMenu() {
		this.log = LogManager.getLogger(MainMenu.class);
		this.ParksMenu = new ParksMenu();
		this.OwnersMenu = new OwnersMenu();
		this.DogsMenu = new DogsMenu();
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
	public void loop(Scanner scan) {
		
		while(this.running) {
			switch(this.state) {
			case MAIN:
				menu();
				setState(scan.nextInt());
				break;
			case PARKS:
				this.ParksMenu.loop(scan);
				this.state = State.MAIN;
				break;
			case OWNERS:
				this.OwnersMenu.loop(scan);
				this.state = State.MAIN;
				break;
			case DOGS:
				this.DogsMenu.loop(scan);
				this.state = State.MAIN;
				break;
			case EXIT:
				scan.close();
				menuOutro();
				this.running = false;
				break;
			default:
				System.out.println("There has been an input error.  Returning the main menu");
				this.state = State.MAIN;
				break;	
			}
		}
		
	}

	public void menu() {
		System.out.println("----------------------------------------------------------------");
		System.out.println("------------------ choose a main menu option -------------------");
		System.out.println("----------------------------------------------------------------");
		System.out.println("|  (option)                                          (command) |");
		System.out.println("|==============================================================|");
		System.out.println("| Parks Menu ------------------------------------------  1  ---|");
		System.out.println("| Owners Menu -----------------------------------------  2  ---|");
		System.out.println("| Dogs Menu -------------------------------------------  3  ---|");
		System.out.println("| exit ------------------------------------------------  4  ---|");
		System.out.println("|==============================================================|");
		System.out.println("");
	}
	@Override
	public void setState(int option) {
		switch(option) {
		case 1:
			this.state = State.PARKS;
			break;
		case 2:
			this.state = State.OWNERS;
			break;
		case 3:
			this.state = State.DOGS;
			break;
		case 4:
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
		log.info("main menu successfully closed");
	}


}

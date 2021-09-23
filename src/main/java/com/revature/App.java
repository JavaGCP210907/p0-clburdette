package com.revature;

import java.util.Scanner;

import com.revature.models.MainMenu;

public class App {
	
	private MainMenu menu;
	public Scanner scan;
	
	public App() {
		menu = new MainMenu();
		scan = new Scanner(System.in);
	}
	
	public void start() {
		menu.menuIntro();
		menu.loop(scan);
	}

}

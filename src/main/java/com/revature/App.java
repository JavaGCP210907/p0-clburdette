package com.revature;

import com.revature.models.Menu;

public class App {
	
	private Menu menu;
	
	public App() {
		menu = new Menu();
	}
	
	public void start() {
		menu.menuIntro();
		menu.loop();
	}

}

package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher {

	public static void main(String[] args) {
		
		Logger log = LogManager.getLogger(Launcher.class);
		App app = new App();
		app.start();
		log.debug("successfully exited program");
	}

}

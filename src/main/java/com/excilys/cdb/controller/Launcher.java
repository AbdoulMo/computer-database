package com.excilys.cdb.controller;

import com.excilys.cdb.vue.Cli;

public class Launcher {

	public static void main(String[] args) {
		ControllerCLI controller = new ControllerCLI(new Cli());
		controller.runApp();
	}

}

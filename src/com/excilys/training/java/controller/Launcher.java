package com.excilys.training.java.controller;

import com.excilys.training.java.ui.Cli;

public class Launcher {

	public static void main(String[] args) {
		ControllerCLI controller = new ControllerCLI(new Cli());
		controller.runApp();
	}

}

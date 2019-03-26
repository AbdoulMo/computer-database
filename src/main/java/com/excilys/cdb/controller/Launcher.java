package com.excilys.cdb.controller;

import java.util.ArrayList;

import com.excilys.cdb.dao.JDBCComputer;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.services.ComputerServices;
import com.excilys.cdb.vue.Cli;

public class Launcher {

	public static void main(String[] args) throws DataNotFoundException {
//		ControllerCLI controller = new ControllerCLI(new Cli());
//		controller.runApp();
		
//		JDBCComputer dao = new JDBCComputer();
		ComputerServices cs = new ComputerServices();
		
		ArrayList<DTOComputer> lc = new ArrayList<>();
		lc = cs.getAllComputer();
		for(DTOComputer c : lc) {
			System.out.println(c);
		}
	}

}

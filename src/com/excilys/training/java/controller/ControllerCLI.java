package com.excilys.training.java.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.training.java.dao.DAOFactory;
import com.excilys.training.java.dao.interfaces.IDAOCompany;
import com.excilys.training.java.dao.interfaces.IDAOComputer;
import com.excilys.training.java.modele.Company;
import com.excilys.training.java.modele.Computer;
import com.excilys.training.java.ui.Cli;

public class ControllerCLI {

	private IDAOComputer computerDAO;
	private IDAOCompany companyDAO;
	private Cli cli;

	private Scanner input = new Scanner(System.in);
	private boolean leaveApp = false;
	
	

	public ControllerCLI(Cli cli) {
		this.cli = cli;
		DAOFactory.getDAOComputer("jdbc");
		DAOFactory.getDAOCompany("jdbc");
	}

	public String getInput() {
		return input.nextLine();
	}

	public boolean isLeaveApp() {
		return leaveApp;
	}

	public void setLeaveApp(boolean b) {
		this.leaveApp = b;
	}

	public ArrayList<Computer> getAllComputers() {
		return computerDAO.getAllComputers();
	}

	public ArrayList<Company> getAllCompanys() {
		return companyDAO.getAllCompany();
	}

	public Computer getComputer(int id) {
		return computerDAO.getComputer(id);
	}

	public int createComputer(String name, Date dateIntro, Date dateFin, int companyID) {
		Computer computer = new Computer();
		computer.setName(name);
		computer.setIntroduced(dateIntro);
		computer.setDiscontinued(dateFin);
		computer.setManufacturer_id(companyID);
		return computerDAO.addComputer(computer);
	}

	public int updateComputer(Computer c) {
		return computerDAO.updateComputer(c);
	}

	public int deleteComputer(int id) {
		return computerDAO.deleteComputer(id);
	}

	public void action(int choice) {
		switch (choice) {
		case 1:
			this.cli.displayComputerList(this.getAllComputers());
			break;
		case 2:
			this.cli.displayCompanyList(this.getAllCompanys());
			break;
		case 3:
			this.cli.displayComputer();
			break;
		case 4:
			this.cli.createComputer();
			break;
		case 5:
			this.cli.updateComputer();
			break;
		case 6:
			this.cli.deleteComputer();
			break;
		case 7:
			this.cli.leaveApplication();
			break;
		default:
			this.cli.invelidInput();
			break;
		}
	}

	public void runApp() {
		while (!this.isLeaveApp()) {
			this.cli.displayMenu();
			this.action(Integer.parseInt(this.getInput()));
		}
	}
}

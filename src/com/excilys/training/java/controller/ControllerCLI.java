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

	private final IDAOComputer computerDAO = DAOFactory.getDAOComputer("jdbc");
	private final IDAOCompany companyDAO = DAOFactory.getDAOCompany("jdbc");
	private Cli cli = new Cli();
	
	private Scanner input = new Scanner(System.in);
	private boolean leaveApp = false;

	public String getInput() {
		return input.nextLine();
	}

	public boolean isLeaveApp() {
		return leaveApp;
	}

	public void setLeaveApp(boolean leaveApp) {
		this.leaveApp = leaveApp;
	}
	
	public ArrayList<Computer> getAllComputers() {
		return computerDAO.getAllComputers();
	}
	
	public ArrayList<Company> getAllCompanys(){
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
	
	public void action(int menuChoice) {
		
	}
	
	public int deleteComputer(int id) {
		return computerDAO.deleteComputer(id);
	}

	public void runApp() {
		while(!this.isLeaveApp()) {
			this.cli.displayMenu();
			this.action(Integer.parseInt(this.getInput()));
		}
	}
}

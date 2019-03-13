package com.excilys.training.java.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.training.java.modele.Company;
import com.excilys.training.java.modele.Computer;
import com.excilys.training.java.services.interfaces.IDAOCompany;
import com.excilys.training.java.services.interfaces.IDAOComputer;
import com.excilys.training.java.services.jdbc.JDBCCompany;
import com.excilys.training.java.services.jdbc.JDBCComputer;

public class Cli {
	
	private IDAOComputer computerDAO = new JDBCComputer();
	private IDAOCompany companyDAO = new JDBCCompany();
			
	public void displayMenu() {
		System.out.println("Tapez un chiffre pour choisir l'action :\n"
				+ "1: Lister les ordinateurs.\n"
				+ "2: Lister les entreprises.\n"
				+ "3: Afficher les détails d'un ordinateur.\n"
				+ "4: Créer un ordinateur.\n"
				+ "5: Mettre à jour les informations d'un ordinateur.\n"
				+ "6: Supprimer un ordinateur.\n"
				+ "7: Quitter.");
	}
	
	public int getInput() {
		int input = 0;
		try (Scanner in = new Scanner(System.in)){
			input = in.nextInt();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return input;
	}
	
	public void action(int choice) {
		switch (choice) {
		case 1:
			ArrayList<Computer> computerList = getComputerList();
			for(Computer c : computerList) {
				System.out.println(c);
			}
			break;
		case 2:
			ArrayList<Company> companyList = getCompanyList();
			for(Company c : companyList) {
				System.out.println(c);
			}
			break;
		case 3:
			System.out.println("Entrez l'identifiant du pc à afficher");
			try (Scanner in = new Scanner(System.in)){
				int id = in.nextInt();
				Computer c = getComputerDetails(id);
				System.out.println(c);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:
			break;
		}
	}
	
	public ArrayList<Computer> getComputerList() {
		ArrayList<Computer> computerList = (ArrayList<Computer>) computerDAO.getAllComputers();
		return computerList;
	}
	
	public ArrayList<Company> getCompanyList(){
		ArrayList<Company> companyList = (ArrayList<Company>) companyDAO.getAllCompany();
		return companyList;
	}
	
	public Computer getComputerDetails(int id) {
		Computer computer = computerDAO.getComputer(id);
		return computer;
	}
	
	public int createComputer(Computer c) {
		return computerDAO.addComputer(c);
	}
	
	public int updateComputer(int id) {
		Computer c = computerDAO.getComputer(id);
		return computerDAO.deleteComputer(c);
	}

	public int deleteComputer(int id) {
		Computer c = computerDAO.getComputer(id);
		return computerDAO.deleteComputer(c);
	}
	
	public static void main(String[] args) {
//		Cli app = new Cli();
//		app.displayMenu();
//		int input = app.getInput();
//		app.action(input);
		
		IDAOComputer computerDAO = new JDBCComputer();
		IDAOCompany companyDAO = new JDBCCompany();
		
		Computer c = new Computer();
		c.setName("Test");
		c.setManufacturer_id(4);
		computerDAO.deleteComputer(c);
	}
}

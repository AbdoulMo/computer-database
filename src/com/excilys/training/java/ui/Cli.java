package com.excilys.training.java.ui;

import java.sql.Date;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private Scanner input = new Scanner(System.in);
	private boolean leaveApp = false;
			
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
	
	public String getInput() {
		return input.nextLine();
	}
	
	public boolean isLeaveApp() {
		return leaveApp;
	}

	public void setLeaveApp(boolean leaveApp) {
		this.leaveApp = leaveApp;
	}

	public void action(int choice) {
		Computer computer;
		Company company;
		int id;
		String reponse;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		switch (choice) {
		case 1:
			ArrayList<Computer> computerList = getComputerList();
			for(Computer pc : computerList) {
				System.out.println(pc);
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
			id = Integer.parseInt(getInput());
			computer = getComputerDetails(id);
			System.out.println(computer);
			break;
		case 4:
			System.out.println("Donnez le nom du nouveau Pc :\n");
			//Computer c = new Computer();
			break;
		case 5:
			System.out.println("Pour continuer il faut connaitre l'identifiant de l'ordinateur...\n"
					+ "Souhaitez vous continuer (o/n)");
			reponse = getInput();
			if(reponse.equals("o")) {
				System.out.println("Entrez l'identifiant de l'ordinateur à modifier: \n");
				id = Integer.parseInt(getInput());
				computer = getComputerDetails(id);
				boolean leaveOption = false;
				String optionInput = new String();
				while (leaveOption) {
					System.out.println("Choisisez le paramètre a modifier "
							+ "(1: Nom de l'ordinateur\n"
							+ " 2: Date d'introduction\n"
							+ " 3: Date d'arrete de production\n"
							+ " 4: L'identifiant du fabriquant\n"
							+ " 5: Pour terminer");
					int choixModif = Integer.parseInt(getInput());
					switch(choixModif){
						case 1:
							System.out.println("Entrez le nouveau nom: ");
							optionInput = getInput();
							computer.setName(optionInput);
							System.out.println("Nom modifié !");
							break;
						case 2:
							System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
							optionInput = getInput();
						try {
							java.util.Date d = format.parse(optionInput);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//							computer.setIntroduced(new D);
							System.out.println("Nom modifié !");
							break;
						default:
							break;
					}
				}
			}
			break;
		case 6:
			System.out.println("Pour continuer il faut connaitre l'identifiant de l'ordinateur...\n"
					+ "Souhaitez vous continuer (o/n)");
			reponse = getInput();
			if(reponse.equals("o")) {
				System.out.println("Entrez l'identifiant de l'ordinateur à supprimer: \n");
				id = Integer.parseInt(getInput());
				computer = getComputerDetails(id);
				deleteComputer(id);
				System.out.println("L'ordinateur "+ computer.getName() + 
						" avec l'identifiant "+ computer.getId() +" a été supprimé.");
			}
			break;
		case 7:
			System.out.println("Fin de l'application");
			setLeaveApp(true);
			break;
		default:
			System.out.println("Choix invalide veuillez réessayer !");
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
		int choice = 0;
		Cli app = new Cli();
		while(!app.isLeaveApp()) {
			app.displayMenu();
			choice = Integer.parseInt(app.getInput());
			app.action(choice);
		}
	}
}

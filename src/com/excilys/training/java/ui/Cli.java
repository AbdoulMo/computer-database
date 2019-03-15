package com.excilys.training.java.ui;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.training.java.controller.ControllerCLI;
import com.excilys.training.java.modele.Company;
import com.excilys.training.java.modele.Computer;

public class Cli {

	private Scanner input = new Scanner(System.in);
	
	public String getInput() {
		return input.nextLine();
	}

	public void displayMenu() {
		System.out.println("Entrez un chiffre pour choisir l'action :\n" + "1: Lister les ordinateurs.\n"
				+ "2: Lister les entreprises.\n" + "3: Afficher les détails d'un ordinateur.\n"
				+ "4: Créer un ordinateur.\n" + "5: Mettre à jour les informations d'un ordinateur.\n"
				+ "6: Supprimer un ordinateur.\n" + "7: Quitter.");
	}

	public void displayComputerList(ArrayList<Computer> computerList) {
		for (Computer pc : computerList) {
			System.out.println(pc);
		}
	}

	public void displayCompanyList(ArrayList<Company> companyList) {
		for (Company c : companyList) {
			System.out.println(c);
		}
	}

	public void displayComputer() {
		System.out.println("Entrez l'identifiant du pc à afficher");
		Computer c = controller.getComputer(Integer.parseInt(getInput()));
		System.out.println(c);
	}

	public void createComputer() {
		System.out.println("Le nom du nouveau Pc :\n");
		String newComputerName = getInput();

		System.out.println("La date d'introduction au format (yyyy-mm-dd)");
		Date newComputerDateI = parseInputDate(getInput());

		System.out.println("La date d'arret de production au format (yyyy-mm-dd)");
		Date newComputerDateD = parseInputDate(getInput());

		System.out.println("L'identifiant de l'entreprise");
		int companyId = Integer.parseInt(getInput());

		controller.createComputer(newComputerName, newComputerDateI, newComputerDateD, companyId);
		System.out.println("Nouvel ordinateur crée");
	}

	private Date parseInputDate(String date) {
		Date parsedDate = null;
		try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			parsedDate = new Date(utilDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parsedDate;
	}

	public void updateComputer() {
		boolean leaveOption = false;
		while (!leaveOption) {
			System.out.println("Entrez l'identifiant de l'ordinateur à modifier: \n");
			Computer computer = controller.getComputer(Integer.parseInt(getInput()));

			System.out.println("Choisisez le paramètre a modifier " + "(1: Nom de l'ordinateur\n"
					+ " 2: Date d'introduction\n" + " 3: Date d'arrete de production\n"
					+ " 4: L'identifiant du fabriquant\n" + " 5: Pour terminer");
			int choixModif = Integer.parseInt(getInput());

			switch (choixModif) {
			case 1:
				System.out.println("Entrez le nouveau nom: ");
				computer.setName(getInput());
				controller.updateComputer(computer);
				System.out.println("Nom modifié !");
				break;
			case 2:
				System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
				computer.setIntroduced(parseInputDate(getInput()));
				controller.updateComputer(computer);
				System.out.println("Date d'introduction modifié !");
				break;
			case 3:
				System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
				computer.setDiscontinued(parseInputDate(getInput()));
				controller.updateComputer(computer);
				System.out.println("Date d'arrêt de production modifié !");
				break;
			case 4:
				System.out.println("Entrez le nouvel identifiant: ");
				computer.setManufacturer_id(Integer.parseInt(getInput()));
				controller.updateComputer(computer);
				System.out.println("Identifiant fabriquant modifié !");
				break;
			case 5:
				leaveOption = true;
				System.out.println("Fin de la mise à jour");
				break;
			default:
				System.out.println("Choix invalide veuillez réessayer !");
				break;
			}
		}
	}

	public void deleteComputer() {
		System.out.println("Entrez l'identifiant de l'ordinateur à supprimer: \n");
		controller.deleteComputer(Integer.parseInt(getInput()));
		System.out.println("L'ordinateur à été supprimé.");
	}
	
	public void leaveApplication() {
		System.out.println("Fin de l'application");
		controller.setLeaveApp(true);
	}
	
	public void invelidInput() {
		System.out.println("Choix invalide veuillez réessayer !");
	}
}

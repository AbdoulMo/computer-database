package com.excilys.training.java.ui;

import java.util.ArrayList;
import java.util.Scanner;

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

	public int askComputerID() {
		System.out.println("Entrez l'identifiant de l'ordinateur");
		return Integer.parseInt(getInput());
	}

	public void displayComputer(Computer c) {
		System.out.println(c);
	}

	public ArrayList<String> getComputerParams() {
		ArrayList<String> computerParams = new ArrayList<>();
		System.out.println("Le nom du nouveau Pc :\n");
		String newComputerName = getInput();
		computerParams.add(newComputerName);

		System.out.println("La date d'introduction au format (yyyy-mm-dd)");
		String newComputerDateI = getInput();
		computerParams.add(newComputerDateI);

		System.out.println("La date d'arret de production au format (yyyy-mm-dd)");
		String newComputerDateD = getInput();
		computerParams.add(newComputerDateD);

		System.out.println("L'identifiant de l'entreprise");
		String companyId = getInput();
		computerParams.add(companyId);

		return computerParams;
	}

	public void createdComputer() {
		System.out.println("Nouvel ordinateur crée");
	}

	public ArrayList<String> getUpdateComputerParams() {
		ArrayList<String> updateParams = new ArrayList<>();
		System.out.println("Choisisez le paramètre a modifier " + "(1: Nom de l'ordinateur\n"
				+ " 2: Date d'introduction\n" + " 3: Date d'arrete de production\n"
				+ " 4: L'identifiant du fabriquant\n" + " 5: Pour terminer");
		int choixModif = Integer.parseInt(getInput());
		updateParams.add(Integer.toString(choixModif));

		switch (choixModif) {
		case 1:
			System.out.println("Entrez le nouveau nom: ");
			String newName = getInput();
			updateParams.add(newName);
			break;
		case 2:
			System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
			String newDateI = getInput();
			updateParams.add(newDateI);
			break;
		case 3:
			System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
			String newDateD = getInput();
			updateParams.add(newDateD);
			break;
		case 4:
			System.out.println("Entrez le nouvel identifiant: ");
			String newID = getInput();
			updateParams.add(newID);
			break;
		case 5:
			System.out.println("Fin de la mise à jour");
			break;
		default:
			System.out.println("Choix invalide veuillez réessayer !");
			break;
		}
		return updateParams;
	}

	public void updatedComputer() {
		System.out.println("Ordinateur mis a jour");
	}

	public void deletedComputer() {
		System.out.println("L'ordinateur à été supprimé.");
	}

	public void leftApplication() {
		System.out.println("Fin de l'application");
	}

	public void invelidInput() {
		System.out.println("Choix invalide veuillez réessayer !");
	}
}

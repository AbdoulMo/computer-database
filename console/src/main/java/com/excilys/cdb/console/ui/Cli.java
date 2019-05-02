package com.excilys.cdb.console.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.DTOComputer;

public class Cli {

	private Scanner input = new Scanner(System.in);
	private String dateFormat = "\\d{4}-\\d{2}-\\d{2}";

	public String getInput() {
		return input.nextLine();
	}

	public void displayMenu() {
		System.out.println("Entrez un chiffre pour choisir l'action :\n" + "1: Lister les ordinateurs.\n"
				+ "2: Lister les entreprises.\n" + "3: Afficher les détails d'un ordinateur.\n"
				+ "4: Créer un ordinateur.\n" + "5: Mettre à jour les informations d'un ordinateur.\n"
				+ "6: Supprimer un ordinateur.\n" + "7: Supprimer une entreprise.\n" + "8: Quitter.");
	}

	public void messagePage(int maximumPage) {
		System.out.println(new StringBuilder(
				"Entrez le numéro de la page à afficher compris entre 1 et " + maximumPage + " , (q) pour quitter"));
	}

	public void displayComputerList(List<DTOComputer> computerList) {
		for (DTOComputer computerDTO : computerList) {
			System.out.println(computerDTO);
		}
		System.out.println("\n");
	}

	public void displayCompanyList(List<Company> companyList) {
		for (Company c : companyList) {
			System.out.println(c);
		}
		System.out.println("\n");
	}

	public int askComputerID() {
		System.out.println("Entrez l'identifiant de l'ordinateur");
		return Integer.parseInt(getInput());
	}

	public String[] askComputerIDToDelete() {
		System.out.println("Entrez le(s) identifiant(s) ordinateur à supprimer (séparer par une virgule)");
		return getInput().split(",");
	}

	public int askCompanyIDToDelete() {
		System.out.println("Entrez l'identifiant de l'entreprise à supprimer");
		int id = 0;
		do {
			id = Integer.parseInt(getInput());
		} while (id == 0);
		return id;
	}

	public void displayComputer(DTOComputer c) {
		System.out.println(c);
	}

	public ArrayList<String> getComputerParams() {
		ArrayList<String> computerParams = new ArrayList<>();
		System.out.println("Le nom du nouveau Pc :\n");
		String newComputerName = getInput();
		computerParams.add(newComputerName);

		String newComputerDateI;
		do {
			System.out.println("La date d'introduction au format (yyyy-mm-dd)");
			newComputerDateI = getInput();
		} while (!newComputerDateI.matches(dateFormat));
		computerParams.add(newComputerDateI);

		String newComputerDateD;
		do {
			System.out.println("La date d'arret de production au format (yyyy-mm-dd)");
			newComputerDateD = getInput();
		} while (!newComputerDateI.matches(dateFormat));
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

		System.out.println("Entrez le nouveau nom: ");
		String newName = getInput();
		updateParams.add(newName);

		String newDateI;
		do {
			System.out.println("Entrez la nouvelle date d'introduction (yyyy-mm-dd): ");
			newDateI = getInput();
		} while (!newDateI.matches(dateFormat));

		updateParams.add(newDateI);

		String newDateD;
		do {
			System.out.println("Entrez la nouvelle date d'arret de production (yyyy-mm-dd): ");
			newDateD = getInput();
		} while (!newDateD.matches(dateFormat));
		updateParams.add(newDateD);

		System.out.println("Entrez le nouvel identifiant de l'entreprise: ");
		String newID = getInput();
		updateParams.add(newID);

		return updateParams;
	}

	public void updatedComputer() {
		System.out.println("Ordinateur mis a jour");
	}

	public void deletedComputer() {
		System.out.println("L'ordinateur à été supprimé.");
	}
	
	public void deletedCompany() {
		System.out.println("L'entreprise ainsi que ces ordinateurs on été supprimé.");
	}

	public void leftApplication() {
		System.out.println("Fin de l'application");
	}

	public void invelidInput() {
		System.out.println("Choix invalide veuillez réessayer !");
	}

	public void invalidDateInput() {
		System.out.println("Format de la date entré incorrecte valeur mise à jour à null !");
	}
}

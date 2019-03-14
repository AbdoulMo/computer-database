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
			// getAllComputers
			break;
		case 2:
			// getAllCompanys
			break;
		case 3:
			System.out.println("Entrez l'identifiant du pc à afficher");
			// getComputer
			System.out.println(computer);
			break;
		case 4:
			System.out.println("Le nom du nouveau Pc :\n");
			String name = getInput();
			System.out.println("La date d'introduction au format (yyyy-mm-dd)");
			try {
				java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(getInput());
				Date date = new Date(utilDate.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("La date d'arret de production au format (yyyy-mm-dd)");
			try {
				java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(getInput());
				Date date = new Date(utilDate.getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("L'identifiant de l'entreprise");
			int companyId = Integer.parseInt(getInput());
			// createComputer
			System.out.println("Nouvel ordinateur crée");
			break;
		case 5:
			System.out.println("Pour continuer il faut connaitre l'identifiant de l'ordinateur...\n"
					+ "Souhaitez vous continuer (o/n)");
			reponse = getInput();
			if (reponse.equals("o")) {
				System.out.println("Entrez l'identifiant de l'ordinateur à modifier: \n");
				id = Integer.parseInt(getInput());
				computer = getComputerDetails(id);
				boolean leaveOption = false;
				String optionInput = new String();
				while (!leaveOption) {
					System.out.println("Choisisez le paramètre a modifier " + "(1: Nom de l'ordinateur\n"
							+ " 2: Date d'introduction\n" + " 3: Date d'arrete de production\n"
							+ " 4: L'identifiant du fabriquant\n" + " 5: Pour terminer");
					int choixModif = Integer.parseInt(getInput());
					switch (choixModif) {
					case 1:
						System.out.println("Entrez le nouveau nom: ");
						optionInput = getInput();
						computer.setName(optionInput);
						computerDAO.updateComputer(computer);
						System.out.println("Nom modifié !");
						break;
					case 2:
						System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
						try {
							java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(getInput());
							Date date = new Date(utilDate.getTime());
							computer.setIntroduced(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						computerDAO.updateComputer(computer);
						System.out.println("Date d'introduction modifié !");
						break;
					case 3:
						System.out.println("Entrez la nouvelle date (yyyy-mm-dd): ");
						optionInput = getInput();
						try {
							java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(optionInput);
							Date date = new Date(utilDate.getTime());
							computer.setDiscontinued(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						computerDAO.updateComputer(computer);
						System.out.println("Date d'arrêt de production modifié !");
						break;
					case 4:
						System.out.println("Entrez le nouvel identifiant: ");
						optionInput = getInput();
						computer.setManufacturer_id(Integer.parseInt(optionInput));
						computerDAO.updateComputer(computer);
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
			break;
		case 6:
			System.out.println("Pour continuer il faut connaitre l'identifiant de l'ordinateur...\n"
					+ "Souhaitez vous continuer (o/n)");
			reponse = getInput();
			if (reponse.equals("o")) {
				System.out.println("Entrez l'identifiant de l'ordinateur à supprimer: \n");
				id = Integer.parseInt(getInput());
				computer = getComputerDetails(id);
				deleteComputer(id);
				System.out.println("L'ordinateur " + computer.getName() + " avec l'identifiant " + computer.getId()
						+ " a été supprimé.");
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

	public static void main(String[] args) {
		int choice = 0;
		Cli app = new Cli();
		while (!app.isLeaveApp()) {
			app.displayMenu();
			choice = Integer.parseInt(app.getInput());
			app.action(choice);
		}
	}
}

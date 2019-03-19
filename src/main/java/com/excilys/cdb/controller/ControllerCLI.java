package com.excilys.cdb.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.excilys.cdb.dao.DAOFactory;
import com.excilys.cdb.interfaces.IDAOCompany;
import com.excilys.cdb.interfaces.IDAOComputer;
import com.excilys.cdb.modele.Company;
import com.excilys.cdb.modele.Computer;
import com.excilys.cdb.vue.Cli;
import com.excilys.cdb.vue.Paging;

public class ControllerCLI {

	private IDAOComputer computerDAO;
	private IDAOCompany companyDAO;
	private Cli cli;

	private boolean leaveApp = false;

	private Computer computer = new Computer();

	public ControllerCLI(Cli cli) {
		this.cli = cli;
		computerDAO = DAOFactory.getDAOComputer("jdbc");
		companyDAO = DAOFactory.getDAOCompany("jdbc");
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

	public int createComputer(Computer computer) {
		return computerDAO.addComputer(computer);
	}

	public int updateComputer(Computer c) {
		return computerDAO.updateComputer(c);
	}

	public int deleteComputer(int id) {
		return computerDAO.deleteComputer(id);
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

	private int updateComputer(Computer computer, ArrayList<String> params) {
		switch (Integer.parseInt(params.get(1))) {
		case 1:
			String name = params.get(2);
			computer.setName(name);
			break;
		case 2:
			Date dateI = parseInputDate(params.get(2));
			computer.setIntroduced(dateI);
			break;
		case 3:
			Date dateD = parseInputDate(params.get(2));
			computer.setDiscontinued(dateD);
			break;
		case 4:
			int id = Integer.parseInt(params.get(2));
			computer.setManufacturer_id(id);
			break;
		default:
			break;
		}
		return computerDAO.updateComputer(computer);
	}

	private boolean handlePagingIndex(Paging p, int listSize) {
		System.out.println("Entrez (s) pour afficher les suivants, (p) pour les précédents ou (q) pour quitter");
		String command = this.cli.getInput();
		if (command.equals("q")) {
			return false;
		} else if (command.equals("p")) {
			if (p.getStartIndex() > 10) {
				p.setStartIndex(p.getStartIndex() - 10);
				p.setEndIndex(p.getEndIndex() - 10);
			} else {
				while (p.getStartIndex() > 0) {
					p.setStartIndex(p.getStartIndex() - 1);
					p.setEndIndex(p.getEndIndex() - 1);
				}
			}
		} else if (command.equals("s")) {
			if (p.getEndIndex() + 10 < listSize) {
				p.setStartIndex(p.getStartIndex() + 10);
				p.setEndIndex(p.getEndIndex() + 10);
			} else {
				while (p.getEndIndex() <= listSize - 1) {
					p.setStartIndex(p.getStartIndex() + 1);
					p.setEndIndex(p.getEndIndex() + 1);
				}
			}
		}
		return true;
	}

	private void displayComputerList(ArrayList<Computer> computerList) {
		Paging p = new Paging();
		boolean display = true;
		while (display) {
			this.cli.displayComputerList(p.showComputerList(computerList));
			display = this.handlePagingIndex(p, computerList.size());
		}
	}

	private void displayCompanyList(ArrayList<Company> companyList) {
		Paging p = new Paging();
		boolean display = true;
		while (display) {
			this.cli.displayCompanyList(p.showCompanyList(companyList));
			display = this.handlePagingIndex(p, companyList.size());
		}
	}

	public void action(int choice) {
		switch (choice) {
		case 1:
			ArrayList<Computer> computerList = this.getAllComputers();
			this.displayComputerList(computerList);
			break;
		case 2:
			ArrayList<Company> companyList = this.getAllCompanys();
			this.displayCompanyList(companyList);
			break;
		case 3:
			computer = this.computerDAO.getComputer(this.cli.askComputerID());
			this.cli.displayComputer(computer);
			break;
		case 4:
			Computer computer = new Computer();
			ArrayList<String> computerParams = this.cli.getComputerParams();
			computer.setName(computerParams.get(1));
			computer.setIntroduced(parseInputDate(computerParams.get(2)));
			computer.setDiscontinued(parseInputDate(computerParams.get(3)));
			computer.setManufacturer_id(Integer.parseInt(computerParams.get(4)));
			this.createComputer(computer);
			this.cli.createdComputer();
			break;
		case 5:
			computer = this.computerDAO.getComputer(this.cli.askComputerID());
			ArrayList<String> updateParams = this.cli.getUpdateComputerParams();
			boolean leaveOption = false;
			while (!leaveOption) {
				if (!updateParams.get(2).equals("5")) {
					leaveOption = true;
				} else {
					this.updateComputer(computer, updateParams);
				}
			}
			this.updateComputer(computer, updateParams);
			break;
		case 6:
			computer = this.computerDAO.getComputer(this.cli.askComputerID());
			this.computerDAO.deleteComputer(computer.getId());
			this.cli.deletedComputer();
			break;
		case 7:
			this.setLeaveApp(true);
			this.cli.leftApplication();
			break;
		default:
			this.cli.invelidInput();
			break;
		}
	}

	public void runApp() {
		while (!this.isLeaveApp()) {
			this.cli.displayMenu();
			this.action(Integer.parseInt(this.cli.getInput()));
		}
	}
}

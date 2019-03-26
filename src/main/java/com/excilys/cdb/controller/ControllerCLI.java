package com.excilys.cdb.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.excilys.cdb.dao.DatabaseParam;
import com.excilys.cdb.interfaces.IDAOCompany;
import com.excilys.cdb.interfaces.IDAOComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.vue.Cli;
import com.excilys.cdb.vue.Paging;

public class ControllerCLI {

	private final static Logger logger = Logger.getLogger(ControllerCLI.class);

	private IDAOComputer computerDAO;
	private IDAOCompany companyDAO;
	private Cli cli;

	private boolean leaveApp = false;

	private Computer computer = new Computer();

	public ControllerCLI(Cli cli) {
		this.cli = cli;
		computerDAO = DatabaseParam.getDAOComputer("jdbc");
		companyDAO = DatabaseParam.getDAOCompany("jdbc");
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

	private Date parseInputDate(String date) throws ParseException {
		Date parsedDate = null;
		java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
		parsedDate = new Date(utilDate.getTime());
		return parsedDate;
	}

	private int updateComputer(Computer computer, ArrayList<String> params) {
		int updateOption = 0;
		try {
			updateOption = Integer.parseInt(params.get(1));
		} catch (NumberFormatException e) {
			logger.error("Invalid update option !", e);
		}
		switch (updateOption) {
		case 1:
			String name = params.get(2);
			computer.setName(name);
			break;
		case 2:
			Date dateI = null;
			try {
				parseInputDate(params.get(2));
				computer.setIntroduced(dateI);
			} catch (ParseException e1) {
				logger.error("Invalid input date format !", e1);
			}
			break;
		case 3:
			Date dateD = null;
			try {
				parseInputDate(params.get(2));
				computer.setDiscontinued(dateD);
			} catch (ParseException e1) {
				logger.error("Invalid input date format !", e1);
			}
			break;
		case 4:
			int id = 0;
			try {
				Integer.parseInt(params.get(2));
				computer.setManufacturer_id(id);
			} catch (NumberFormatException e) {
				logger.error("Invalid manufacturer id !", e);
			}
			break;
		default:
			break;
		}
		this.cli.updatedComputer();
		return computerDAO.updateComputer(computer);
	}

	private void displayComputerList(ArrayList<Computer> computerList) {
		Paging p = new Paging();
		boolean display = true;
		while (display) {
			this.cli.displayComputerList(p.showComputerList(computerList));
			String action = this.cli.getInput();
			display = p.handlePagingIndex(action, computerList.size());
		}
	}

	private void displayCompanyList(ArrayList<Company> companyList) {
		Paging p = new Paging();
		boolean display = true;
		while (display) {
			this.cli.displayCompanyList(p.showCompanyList(companyList));
			String action = this.cli.getInput();
			display = p.handlePagingIndex(action, companyList.size());
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
			try {
				computer.setIntroduced(parseInputDate(computerParams.get(2)));
			} catch (ParseException e) {
				logger.error("Invalid input date format !", e);
			}
			try {
				computer.setDiscontinued(parseInputDate(computerParams.get(3)));
			} catch (ParseException e) {
				logger.error("Invalid input date format !", e);
			}
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
			try {
				this.action(Integer.parseInt(this.cli.getInput()));
			} catch (NumberFormatException e) {
				this.cli.invelidInput();
				logger.error("Invalid menu input !", e);
			}
		}
	}
}

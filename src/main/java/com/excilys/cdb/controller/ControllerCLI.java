package com.excilys.cdb.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.services.CompanyServices;
import com.excilys.cdb.services.ComputerServices;
import com.excilys.cdb.vue.Cli;
import com.excilys.cdb.vue.Paging;

public class ControllerCLI {

	private final static Logger logger = Logger.getLogger(ControllerCLI.class);

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	private ComputerServices computerServices;
	private CompanyServices companyServices;

	private Cli cli;

	private boolean leaveApp = false;

	public ControllerCLI() {
		cli = new Cli();
		computerServices = (ComputerServices) applicationContext.getBean(ComputerServices.class);
		companyServices = (CompanyServices) applicationContext.getBean(CompanyServices.class);
	}

	public boolean isLeaveApp() {
		return leaveApp;
	}

	public void setLeaveApp(boolean b) {
		this.leaveApp = b;
	}

	public void action(int choice) throws DataNotFoundException {
		switch (choice) {
		case 1:
			ArrayList<DTOComputer> computerList = computerServices.getAllComputer();
			Paging paging = new Paging(computerList);
			int page = 1;
			boolean display = true;
			do {
				cli.displayComputerList(paging.showComputerList(page));
				cli.messagePage(paging.getNumberOfPage());
				String input = cli.getInput();
				if (input.equals("q")) {
					display = false;
				} else {
					try {
						page = Integer.parseInt(input);
						if (page > paging.getNumberOfPage()) {
							page = paging.getNumberOfPage();
						}
					} catch (NumberFormatException e) {
						logger.error("Invalid page number input !", e);
					}
				}
			} while (display);
			break;
		case 2:
			ArrayList<Company> companyList = companyServices.getAllCompany();
			cli.displayCompanyList(companyList);
			break;
		case 3:
			DTOComputer dtoComputer = computerServices.getComputerByID(cli.askComputerID());
			cli.displayComputer(dtoComputer);
			break;
		case 4:
			ArrayList<String> computerParams = cli.getComputerParams();
			computerServices.addComputer(computerParams.get(0), 
										computerParams.get(1), 
										computerParams.get(2),
										computerParams.get(3));
			cli.createdComputer();
			break;
		case 5:
			DTOComputer dtoEditComputer = computerServices.getComputerByID(cli.askComputerID());
			ArrayList<String> updateParams = cli.getUpdateComputerParams();
			computerServices.editComputer(String.valueOf(dtoEditComputer.getId()),
											updateParams.get(0), 
											updateParams.get(1), 
											updateParams.get(2), 
											updateParams.get(3));
			break;
		case 6:
			computerServices.deleteComputer(cli.askComputerIDToDelete());
			cli.deletedComputer();
			break;
		case 8:
			setLeaveApp(true);
			cli.leftApplication();
			break;
		default:
			cli.invelidInput();
			break;
		}
	}

	public void runApp() {
		while (!isLeaveApp()) {
			cli.displayMenu();
			try {
				action(Integer.parseInt(cli.getInput()));
			} catch (NumberFormatException e) {
				cli.invelidInput();
				logger.error("Invalid menu input !", e);
			} catch (DataNotFoundException e) {
				logger.error("Unable to find data !", e);
			}
		}
	}
}

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
	
	private enum ChoixMenu{
		
		DISPLAY_ALL_COMPUTER(1),
		DISPLAY_ALL_COMPANY(2),
		DISPLAY_COMPUTER_WITH_ID(3),
		CREATE_COMPUTER(4),
		UPDATE_COMPUTER(5),
		DELETE_COMPUTER(6),
		DELETE_COMPANY(7),
		LEAVE_APP(8);
		
		@SuppressWarnings("unused")
		private int selection = 0;
		
		 ChoixMenu(int selection) {
			this.selection = selection;
		}
	}

	private final static Logger logger = Logger.getLogger(ControllerCLI.class);

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	private Cli cli;
	private ComputerServices computerServices;
	private CompanyServices companyServices;
	

	private boolean leaveApp = false;

	public ControllerCLI() {
		cli = (Cli) applicationContext.getBean(Cli.class);
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
		switch (ChoixMenu.values()[choice - 1]) {
		case DISPLAY_ALL_COMPUTER:
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
		case DISPLAY_ALL_COMPANY:
			ArrayList<Company> companyList = companyServices.getAllCompany();
			cli.displayCompanyList(companyList);
			break;
		case DISPLAY_COMPUTER_WITH_ID:
			DTOComputer dtoComputer = computerServices.getComputerByID(cli.askComputerID());
			cli.displayComputer(dtoComputer);
			break;
		case CREATE_COMPUTER:
			ArrayList<String> computerParams = cli.getComputerParams();
			computerServices.addComputer(computerParams.get(0), 
										computerParams.get(1), 
										computerParams.get(2),
										computerParams.get(3));
			cli.createdComputer();
			break;
		case UPDATE_COMPUTER:
			DTOComputer dtoEditComputer = computerServices.getComputerByID(cli.askComputerID());
			ArrayList<String> updateParams = cli.getUpdateComputerParams();
			computerServices.editComputer(String.valueOf(dtoEditComputer.getId()),
											updateParams.get(0), 
											updateParams.get(1), 
											updateParams.get(2), 
											updateParams.get(3));
			break;
		case DELETE_COMPUTER:
			computerServices.deleteComputer(cli.askComputerIDToDelete());
			cli.deletedComputer();
			break;
		case DELETE_COMPANY:
			companyServices.deleteCompany(cli.askCompanyIDToDelete());
			cli.deletedCompany();
			break;
		case LEAVE_APP:
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

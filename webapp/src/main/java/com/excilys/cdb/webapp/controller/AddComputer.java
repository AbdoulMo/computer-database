package com.excilys.cdb.webapp.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.exception.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyServices;
import com.excilys.cdb.service.ComputerServices;

@Controller
public class AddComputer {

	private final Logger logger = Logger.getLogger(AddComputer.class);
	private CompanyServices companyServices;
	private ComputerServices computerServices;

	public AddComputer(CompanyServices companyServices, ComputerServices computerServices) {
		this.companyServices = companyServices;
		this.computerServices = computerServices;
	}

	@GetMapping({ "/addComputer" })
	public String displayPage(Model model) {
		ArrayList<Company> companyList = new ArrayList<>();
		try {
			companyList = companyServices.getAllCompany();
		} catch (DataNotFoundException e) {
			logger.error("Unable to get all company", e);
		}

		model.addAttribute("companyList", companyList);

		return "addComputer";
	}

	@PostMapping({ "/addComputer" })
	public String saveNewComputer(@RequestParam(value = "computerName", required = true) String computerName,
			@RequestParam(value = "introduced", required = true) String introduced,
			@RequestParam(value = "discontinued", required = true) String discontinued,
			@RequestParam(value = "companyId", required = true) String companyId) {

		if(computerServices.addComputer(computerName, introduced, discontinued, companyId)) {
			return "dashboard";
		}
		return "addComputer";
	}
}

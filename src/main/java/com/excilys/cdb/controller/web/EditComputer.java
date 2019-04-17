package com.excilys.cdb.controller.web;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.services.CompanyServices;
import com.excilys.cdb.services.ComputerServices;

@Controller
public class EditComputer {

	private final Logger logger = Logger.getLogger(EditComputer.class);
	private ComputerServices computerServices;
	private CompanyServices companyServices;

	public EditComputer(ComputerServices computerServices, CompanyServices companyServices) {
		this.computerServices = computerServices;
		this.companyServices = companyServices;
	}

	@GetMapping({ "/editComputer" })
	public String displayPage(
			@RequestParam(value = "computerIDToEdit", required = false, defaultValue = "0") int computerIDToEdit,
			Model model) {
		DTOComputer computerToEdit = null;
		ArrayList<Company> companyList = new ArrayList<>();
		try {
			computerToEdit = computerServices.getComputerByID(computerIDToEdit);
		} catch (DataNotFoundException e) {
			logger.error("Unable to find specified computer in the database");
		}
		try {
			companyList = companyServices.getAllCompany();
		} catch (DataNotFoundException e) {
			logger.error("Unable to find all company in the database");
		}
		model.addAttribute("computerToEdit", computerToEdit);
		model.addAttribute("companyList", companyList);

		return "editComputer";
	}

	@PostMapping({ "/editComputer" })
	public String saveEditComputer(@RequestParam(value = "computerID", required = true) String computerID,
			@RequestParam(value = "computerName", required = true) String computerName,
			@RequestParam(value = "introduced", required = true) String computerIntro,
			@RequestParam(value = "discontinued", required = true) String computerDiscon,
			@RequestParam(value = "companyId", required = true) String companyId) {

		computerServices.editComputer(computerID, computerName, computerIntro, computerDiscon, companyId);

		return "dashboard";
	}
}

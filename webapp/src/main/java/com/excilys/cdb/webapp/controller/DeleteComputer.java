package com.excilys.cdb.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.service.ComputerServices;


@Controller
public class DeleteComputer {

	private ComputerServices computerServices;

	public DeleteComputer(ComputerServices computerServices) {
		this.computerServices = computerServices;
	}

	@PostMapping({ "/deleteComputer" })
	public String deleteComputer(@RequestParam(value = "selection", required = true) String listComputersID) {
		String[] computersID = listComputersID.split(",");
		try {
			computerServices.deleteComputer(computersID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "dashboard";
	}
}

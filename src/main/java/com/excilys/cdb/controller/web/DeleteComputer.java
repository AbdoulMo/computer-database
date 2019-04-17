package com.excilys.cdb.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.services.ComputerServices;

@Controller
public class DeleteComputer {

	private ComputerServices computerServices;

	public DeleteComputer(ComputerServices computerServices) {
		this.computerServices = computerServices;
	}

	@PostMapping({ "/deleteComputer" })
	public String deleteComputer(@RequestParam(value = "selection", required = true) String listComputersID) {
		String[] computersID = listComputersID.split(",");
		computerServices.deleteComputer(computersID);

		return "dashboard";
	}
}

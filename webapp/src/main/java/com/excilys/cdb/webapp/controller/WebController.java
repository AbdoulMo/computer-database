package com.excilys.cdb.webapp.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.exception.DataNotFoundException;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.persistence.paging.Paging;
import com.excilys.cdb.service.ComputerServices;


@Controller
public class WebController {

	private final Logger logger = Logger.getLogger(WebController.class);
	private ComputerServices computerService;

	private Paging paging;

	public WebController(ComputerServices computerServices) {
		this.computerService = computerServices;
	}

	@GetMapping({ "/dashboard" })
	public String dashboard(@RequestParam(value = "search", required = false) String searchPattern,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageQuery, Model model) {

		ArrayList<DTOComputer> listToDisplay = new ArrayList<>();
		ArrayList<DTOComputer> allComputerList = new ArrayList<>();
		try {
			allComputerList = computerService.getAllComputer();
		} catch (DataNotFoundException e) {
			logger.error("Unable to find all computer data in the database", e);
		}
		if (searchPattern != null) {
			List<DTOComputer> filteredComputerList = allComputerList.stream()
					.filter(computerDto -> computerDto.getName().matches("(?i).*" + searchPattern + ".*")
							| computerDto.getManufacturer_name().matches("(?i).*" + searchPattern + ".*"))
					.collect(Collectors.toList());
			listToDisplay = (ArrayList<DTOComputer>) filteredComputerList;
		} else {
			listToDisplay = allComputerList;
		}

		if (orderBy != null) {
			switch (orderBy) {
			case "name":
				listToDisplay.sort(Comparator.comparing(DTOComputer::getName));
				break;
			case "introduced":
				listToDisplay.sort(Comparator.comparing(DTOComputer::getIntroduced));
				break;
			case "discontinued":
				listToDisplay.sort(Comparator.comparing(DTOComputer::getDiscontinued));
				break;
			case "company":
				listToDisplay.sort(Comparator.comparing(DTOComputer::getManufacturer_name));
				break;
			}
		}

		paging = new Paging(listToDisplay);
		model.addAttribute("computersFound", listToDisplay.size());
		int page = 1;
		if (pageQuery != 0) {
			page = pageQuery;
			if (page > paging.getNumberOfPage()) {
				page = paging.getNumberOfPage();
			}
		}
		List<DTOComputer> displayedComputer = paging.showComputerList(page);

		model.addAttribute("displayedComputer", displayedComputer);
		model.addAttribute("currentPage", page);
		model.addAttribute("numberOfPage", paging.getNumberOfPage());
		return "dashboard";
	}
}

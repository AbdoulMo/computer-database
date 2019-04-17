//package com.excilys.cdb.controller.web;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.excilys.cdb.exceptions.DataNotFoundException;
//import com.excilys.cdb.model.DTOComputer;
//import com.excilys.cdb.services.ComputerServices;
//import com.excilys.cdb.vue.Paging;
//
//@Controller
//public class Dashboard {
//	
//	private WebApplicationContext applicationContext;
//	private ComputerServices computerService;
//	private static Paging paging;
//	
//	public Dashboard(ComputerServices computerServices) {
//		this.computerService = computerServices;
//	}
//	
//	@GetMapping({ "/", "/dashboard" })
//	public String hello(@RequestParam(value="search", required=false) String searchPattern,
//			@RequestParam(value="orderBy", required=false) String orderBy,
//			@RequestParam(value="page", required=false, defaultValue = "0") int pageQuery,
//			Model model) {
//
//		ArrayList<DTOComputer> listToDisplay = new ArrayList<>();
//		ArrayList<DTOComputer> allComputerList = new ArrayList<>();
//		try {
//			allComputerList = computerService.getAllComputer();
//		} catch (DataNotFoundException e) {
//			e.printStackTrace();
//		}
//		if (searchPattern != null) {
//			List<DTOComputer> filteredComputerList = allComputerList.stream()
//					.filter(computerDto -> computerDto.getName().matches("(?i).*" + searchPattern + ".*")
//							| computerDto.getManufacturer_name().matches("(?i).*" + searchPattern + ".*"))
//					.collect(Collectors.toList());
//			listToDisplay = (ArrayList<DTOComputer>) filteredComputerList;
//		} else {
//			listToDisplay = allComputerList;
//		}
//
//		if (orderBy != null) {
//			switch (orderBy) {
//			case "name":
//				listToDisplay.sort(Comparator.comparing(DTOComputer::getName));
//				break;
//			case "introduced":
//				listToDisplay.sort(Comparator.comparing(DTOComputer::getIntroduced));
//				break;
//			case "discontinued":
//				listToDisplay.sort(Comparator.comparing(DTOComputer::getDiscontinued));
//				break;
//			case "company":
//				listToDisplay.sort(Comparator.comparing(DTOComputer::getManufacturer_name));
//				break;
//			}
//		}
//
//		paging = new Paging(listToDisplay);
////		request.setAttribute("computersFound", listToDisplay.size());
//		model.addAttribute(listToDisplay.size());
//		int page = 1;
//		if (pageQuery != 0) {
//			page = pageQuery;
//			if (page > paging.getNumberOfPage()) {
//				page = paging.getNumberOfPage();
//			}
//		}
//		List<DTOComputer> displayedComputer = paging.showComputerList(page);
//
////		request.setAttribute("displayedComputer", displayedComputer);
//		model.addAttribute(displayedComputer);
////		request.setAttribute("currentPage", page);
//		model.addAttribute(page);
////		request.setAttribute("numberOfPage", paging.getNumberOfPage());
//		model.addAttribute(paging.getNumberOfPage());
////		RequestDispatcher requestDispatcher = getServletContext()
////				.getRequestDispatcher("/web-ressources/views/dashboard.jsp");
////		requestDispatcher.forward(request, response);
//		
//		return "dashboard";
//	}
//}

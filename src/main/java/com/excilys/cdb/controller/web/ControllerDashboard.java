package com.excilys.cdb.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.services.ComputerServices;
import com.excilys.cdb.vue.Paging;

/**
 * Servlet implementation class ControllerDashboard
 */
@WebServlet("/dashboard")
public class ControllerDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContext;
	private ComputerServices computerServices;
	private static Paging paging;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerDashboard() {
		super();
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		computerServices = (ComputerServices) applicationContext.getBean(ComputerServices.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<DTOComputer> listToDisplay = new ArrayList<>();
		ArrayList<DTOComputer> allComputerList = new ArrayList<>();
		try {
			allComputerList = computerServices.getAllComputer();
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}
		String searchPattern = request.getParameter("search");
		if (searchPattern != null) {
			List<DTOComputer> filteredComputerList = allComputerList.stream()
					.filter(computerDto -> computerDto.getName().matches("(?i).*" + searchPattern + ".*")
							| computerDto.getManufacturer_name().matches("(?i).*" + searchPattern + ".*"))
					.collect(Collectors.toList());
			listToDisplay = (ArrayList<DTOComputer>) filteredComputerList;
		} else {
			listToDisplay = allComputerList;
		}

		String orderBy = request.getParameter("orderBy");

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
		request.setAttribute("computersFound", listToDisplay.size());

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			if (page > paging.getNumberOfPage()) {
				page = paging.getNumberOfPage();
			}
		}
		List<DTOComputer> displayedComputer = paging.showComputerList(page);

		request.setAttribute("displayedComputer", displayedComputer);
		request.setAttribute("currentPage", page);
		request.setAttribute("numberOfPage", paging.getNumberOfPage());
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/web-ressources/views/dashboard.jsp");
		requestDispatcher.forward(request, response);
	}

}

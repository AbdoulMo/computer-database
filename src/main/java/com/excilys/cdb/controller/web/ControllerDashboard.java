package com.excilys.cdb.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	private static ComputerServices computerServices;
	private static Paging paging;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerDashboard() {
		super();
		computerServices = new ComputerServices();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<DTOComputer> dtoComputerList = new ArrayList<>();
		try {
			dtoComputerList = computerServices.getAllComputer();
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}
		paging = new Paging(dtoComputerList);

		int page = 1;
		int nbComputerDisplayed = 1;
		if (request.getParameter("nbComputerDisplayed") != null) {
			nbComputerDisplayed = Integer.parseInt(request.getParameter("nbComputerDisplayed"));
			paging.setMaxDisplay(nbComputerDisplayed);
		}
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			if (page > paging.getNumberOfPage()) {
				page = paging.getNumberOfPage();
			}
		}
		List<DTOComputer> displayedComputer = paging.showComputerList(page);

		HttpSession session = request.getSession();
		session.setAttribute("computersFound", dtoComputerList.size());
		session.setAttribute("displayedComputer", displayedComputer);
		session.setAttribute("currentPage", page);
		session.setAttribute("numberOfPage", paging.getNumberOfPage());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/web-ressources/views/dashboard.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

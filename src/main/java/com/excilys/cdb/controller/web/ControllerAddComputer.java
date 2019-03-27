package com.excilys.cdb.controller.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.services.CompanyServices;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/addComputer")
public class ControllerAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CompanyServices companyServices;
	private static ComputerServices computerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerAddComputer() {
		super();
		companyServices = new CompanyServices();
		computerServices = new ComputerServices();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Company> companyList = new ArrayList<>();
		try {
			companyList = companyServices.getAllCompany();
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("companyList", companyList);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/web-ressources/views/addComputer.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (computerServices.addComputer(request.getParameter("computerName"), request.getParameter("introduced"),
				request.getParameter("discontinued"), request.getParameter("companyId"))) {
			doGet(request, response);
		}else {
			response.getWriter().write("Unable to add computer");
		}
	}

}

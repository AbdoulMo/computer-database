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
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.services.CompanyServices;
import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ControllerEditComputer
 */
@WebServlet("/editComputer")
public class ControllerEditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ComputerServices computerServices;
	private static CompanyServices companyServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerEditComputer() {
		super();
		computerServices = new ComputerServices();
		companyServices = new CompanyServices();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String computerIDToEdit = request.getParameter("computerIDToEdit");
		int computerId = "".equals(computerIDToEdit) ? 0 : Integer.parseInt(computerIDToEdit);

		DTOComputer computerToEdit = null;
		ArrayList<Company> companyList = new ArrayList<>();
		try {
			computerToEdit = computerServices.getComputerByID(computerId);
			companyList = companyServices.getAllCompany();
		} catch (DataNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("computerToEdit", computerToEdit);
		request.setAttribute("companyList", companyList);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/web-ressources/views/editComputer.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String computerId, computerName, computerIntro, computerDiscon, companyId;
		computerId = request.getParameter("computerID");
		computerName =  request.getParameter("computerName");
		computerIntro = request.getParameter("introduced");
		computerDiscon = request.getParameter("discontinued");
		companyId = request.getParameter("companyId");
		computerServices.editComputer(computerId, computerName, computerIntro, computerDiscon, companyId);
		response.sendRedirect("dashboard");
	}

}

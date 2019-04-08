package com.excilys.cdb.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ContollerDeleteComputer
 */
@WebServlet("/deleteComputer")
public class ContollerDeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ComputerServices computerServices;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContollerDeleteComputer() {
		super();
		computerServices = new ComputerServices();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String[] computersID = request.getParameter("selection").split(",");
		if(computerServices.deleteComputer(computersID)) {
			doGet(request, response);
		}
	}

}

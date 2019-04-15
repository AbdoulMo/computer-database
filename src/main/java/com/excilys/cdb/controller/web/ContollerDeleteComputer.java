package com.excilys.cdb.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.services.ComputerServices;

/**
 * Servlet implementation class ContollerDeleteComputer
 */
@WebServlet("/deleteComputer")
public class ContollerDeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContext;
	private static ComputerServices computerServices;

	@Override
	public void init() throws ServletException {
		super.init();
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		computerServices = (ComputerServices) applicationContext.getBean(ComputerServices.class);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContollerDeleteComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] computersID = request.getParameter("selection").split(",");
		if (computerServices.deleteComputer(computersID)) {
			doGet(request, response);
		}
	}

}

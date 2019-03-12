package com.excilys.training.java.modele;

import com.excilys.training.java.services.interfaces.IDAOComputer;
import com.excilys.training.java.services.jdbc.JDBCComputer;

public class Main {

	public static void main(String[] args) {

		IDAOComputer cDAO = new JDBCComputer();
		Computer c = cDAO.getComputer(7);
		System.out.println(c.toString());
	}

}

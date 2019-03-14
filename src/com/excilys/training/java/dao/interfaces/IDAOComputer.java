package com.excilys.training.java.dao.interfaces;

import java.util.ArrayList;

import com.excilys.training.java.modele.Computer;

public interface IDAOComputer {

	public Computer getComputer(int id);
	
	public ArrayList<Computer> getAllComputers();
	
	public int addComputer(Computer c);
	
	public int deleteComputer(Computer c);
	
	public int updateComputer(Computer c);
}

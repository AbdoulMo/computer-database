package com.excilys.training.java.services.interfaces;

import java.util.List;

import com.excilys.training.java.modele.Computer;

public interface IDAOComputer {

	public Computer getComputer(int id);
	
	public List<Computer> getAllComputers();
	
	public int addComputer(Computer c);
	
	public int deleteComputer(Computer c);
	
	public int updateComputer(Computer c);
}

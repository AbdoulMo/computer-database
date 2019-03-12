package com.excilys.training.java.services.interfaces;

import java.util.List;

import com.excilys.training.java.modele.Computer;

public interface IDAOComputer {

	public Computer getComputer(String name);
	
	public List<Computer> getAllComputers();
	
	public boolean addComputer(Computer c);
	
	public boolean deleteComputer(Computer c);
	
	public boolean updateComputer(Computer c);
}

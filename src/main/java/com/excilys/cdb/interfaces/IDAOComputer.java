package com.excilys.cdb.interfaces;

import java.util.List;

import com.excilys.cdb.modele.Computer;

public interface IDAOComputer {

	public Computer getComputer(int id);
	
	public List<Computer> getAllComputers();
	
	public int addComputer(Computer c);
	
	public int deleteComputer(Computer c);
	
	public int updateComputer(Computer c);
}

package com.excilys.cdb.interfaces;

import java.util.ArrayList;

import com.excilys.cdb.modele.Computer;

public interface IDAOComputer {

	/**
	 * Gets a computer 
	 * @param id the id of the computer
	 * @return the computer
	 */
	public Computer getComputer(int id);
	
	/**
	 * Gets all computers
	 * @return all computers
	 */
	public ArrayList<Computer> getAllComputers();
	
	/**
	 * Add a computer
	 * @param c a computer
	 * @return an int that indicates the number of rows affected
	 */
	public int addComputer(Computer c);
	
	/**
	 * Delete a computer
	 * @param id the id of the computer
	 * @return an int that indicates the number of rows affected
	 */
	public int deleteComputer(int id);
	
	/**
	 * Update a computer
	 * @param c a computer
	 * @return an int that indicates the number of rows affected
	 */
	public int updateComputer(Computer c);
}

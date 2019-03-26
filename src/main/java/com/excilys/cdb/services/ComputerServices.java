package com.excilys.cdb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.cdb.dao.JDBCComputer;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.model.MapperComputer;
import com.excilys.cdb.vue.Paging;

public class ComputerServices {

	private static JDBCComputer daoComputer = new JDBCComputer();
	private static Paging paging;

	public DTOComputer getComputerByID(int id) throws DataNotFoundException {
		Optional<Computer> computer = daoComputer.getComputerByID(id);
		if (!computer.isPresent()) {
			throw new DataNotFoundException();
		} else {
			DTOComputer dtoComputer = MapperComputer.objectToDTO(computer.get());
			return dtoComputer;
		}
	}

	public ArrayList<DTOComputer> getAllComputer() throws DataNotFoundException {
		ArrayList<Computer> computerList = new ArrayList<>();
		computerList = daoComputer.getAllComputers();
		if (computerList.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			ArrayList<DTOComputer> dtoComputersList = new ArrayList<>();
			for (Computer computer : computerList) {
				dtoComputersList.add(MapperComputer.objectToDTO(computer));
			}
			return dtoComputersList;
		}
	}
}

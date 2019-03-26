package com.excilys.cdb.services;

import java.util.ArrayList;

import com.excilys.cdb.dao.JDBCCompany;
import com.excilys.cdb.dao.JDBCComputer;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.model.MapperComputer;

public class ComputerServices {

	private static JDBCComputer daoComputer = new JDBCComputer();
	private static JDBCCompany daoCompany = new JDBCCompany();

//	public DTOComputer getComputerByID(int id) throws DataNotFoundException {
//		Optional<Computer> computer = daoComputer.getComputerByID(id);
//		if (!computer.isPresent()) {
//			throw new DataNotFoundException();
//		} else {
//			DTOComputer dtoComputer = MapperComputer.objectToDTO(computer.get());
//			return dtoComputer;
//		}
//	}

	public ArrayList<DTOComputer> getAllComputer() throws DataNotFoundException {
		ArrayList<Computer> computerList = new ArrayList<>();
		ArrayList<Company> companyList = new ArrayList<>();
		computerList = daoComputer.getAllComputers();
		companyList = daoCompany.getAllCompany();
		if (computerList.isEmpty() | companyList.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			ArrayList<DTOComputer> dtoComputersList = new ArrayList<>();
			for (Computer computer : computerList) {
				Company company = companyList.stream().filter(c -> c.getId() == computer.getManufacturer_id())
						.findFirst().orElse(null);
				dtoComputersList.add(MapperComputer.objectToDTO(computer, company));
			}
			return dtoComputersList;
		}
	}
}

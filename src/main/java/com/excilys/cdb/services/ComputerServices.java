package com.excilys.cdb.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.cdb.dao.JDBCCompany;
import com.excilys.cdb.dao.JDBCComputer;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.model.MapperComputer;

public class ComputerServices {

	private JDBCComputer daoComputer;
	private JDBCCompany daoCompany;
	
	public ComputerServices(JDBCCompany daoCompany, JDBCComputer daoComputer) {
		this.daoCompany = daoCompany;
		this.daoComputer = daoComputer;
	}

	public ArrayList<DTOComputer> getAllComputer() throws DataNotFoundException {
		ArrayList<Computer> computerList = daoComputer.getAllComputers();
		ArrayList<Company> companyList = daoCompany.getAllCompany();

		if (computerList.isEmpty() | companyList.isEmpty()) {
			throw new DataNotFoundException();
		}
		ArrayList<DTOComputer> dtoComputersList = new ArrayList<>();
		for (Computer computer : computerList) {
			Company company = companyList.stream().filter(c -> c.getId() == computer.getManufacturer_id()).findFirst()
					.orElse(null);
			dtoComputersList.add(MapperComputer.objectToDTO(computer, company));
		}
		return dtoComputersList;
	}
	
	public ArrayList<DTOComputer> searchComputer(ArrayList<DTOComputer> dtoComputersList, String pattern){
		
		return null;
	}

	private Optional<Date> parseInputDate(String date) {
		Date parsedDate = null;
		java.util.Date utilDate = null;
		try {
			utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			parsedDate = new Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(parsedDate);
	}

	public Boolean addComputer(String name, String introduced, String discontinued, String company_id) {

		Date parsedDateIntroduced = parseInputDate(introduced).get();
		Date parsedDateDiscontinued = parseInputDate(discontinued).get();

		String newName = "".equals(name) ? "default name" : name;
		Date introducedDate = parsedDateIntroduced != null ? parsedDateDiscontinued : null;
		Date discontinuedDate = parsedDateDiscontinued != null ? parsedDateDiscontinued : null;
		int companyId = "0".equals(company_id) ? 0 : Integer.parseInt(company_id);

		Computer computer = new Computer.ComputerBuilder().withName(newName).withIntroduced(introducedDate)
				.withDiscontinued(discontinuedDate).withManufacturerID(companyId).build();

		return daoComputer.addComputer(computer);
	}

	public DTOComputer getComputerByID(int id) throws DataNotFoundException {
		Optional<Computer> optionalComputer = daoComputer.getComputerByID(id);
		ArrayList<Company> companyList = daoCompany.getAllCompany();
		if (!optionalComputer.isPresent() | companyList.isEmpty()) {
			throw new DataNotFoundException();
		}
		Computer computer = optionalComputer.get();
		Company company = companyList.stream().filter(c -> c.getId() == computer.getManufacturer_id()).findFirst()
				.orElse(null);
		return MapperComputer.objectToDTO(computer, company);
	}

	public boolean editComputer(String id, String name, String introduced, String discontinued, String company_id) {
		
		Date parsedDateIntroduced = parseInputDate(introduced).get();
		Date parsedDateDiscontinued = parseInputDate(discontinued).get();
		
		int computerId = Integer.parseInt(id);
		String newName = "".equals(name) ? "default name" : name;
		Date introducedDate = parsedDateIntroduced != null ? parsedDateDiscontinued : null;
		Date discontinuedDate = parsedDateDiscontinued != null ? parsedDateDiscontinued : null;
		int companyId = "0".equals(company_id) ? 0 : Integer.parseInt(company_id);
		
		Computer computer = new Computer.ComputerBuilder()
				.withID(computerId)
				.withName(newName)
				.withIntroduced(introducedDate)
				.withDiscontinued(discontinuedDate)
				.withManufacturerID(companyId)
				.build();
		
		return daoComputer.updateComputer(computer);
	}
	
	public boolean deleteComputer(String[] paramComputerID) {
		boolean result = false;
		for(String id : paramComputerID) {
			if(daoComputer.deleteComputer(Integer.parseInt(id))) {
				result = true;
			}
		}
		return result;
	}
}

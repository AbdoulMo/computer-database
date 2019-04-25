package com.excilys.cdb.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.excilys.cdb.dao.IDAOCompany;
import com.excilys.cdb.dao.IDAOComputer;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.DTOComputer;
import com.excilys.cdb.model.MapperComputer;

@Service
public class ComputerServices {

	private IDAOComputer daoComputer;
	private IDAOCompany daoCompany;

	public ComputerServices(IDAOCompany daoCompany, IDAOComputer daoComputer) {
		this.daoCompany = daoCompany;
		this.daoComputer = daoComputer;
	}

	public ArrayList<DTOComputer> getAllComputer() throws DataNotFoundException {
		ArrayList<Computer> computerList = daoComputer.findAll();
		ArrayList<Company> companyList = daoCompany.findAll();

		if (computerList.isEmpty() | companyList.isEmpty()) {
			throw new DataNotFoundException();
		}
		ArrayList<DTOComputer> dtoComputersList = new ArrayList<>();
		for (Computer computer : computerList) {
			Company company = companyList.stream().filter(c -> c.getId() == computer.getManufacturerId()).findFirst()
					.orElse(null);
			dtoComputersList.add(MapperComputer.objectToDTO(computer, company));
		}
		return dtoComputersList;
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

	public boolean addComputer(String name, String introduced, String discontinued, String company_id) {

		Date parsedDateIntroduced = parseInputDate(introduced).get();
		Date parsedDateDiscontinued = parseInputDate(discontinued).get();

		String newName = "".equals(name) ? "default name" : name;
		Date introducedDate = parsedDateIntroduced != null ? parsedDateDiscontinued : null;
		Date discontinuedDate = parsedDateDiscontinued != null ? parsedDateDiscontinued : null;
		int companyId = "0".equals(company_id) ? 0 : Integer.parseInt(company_id);

		Computer computer = new Computer.ComputerBuilder().withName(newName).withIntroduced(introducedDate)
				.withDiscontinued(discontinuedDate).withManufacturerID(companyId).build();

		Computer savedComputer = daoComputer.save(computer);
		return savedComputer == null ? false : true;
	}

	public DTOComputer getComputerByID(int id) throws DataNotFoundException {
		Optional<Computer> optionalComputer = daoComputer.findById(id);
		ArrayList<Company> companyList = daoCompany.findAll();
		if (!optionalComputer.isPresent() | companyList.isEmpty()) {
			throw new DataNotFoundException();
		}
		Computer computer = optionalComputer.get();
		Company company = companyList.stream().filter(c -> c.getId() == computer.getManufacturerId()).findFirst()
				.orElse(null);
		return MapperComputer.objectToDTO(computer, company);
	}

	public boolean editComputer(String id, String name, String introduced, String discontinued, String company_id) {

		Date parsedDateIntroduced = parseInputDate(introduced).get();
		Date parsedDateDiscontinued = parseInputDate(discontinued).get();

		Integer computerId = Integer.valueOf(id);
		String newName = "".equals(name) ? "default name" : name;
		Date introducedDate = parsedDateIntroduced != null ? parsedDateDiscontinued : null;
		Date discontinuedDate = parsedDateDiscontinued != null ? parsedDateDiscontinued : null;
		int companyId = "0".equals(company_id) ? 0 : Integer.parseInt(company_id);

		Computer computer = new Computer.ComputerBuilder().withID(computerId).withName(newName)
				.withIntroduced(introducedDate).withDiscontinued(discontinuedDate).withManufacturerID(companyId)
				.build();

		Computer savedComputer = daoComputer.save(computer);
		return savedComputer == null ? false : true;
	}

	public void deleteComputer(String[] paramComputerID) throws Exception {
		for (String id : paramComputerID) {
			try {
				daoComputer.deleteById(Integer.parseInt(id));
			} catch (Exception e) {
				throw new Exception();
			}
		}
	}
}

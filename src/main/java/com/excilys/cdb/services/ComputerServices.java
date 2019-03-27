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

	private static JDBCComputer daoComputer = new JDBCComputer();
	private static JDBCCompany daoCompany = new JDBCCompany();

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

	public Boolean addComputer(String computerName, String introduced, String discontinued, String company_id) {
		
		Date introducedDate = null, discontinuedDate = null;
		int companyId = 0;
		companyId = new String("").equals(company_id)  ? Integer.parseInt(company_id) : 0;
		introducedDate = parseInputDate(introduced).get();
		discontinuedDate = parseInputDate(discontinued).get();
		
		Computer computer = new Computer.ComputerBuilder()
				.withName(computerName)
				.withIntroduced(introducedDate)
				.withDiscontinued(discontinuedDate)
				.withManufacturerID(companyId)
				.build();
		
		return daoComputer.addComputer(computer);
	}
}

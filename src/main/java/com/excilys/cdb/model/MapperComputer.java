package com.excilys.cdb.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperComputer {
	
	public static Computer resultSetToComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer.ComputerBuilder()
				.withID(resultSet.getInt("id"))
				.withName(resultSet.getString("name"))
				.withIntroduced(resultSet.getDate("introduced"))
				.withDiscontinued(resultSet.getDate("discontinued"))
				.withManufacturerID(resultSet.getInt("company_id"))
				.build();
	}
	
	public static DTOComputer objectToDTO(Computer computer, Company company) {
		Date introduceDate = computer.getIntroduced();
		Date discontinuedDate = computer.getDiscontinued();
		
		return new DTOComputer.DTOComputerBuilder()
				.withID(computer.getId())
				.withName(computer.getName())
				.withIntroduced(introduceDate == null ? "" : introduceDate.toString())
				.withDiscontinued(discontinuedDate == null ? "" : discontinuedDate.toString())
				.withManufacturerName(company == null ? "": company.getName())
				.build();
	}
}

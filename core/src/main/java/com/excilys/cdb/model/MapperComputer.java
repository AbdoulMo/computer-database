package com.excilys.cdb.model;

import java.sql.Date;

public class MapperComputer {
	
	public static DTOComputer objectToDTO(Computer computer, Company company) {
		Date introduceDate = computer.getIntroduced();
		Date discontinuedDate = computer.getDiscontinued();
		
		return new DTOComputer.DTOComputerBuilder()
				.withID(computer.getId())
				.withName(computer.getName())
				.withIntroduced(introduceDate == null ? "" : introduceDate.toString())
				.withDiscontinued(discontinuedDate == null ? "" : discontinuedDate.toString())
				.withManufacturer_ID(company == null ? 0: company.getId())
				.withManufacturerName(company == null ? "": company.getName())
				.build();
	}
}

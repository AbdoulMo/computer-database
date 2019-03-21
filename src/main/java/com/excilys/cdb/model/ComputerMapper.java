package com.excilys.cdb.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComputerMapper {
	
	public static Computer resultSetToComputer(ResultSet resultSet) throws SQLException {
		
		return new Computer.ComputerBuilder().withID(resultSet.getInt("id"))
				.withIntroduced(resultSet.getDate("introduced"))
				.withDiscontinued(resultSet.getDate("discontinued"))
				.withManufacturerID(resultSet.getInt("company_id"))
				.build();
	}
}

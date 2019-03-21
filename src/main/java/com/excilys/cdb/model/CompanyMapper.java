package com.excilys.cdb.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper {

	public static Company resultSetToCompany(ResultSet resultSet) throws SQLException {
		return new Company.CompanyBuilder()
				.withID(resultSet.getInt("id"))
				.withName(resultSet.getString("name"))
				.build();
	}
}

package com.excilys.cdb.exceptions;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Unable to find data in the database";
	
	public DataNotFoundException() {
		super(MESSAGE);
	}

}

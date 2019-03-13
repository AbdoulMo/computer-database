package com.excilys.training.java.modele;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Computer {

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int manufacturer_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public int getManufacturer_id() {
		return manufacturer_id;
	}

	public void setManufacturer_id(int manufacturer_id) {
		this.manufacturer_id = manufacturer_id;
	}

	public String formatReadDate(Date date) {
		SimpleDateFormat readingFormat = new SimpleDateFormat("dd-MMM-yy");
		String d = readingFormat.format(date);
		return d;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + formatReadDate(introduced) + ", discontinued="
				+ formatReadDate(discontinued) + ", manufacturer_id=" + manufacturer_id + "]";
	}
}

package com.excilys.cdb.model;

import java.sql.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Computer {

	public static class ComputerBuilder {

		private int id;
		private String name;
		private Date introduced;
		private Date discontinued;
		private int manufacturer_id;

		public ComputerBuilder withID(int id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder withIntroduced(Date inDate) {
			introduced = inDate;
			return this;
		}

		public ComputerBuilder withDiscontinued(Date diDate) {
			discontinued = diDate;
			return this;
		}

		public ComputerBuilder withManufacturerID(int id) {
			this.id = id;
			return this;
		}

		public Computer build() {
			Computer computer = new Computer();
			computer.id = this.id;
			computer.name = this.name;
			computer.introduced = this.introduced;
			computer.discontinued = this.discontinued;
			computer.manufacturer_id = this.manufacturer_id;
			return computer;
		}
	}

	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int manufacturer_id;

	private Computer() {

	}

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

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("introduced", introduced)
				.append("discontinued", discontinued).append("manufacturer_id", manufacturer_id).toString();
	}
}

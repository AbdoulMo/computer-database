package com.excilys.cdb.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DTOComputer {

	public static class DTOComputerBuilder {

		private int id;
		private String name;
		private String introduced;
		private String discontinued;
		private String manufacturer_name;

		public DTOComputerBuilder withID(int id) {
			this.id = id;
			return this;
		}

		public DTOComputerBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public DTOComputerBuilder withIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public DTOComputerBuilder withDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public DTOComputerBuilder withManufacturerName(String manufacturer_name) {
			this.manufacturer_name = manufacturer_name;
			return this;
		}

		public DTOComputer build() {
			DTOComputer dtoComputer = new DTOComputer();
			dtoComputer.id = this.id;
			dtoComputer.name = this.name;
			dtoComputer.introduced = this.introduced;
			dtoComputer.discontinued = this.discontinued;
			dtoComputer.manufacturer_name = this.manufacturer_name;
			return dtoComputer;
		}
	}

	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private String manufacturer_name;

	private DTOComputer() {

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

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.append("name", name)
				.append("introduced", introduced)
				.append("discontinued", discontinued)
				.append("manufacturer_name", manufacturer_name)
				.toString();
	}
}

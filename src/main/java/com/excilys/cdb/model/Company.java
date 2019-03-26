package com.excilys.cdb.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Company {

	public static class CompanyBuilder {

		private int id;
		private String name;

		public CompanyBuilder withID(int id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public Company build() {
			Company company = new Company();
			company.id = this.id;
			company.name = this.name;
			return company;
		}

	}

	private int id;
	private String name;

	private Company() {

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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", id)
				.append("name", name)
				.toString();
	}
}

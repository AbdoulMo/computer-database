package com.excilys.cdb.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "company")
public class Company {

	public static class CompanyBuilder {

		private Integer id;
		private String name;
		private List<Computer> computerList;

		public CompanyBuilder withID(Integer id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public CompanyBuilder withComputerList(List<Computer> computerList) {
			this.computerList = computerList;
			return this;
		}

		public Company build() {
			Company company = new Company();
			company.id = this.id;
			company.name = this.name;
			company.computerList = this.computerList;
			return company;
		}

	}

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "company")
	private List<Computer> computerList;

	private Company() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Computer> getCompanyList() {
		return computerList;
	}

	public void setCompanyList(List<Computer> computerList) {
		this.computerList = computerList;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("computerList", computerList)
				.toString();
	}
}

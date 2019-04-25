package com.excilys.cdb.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "computer")
public class Computer {

	public static class ComputerBuilder {

		private Integer id;
		private String name;
		private Date introduced;
		private Date discontinued;
		private Integer manufacturerId;
		private Company company;

		public ComputerBuilder withID(Integer id) {
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

		public ComputerBuilder withManufacturerID(Integer manufacturerId) {
			this.manufacturerId = manufacturerId;
			return this;
		}

		public ComputerBuilder withCompany(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			Computer computer = new Computer();
			computer.id = this.id;
			computer.name = this.name;
			computer.introduced = this.introduced;
			computer.discontinued = this.discontinued;
			computer.manufacturerId = this.manufacturerId;
			computer.company = this.company;
			return computer;
		}
	}

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "introduced")
	private Date introduced;
	@Column(name = "discontinued")
	private Date discontinued;
	@Column(name = "company_id")
	private Integer manufacturerId;
	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Company company;

	private Computer() {

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

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).append("introduced", introduced)
				.append("discontinued", discontinued).append("manufacturerId", manufacturerId)
				.append("company", company).toString();
	}
}

package com.excilys.cdb.vue;

import java.util.List;
import java.util.ArrayList;

import com.excilys.cdb.modele.Company;
import com.excilys.cdb.modele.Computer;

public class Paging {

	private int startIndex;
	private int endIndex;

	public Paging() {
		this.startIndex = 0;
		this.endIndex = 10;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public List<Computer> showComputerList(ArrayList<Computer> computerList) {
		return computerList.subList(startIndex, endIndex);
	}

	public List<Company> showCompanyList(ArrayList<Company> objectList) {
		return objectList.subList(startIndex, endIndex);
	}
}

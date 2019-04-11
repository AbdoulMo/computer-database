package com.excilys.cdb.vue;

import java.util.List;

import com.excilys.cdb.model.DTOComputer;

import java.util.ArrayList;

public class Paging {

	private int numberOfPage;
	private int maxDisplay;
	private int startIndex;
	private int endIndex;

	private ArrayList<DTOComputer> computerList;

	public Paging(ArrayList<DTOComputer> computerList) {
		this.computerList = computerList;
		this.maxDisplay = 10;
		this.startIndex = 0;
		this.endIndex = maxDisplay;
		this.numberOfPage = setNumberOfPage();
	}

	public int getMaxDisplay() {
		return maxDisplay;
	}

	public void setMaxDisplay(int maxDisplay) {
		this.maxDisplay = maxDisplay;
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

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public int setNumberOfPage() {
		if ((computerList.size() % maxDisplay) != 0) {
			return this.numberOfPage = (computerList.size() / maxDisplay) + 1;
		} else if (computerList.size() % maxDisplay == computerList.size()) {
			return this.numberOfPage = 1;
		}
		return this.numberOfPage = (computerList.size() / maxDisplay);
	}

	public List<DTOComputer> showComputerList(int pageIndex) {
		this.startIndex = (pageIndex * this.maxDisplay) - this.maxDisplay;
		this.endIndex += this.startIndex;
		if (this.endIndex > computerList.size()) {
			this.endIndex = computerList.size() ;
		}
		return computerList.subList(startIndex, endIndex);
	}
}

package com.tcs.bbsr.ats.domain;

import java.util.List;

public class Cube {
	private int id;
	private String cubeId;
	private int rowId;
	private int colId;
	private int positionInCube;
	private boolean visible;
	private List<String> employeeNames;
	private String specialTxt;
	private boolean special;
	
	
	
	
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCubeId() {
		return cubeId;
	}
	public void setCubeId(String cubeId) {
		this.cubeId = cubeId;
	}
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public int getPositionInCube() {
		return positionInCube;
	}
	public void setPositionInCube(int positionInCube) {
		this.positionInCube = positionInCube;
	}
	public String getSpecialTxt() {
		return specialTxt;
	}
	public void setSpecialTxt(String specialTxt) {
		this.specialTxt = specialTxt;
	}
	public List<String> getEmployeeNames() {
		return employeeNames;
	}
	public void setEmployeeNames(List<String> employeeNames) {
		this.employeeNames = employeeNames;
	}
	public boolean isSpecial() {
		return special;
	}
	public void setSpecial(boolean special) {
		this.special = special;
	}
	
}

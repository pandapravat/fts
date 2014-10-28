package com.pravatpanda.apps.ats.domain.ui;

import java.util.ArrayList;
import java.util.List;

import com.pravatpanda.apps.ats.domain.Cube;
import com.pravatpanda.apps.ats.domain.CubeLayout;
import com.pravatpanda.apps.ats.domain.FloorLayout;
import com.pravatpanda.apps.ats.domain.RowLayout;

public class NewOdcVO {
	
	
	String odcName;
	String establishmentName;
	String city;
	String odcId;
	int floorNumber;
	String wing;
	int rows;
	int columns;
	FloorLayout floorLayout;
	

	public FloorLayout getFloorLayout() {
		return floorLayout;
	}
	public void setFloorLayout(FloorLayout floorLayout) {
		this.floorLayout = floorLayout;
	}
	public String getOdcName() {
		return odcName;
	}
	public void setOdcName(String odcName) {
		this.odcName = odcName;
	}
	public String getEstablishmentName() {
		return establishmentName;
	}
	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	public String getOdcId() {
		return odcId;
	}
	public void setOdcId(String odcId) {
		this.odcId = odcId;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void initialise() {
		floorLayout = new FloorLayout();
		List<RowLayout> rowLayouts = new ArrayList<RowLayout>();
		for(int count = 0; count < rows; count ++){
			RowLayout rowLayout = new RowLayout();
			List<CubeLayout> rowData = new ArrayList<CubeLayout>();
			for(int colCount =0; colCount < columns; colCount++){
				
				CubeLayout cubeLayout = new CubeLayout();
				List<Cube> cubes = new ArrayList<Cube>();
				
				for(int cubeCount=0; cubeCount < 4; cubeCount++) {
					Cube cube = new Cube();
					cube.setCubeId("");
					cubes.add(cube);
				}
				cubeLayout.setCubes(cubes);
				rowData.add(cubeLayout);
			}
			rowLayout.setRowData(rowData);
			
			rowLayouts.add(rowLayout);
		}
		floorLayout.setRowLayout(rowLayouts);
	}
	
	public String getWing() {
		return wing;
	}
	public void setWing(String wing) {
		this.wing = wing;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
}

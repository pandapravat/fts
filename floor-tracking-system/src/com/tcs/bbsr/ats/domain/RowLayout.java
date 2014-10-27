package com.tcs.bbsr.ats.domain;

import java.util.ArrayList;
import java.util.List;


public class RowLayout {
	private List<CubeLayout> rowData;

	public List<CubeLayout> getRowData() {
		if(null == rowData) {
			rowData = new ArrayList<CubeLayout>();
		}
		return rowData;
	}

	public void setRowData(List<CubeLayout> rowData) {
		this.rowData = rowData;
	}
}

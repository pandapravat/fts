package com.tcs.bbsr.ats.domain;

import java.util.ArrayList;
import java.util.List;

public class FloorLayout {
	private List<RowLayout> rowLayout;
	private String floorName = "Kalinga Park - ODC6";
	public List<RowLayout> getRowLayout() {
		if(null == rowLayout) {
			rowLayout = new ArrayList<RowLayout>();
		}
		return rowLayout;
	}

	public void setRowLayout(List<RowLayout> rowLayout) {
		this.rowLayout = rowLayout;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
}

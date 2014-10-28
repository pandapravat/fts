package com.tcs.bbsr.ats.domain;

import java.util.ArrayList;
import java.util.List;

public class CubeLayout {
	List<Cube> cubes;

	public List<Cube> getCubes() {
		if(null == cubes) {
			cubes = new ArrayList<Cube>();
		}
		return cubes;
	}

	public void setCubes(List<Cube> cubes) {
		this.cubes = cubes;
	} 
}

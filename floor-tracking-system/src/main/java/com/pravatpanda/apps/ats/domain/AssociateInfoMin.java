package com.pravatpanda.apps.ats.domain;

import java.util.HashMap;
import java.util.Map;


public class AssociateInfoMin {
	
	int 		slNo;
	int 		employeeId;
	String 		employeeName = "";
	String 		designation = "";
	String 		emailId = "";
	String 		seatNo = "";
	String 		assetId1 = "";
	int 		extNo;
	String 		projectName = "";
	String 		shift = "";
	
	Map<String, String> shiftMap = new HashMap<String, String>();
	public AssociateInfoMin() {
		shiftMap.put("A", "Morning");
		shiftMap.put("B", "Afternoon");
		shiftMap.put("C", "Night");
		shiftMap.put("D", "General");
	}
	public AssociateInfoMin(int employeeId, String employeeName,
			String designation, String emailId, String seatNo, String assetId1, 
			int extNo, String projectName, String shift){

		this();
		this.seatNo = seatNo;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.emailId = emailId;
		this.designation = designation;
		this.extNo = extNo;
		this.assetId1 = assetId1;
		this.projectName = projectName;
		this.shift = shift;
	}
	

	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		if(null == employeeName) employeeName = "";
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignation() {
		if(null == designation) designation = "";
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmailId() {
		if(null == emailId) emailId = "";
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSeatNo() {
		if(null == seatNo) seatNo = "";
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public String getAssetId1() {
		if(null == assetId1) assetId1 = "";
		return assetId1;
	}
	public void setAssetId1(String assetId1) {
		this.assetId1 = assetId1;
	}
	public int getExtNo() {
		return extNo;
	}
	public void setExtNo(int extNo) {
		this.extNo = extNo;
	}
	public String getProjectName() {
		if(null == projectName) projectName = "";
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getShift() {
		if(null == shift) shift = "";
		
		return shift;
	}
	
	public String getFriendlyShift() {
		String shift2 = getShift();
		if(!"".equals(shift2)) {
			return shiftMap.get(shift2);
		} 
		return "";
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public Map<String, String> getShiftMap() {
		return shiftMap;
	}
	public void setShiftMap(Map<String, String> shiftMap) {
		this.shiftMap = shiftMap;
	}
}

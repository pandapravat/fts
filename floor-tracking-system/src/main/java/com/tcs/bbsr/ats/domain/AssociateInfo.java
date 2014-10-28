package com.tcs.bbsr.ats.domain;

public class AssociateInfo extends AssociateInfoMin {

	String building = "";
	String floor = "";
	String wing = "";
	String occupiedFromDate = "";
	String allocationTillDate = "";
	String projectId = "";
	String clientGroup = "";
	String subUnitSubISU = "";
	String unitISU = "";
	String unallocated = ""; 
	int cardNo;
	String baseBranch = "";
	String manager = "";
	int wonSwonNo;
	String mobileNo = "";
	
	public AssociateInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getBuilding() {
		if(null == building) building = "";
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFloor() {
		if(null == floor) floor = "";
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getWing() {
		if(null == wing) wing = "";
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

	public String getOccupiedFromDate() {
		if(null == occupiedFromDate) occupiedFromDate = "";
		return occupiedFromDate;
	}

	public void setOccupiedFromDate(String occupiedFromDate) {
		this.occupiedFromDate = occupiedFromDate;
	}

	public String getAllocationTillDate() {
		if(null == allocationTillDate) allocationTillDate = "";
		return allocationTillDate;
	}

	public void setAllocationTillDate(String allocationTillDate) {
		this.allocationTillDate = allocationTillDate;
	}

	public String getProjectId() {
		if(null == projectId) projectId = "";
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getClientGroup() {
		if(null == clientGroup) clientGroup = "";
		return clientGroup;
	}

	public void setClientGroup(String clientGroup) {
		this.clientGroup = clientGroup;
	}

	public String getSubUnitSubISU() {
		if(null == subUnitSubISU) subUnitSubISU = "";
		return subUnitSubISU;
	}

	public void setSubUnitSubISU(String subUnitSubISU) {
		this.subUnitSubISU = subUnitSubISU;
	}

	public String getUnitISU() {
		if(null == unitISU) unitISU = "";
		return unitISU;
	}

	public void setUnitISU(String unitISU) {
		this.unitISU = unitISU;
	}

	public String getUnallocated() {
		if(null == unallocated) unallocated = "";
		return unallocated;
	}

	public void setUnallocated(String unallocated) {
		this.unallocated = unallocated;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public String getBaseBranch() {
		if(null == baseBranch) baseBranch = "";
		return baseBranch;
	}

	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}

	public String getManager() {
		if(null == manager) manager = "";
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getWonSwonNo() {
		return wonSwonNo;
	}

	public void setWonSwonNo(int wonSwonNo) {
		this.wonSwonNo = wonSwonNo;
	}

	public String getMobileNo() {
		if(null == mobileNo) mobileNo = "";
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}

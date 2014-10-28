package com.pravatpanda.apps.ats.dao;

import java.util.List;
import java.util.Map;

import com.pravatpanda.apps.ats.domain.AssociateInfo;
import com.pravatpanda.apps.ats.domain.AssociateInfoMin;
import com.pravatpanda.apps.ats.domain.Cube;
import com.pravatpanda.apps.ats.domain.ODC;
import com.pravatpanda.apps.ats.domain.ui.NewOdcVO;


public interface AtsDao {

	int addAssociate(String building, String floor, String wing, String seatNo,
			int employeeId, String employeeName, String emailId, int cardNo, String designation,
			String mobileNo, int extn, String occupiedFromDate, String allocationTillDate, 
			int wonSwon, String projectId, String clientGroup, String subISU, String unitISU, String assetId1, 
			String projectName, String unallocated, String baseBranch, String shift, String manager, String floorId);

	/**
	 * Updates an associates details by seatNo.
	 */
	int updateAssociate(int slNo, String seatNo, int employeeId, String employeeName, String emailId, int cardNo,
			String designation, String mobileNo, int extn, String occupiedFromDate, String allocationTillDate, 
			int wonSwon, String projectId, String clientGroup, String subISU, String unitISU, String assetId1, 
			String projectName, String unallocated, String baseBranch, String shift, 
			String manager);

	
	List<String> getEmployeesInCube(String cubeId) ;
	
	List<AssociateInfoMin> compactSearch(
			String seatNo, String employeeName, 
			int employeeId, String projectName, 
			String assetId1, String shift, String floorId);
	
	List<AssociateInfoMin> fullSearch(
			String seatNo, String employeeName, 
			int employeeId, String projectName, 
			String assetId1, String shift, String floorId);
	
	public List<Cube> getFloorDetails(String floorId);
	
	Map<String, List<String>> getEmployeeNames(String floorId);
	


	List<ODC> getAvailableFloors();

	ODC getODC(String floorId);

	int removeAssociate(int slNo);

	AssociateInfo getAssociate(int recordId, String floorId);
	
	void addNewFloor(String odcId, String odcName, String establishmentName,
			String city, int i, String string);

	int getMaxFloorId();
	void addFloorRecord(int maxFloorId, String cubeId, int rowNum, int colNum,
			int posInCube, String visible, String specialText, String odcId);

	void removeFloor(String odcId);
	int retrieveHitCount(String floorId);

	NewOdcVO getFloorById(String floorId);
}

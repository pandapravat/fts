package com.tcs.bbsr.ats.bi;

import java.util.List;
import java.util.Map;

import com.tcs.bbsr.ats.domain.AssociateInfo;
import com.tcs.bbsr.ats.domain.AssociateInfoMin;
import com.tcs.bbsr.ats.domain.FloorLayout;
import com.tcs.bbsr.ats.domain.ui.NewOdcVO;

public interface AtsBiService {
	FloorLayout getFloorPlan(String floorId);
	
	List<String> getEmployeesInCube(String cubeId);
	
	List<AssociateInfoMin> searchEmployee(String seatNo, String employeeName, 
										int employeeId, String projectName, 
										String assetId1, String shift, 
										boolean fullDetails, String floorId);
	Map<String, String> getAvailableFloors();
	boolean addAssociate(AssociateInfo info, String floorId);
	boolean removeAssociate(int slNo);
	AssociateInfo getAssociate(int recordId, String floorId);
	boolean updateAssociate(AssociateInfo info) ;
	void configureFloor(NewOdcVO odcVO);
	void removeFloor(String odcId);
	public int retrieveHitCount(String floorId);
	NewOdcVO getFloorById(String floorId);
}

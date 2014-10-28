package com.pravatpanda.apps.ats.bi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.pravatpanda.apps.ats.dao.AtsDao;
import com.pravatpanda.apps.ats.domain.AssociateInfo;
import com.pravatpanda.apps.ats.domain.AssociateInfoMin;
import com.pravatpanda.apps.ats.domain.Cube;
import com.pravatpanda.apps.ats.domain.CubeLayout;
import com.pravatpanda.apps.ats.domain.FloorLayout;
import com.pravatpanda.apps.ats.domain.ODC;
import com.pravatpanda.apps.ats.domain.RowLayout;
import com.pravatpanda.apps.ats.domain.ui.NewOdcVO;

public class AtsBiServiceImpl implements AtsBiService {

	private AtsDao dao;

	public FloorLayout getFloorPlan(String floorId) {

		//List<Cube> floorPlan = dao.getFloorPlan(floorId);
		List<Cube> floorPlan = dao.getFloorDetails(floorId);
		Map<String, List<String>> cubeIdToCountMap = dao.getEmployeeNames(floorId);
		
		ODC odc = dao.getODC(floorId);
		

		FloorLayout floorLayout = new FloorLayout();
		floorLayout.setFloorName(odc.getEstablishmentName() + " - " + odc.getOdcName());
		List<RowLayout> rowLayouts = floorLayout.getRowLayout();

		for (Cube cube : floorPlan) {

			int rowId = cube.getRowId();
			if(rowId > rowLayouts.size()) {
				rowLayouts.add(new RowLayout());
			} 

			String cubeId = cube.getCubeId();
			List<String> employees = null;
			if(null != cubeIdToCountMap.get(cubeId)) {
				employees = cubeIdToCountMap.get(cubeId);
				
			}
			cube.setEmployeeNames(employees);
			
			RowLayout aRowLayout = rowLayouts.get(rowId - 1);
			List<CubeLayout> rowData = aRowLayout.getRowData();

			int colId = cube.getColId();
			if(colId > rowData.size()) {
				rowData.add(new CubeLayout());
			} 
			CubeLayout cubeLayout = rowData.get(colId -1); // colId is 1 based
			List<Cube> cubes = cubeLayout.getCubes();

			int positionInCube = cube.getPositionInCube();
			if(positionInCube > cubes.size()) {
				cubes.add(cube);
			} 
		}
		int rowCount = 1;
		for (RowLayout rowLayout : rowLayouts) {
			List<CubeLayout> rowData = rowLayout.getRowData();
			int colCount = 1;
			for (CubeLayout cubeLayout : rowData) {
				
				List<Cube> cubes = cubeLayout.getCubes();
				Assert.notEmpty(cubes);
				Assert.isTrue(4 == cubes.size(), "Cubicle does not contain four cubes for row " + rowCount + " and column " + colCount);
				colCount++;
				
			}
			rowCount ++;
		}
		
		return floorLayout;
	}
	
	public List<String> getEmployeesInCube(String cubeId) {
		List<String> employeesInCube = null;
		if(!StringUtils.isEmpty(cubeId)) {
			employeesInCube = dao.getEmployeesInCube(cubeId);
		}
		return employeesInCube;
	}
	public boolean addAssociate(AssociateInfo info, String floorId){

		dao.addAssociate(info.getBuilding(), info.getFloor(), info.getWing(), info.getSeatNo(), info.getEmployeeId(), 
				info.getEmployeeName(), info.getEmailId(), info.getCardNo(), info.getDesignation(), info.getMobileNo(),
				info.getExtNo(), info.getOccupiedFromDate(), info.getAllocationTillDate(), info.getWonSwonNo(), 
				info.getProjectId(), info.getClientGroup(), info.getSubUnitSubISU(), info.getUnitISU(), info.getAssetId1(),
				info.getProjectName(), info.getUnallocated(), info.getBaseBranch(), info.getShift(), info.getManager(), floorId);

		return true;
	}
	public List<AssociateInfoMin> searchEmployee(String seatNo, String employeeName, int employeeId, 
			String projectName, String assetId1, String shift, boolean detailedView, String floorId) {
		if(detailedView) {
			return dao.fullSearch(seatNo, employeeName, employeeId, projectName, assetId1, shift, floorId);
		} else {
			return dao.compactSearch(seatNo, employeeName, employeeId, projectName, assetId1, shift, floorId);
		}
	}
	public Map<String, String> getAvailableFloors() {
		List<ODC> availableFloors = dao.getAvailableFloors();
		HashMap<String,String> map = new HashMap<String, String>();
		for (ODC odc : availableFloors) {
			String odcId = odc.getOdcId();
			String cityName = odc.getCityName();
			String establishmentName = odc.getEstablishmentName();
			String odcName = odc.getOdcName();
			map.put(odcId, cityName + "_" + establishmentName + "_" + odcName);
		}
		
		return map;
	}
	
	
	public boolean removeAssociate(int slNo) {
		
		int numbersRemoved = dao.removeAssociate(slNo);
		boolean removed = numbersRemoved > 0 ? true : false;
		if(!removed) 
			throw new AtsException("No employees were removed", "System returned " + numbersRemoved + " results");
		return removed;
	}
	public AssociateInfo getAssociate(int recordId, String floorId) {
		return dao.getAssociate(recordId, floorId);
	}
	
	public boolean updateAssociate(AssociateInfo info) {
		dao.updateAssociate(info.getSlNo(), info.getSeatNo(), info.getEmployeeId(), 
				info.getEmployeeName(), info.getEmailId(), info.getCardNo(), 
				info.getDesignation(), info.getMobileNo(), info.getExtNo(), 
				info.getOccupiedFromDate(), info.getAllocationTillDate(), 
				info.getWonSwonNo(), info.getProjectId(), info.getClientGroup(), 
				info.getSubUnitSubISU(), info.getUnitISU(), info.getAssetId1(), 
				info.getProjectName(), info.getUnallocated(), info.getBaseBranch(), 
				info.getShift(), info.getManager());
		return true;
	}
	
	@Transactional
	public void configureFloor(NewOdcVO odcVO) {
		dao.addNewFloor(odcVO.getOdcId(), odcVO.getOdcName(), 
				odcVO.getEstablishmentName(), odcVO.getCity(), odcVO.getFloorNumber(), odcVO.getWing());
		
		int maxFloorId = dao.getMaxFloorId();
		FloorLayout floorLayout = odcVO.getFloorLayout();
		List<RowLayout> rowLayout = floorLayout.getRowLayout();
		maxFloorId ++;
		int rowNum = 1;
		int emptyCubeCount = 1;
		for (RowLayout rowLayout2 : rowLayout) {
		
			List<CubeLayout> rowData = rowLayout2.getRowData();
			int colNum = 1;
			for (CubeLayout cubeLayout : rowData) {
				
				List<Cube> cubes = cubeLayout.getCubes();
				int posInCube = 1;
				for (Cube cube : cubes) {
					
					String cubeId = cube.getCubeId();
					String visible = "Y";
					String specialText = "";
					if(StringUtils.isEmpty(cubeId)) {
						cubeId = "NoCube" + emptyCubeCount;
						emptyCubeCount ++;
						visible = "N";
					}
					if(cube.isSpecial()) {
						specialText = "Special Cube";
					}
 					dao.addFloorRecord(maxFloorId, cubeId, rowNum, colNum, posInCube, visible, specialText, odcVO.getOdcId());
					maxFloorId ++;
					posInCube ++;
				}
				colNum ++;
			}
			rowNum ++;
		}
		
		
	}
	
	public int retrieveHitCount(String floorId){

		int hitCount = dao.retrieveHitCount(floorId);
		
		return hitCount;
	}
	@Transactional
	public void removeFloor(String odcId) {
		dao.removeFloor(odcId);
		
	}
	public void setDao(AtsDao dao) {
		this.dao = dao;
	}

	public NewOdcVO getFloorById(String floorId) {
		return dao.getFloorById( floorId);
	}
}

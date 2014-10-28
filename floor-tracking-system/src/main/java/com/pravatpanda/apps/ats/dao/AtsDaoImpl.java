package com.pravatpanda.apps.ats.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.util.CollectionUtils;

import com.pravatpanda.apps.ats.bi.AtsException;
import com.pravatpanda.apps.ats.domain.AssociateInfo;
import com.pravatpanda.apps.ats.domain.AssociateInfoMin;
import com.pravatpanda.apps.ats.domain.Cube;
import com.pravatpanda.apps.ats.domain.ODC;
import com.pravatpanda.apps.ats.domain.ui.NewOdcVO;

public class AtsDaoImpl implements AtsDao{

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	private int getId() {

		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("NOTHING", 0);

		String nextId = "SELECT SL_NO FROM Associate " +
		"WHERE SL_NO = (SELECT MAX(SL_NO)  FROM Associate)";

		int queryForInt = namedJdbcTemplate.queryForInt(nextId, values);

		return queryForInt;
	}

	

	

	public int addAssociate(String building, String floor,
			String wing, String seatNo, int employeeId, String employeeName,
			String emailId, int cardNo, String designation, String mobileNo,
			int extn, String occupiedFromDate, String allocationTillDate,
			int wonSwon, String projectId, String clientGroup, String subISU,
			String unitISU, String assetId1, String projectName,
			String unallocated, String baseBranch,
			String shift, String manager, String floorId) {
		int addValue = 2;
		int id = getId()  + 1;
		String insertQuery = "INSERT INTO Associate(SL_No, " +
		" Seat_no, Employee_Id, Employee_Name, Email_Id, Card_No, Designation, Mobile_No, Extn, " +
		" Occupied_From_Date, Allocation_Till_Date, WON_SWON, Project_Id, Client_Group, " +
		" Sub_Unit_Sub_ISU, Unit_ISU, Asset_Id1, Project_Name, " +
		" Unallocated, BASE_BRANCH, SHIFT, MANAGER, FLOOR_ID) " +
		" VALUES (:SL_No, :Seat_no, :Employee_Id, :Employee_Name, " +
		":Email_Id, :Card_No, :Designation, :Mobile_No, :Extn, :Occupied_From_Date, :Allocation_Till_Date, " +
		":WON_SWON, :Project_ID, :Client_Group, :Sub_Unit_Sub_ISU, :Unit_ISU, :Asset_Id1, :Project_Name, " +
		":Unallocated, :BASE_BRANCH, :SHIFT, :MANAGER, :FLOOR_ID)";

		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("SL_No", id);
		values.put("Seat_no", seatNo);
		values.put("Employee_Id", employeeId);
		values.put("Employee_Name", employeeName);
		values.put("Email_Id", emailId);
		values.put("Card_No", cardNo);
		values.put("Designation", designation);
		values.put("Mobile_No", mobileNo);
		values.put("Extn", extn);
		values.put("Occupied_From_Date", occupiedFromDate);
		values.put("Allocation_Till_Date", allocationTillDate);
		values.put("WON_SWON", wonSwon);
		values.put("Project_ID", projectId);
		values.put("Client_Group", clientGroup);
		values.put("Sub_Unit_Sub_ISU", subISU);
		values.put("Unit_ISU", unitISU);
		values.put("Asset_Id1", assetId1);
		values.put("Project_Name", projectName);
		values.put("Unallocated", unallocated);
		values.put("BASE_BRANCH", baseBranch);
		values.put("SHIFT", shift);
		values.put("MANAGER", manager);
		values.put("FLOOR_ID", floorId);

		namedJdbcTemplate.update(insertQuery, values);

		return addValue;
	}



	public List<Cube> getFloorDetails(String floorId) {
		String sql = 
			"select id, cube_id, row_num, column_num, cube_num, visible, specialtxt " +
			"from floor_plan " +
			"where floor_id = :floorid " +
			"order by 	row_num asc, " +
			"			column_num asc, " +
			"			cube_num asc ";
			//"having b.floor_id = a.floor_id " +
			//"		and b.floor_id = 'KP_ODC6' ";
			
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("floorid", floorId);
		
		List<Cube> cubes = namedJdbcTemplate.query(sql, paramMap, new RowMapper<Cube>() {

			public Cube mapRow(ResultSet rs, int arg1) throws SQLException {
				Cube cube = new Cube();
				int id = rs.getInt("id");
				int rowNum = rs.getInt("row_num");;
				String cubeId = rs.getString("cube_id");
				int columnNo = rs.getInt("column_num");
				int cubeNo = rs.getInt("cube_num");
				String isVisible = rs.getString("visible");
				String specialTxt = rs.getString("specialtxt");
				
				cube.setId(id);
				cube.setCubeId(cubeId);
				cube.setRowId(rowNum);
				cube.setColId(columnNo);
				cube.setPositionInCube(cubeNo);
				cube.setSpecialTxt(specialTxt);
				boolean isVisibleB = false;
				if("Y".equals(isVisible)) isVisibleB = true; 
				cube.setVisible(isVisibleB);
				return cube;
			}
		});
		
		return cubes;
	}
	
	public Map<String, List<String>> getEmployeeNames(String floorId) {
		String sql = 
			"select seat_no, employee_name " +
			"from associate " ;
			//"where floor_id = :floorid " +
			//"group by 	seat_no  " +
			//"order by seat_no asc" ;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("floorid", floorId);
		Map<String, List<String>> cubeToCount = namedJdbcTemplate.query(sql, paramMap, new ResultSetExtractor<Map<String, List<String>>>() {

			public Map<String, List<String>> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<String, List<String>> hashMap = new HashMap<String, List<String>>();
				while(rs.next()) {
					String string = rs.getString("seat_no");
					String employeeName = rs.getString("employee_name");
					//System.out.println(string + "\t>>\t" +int1);
					List<String> list = hashMap.get(string);
					if(CollectionUtils.isEmpty(list)) {
						list = new ArrayList<String>();
						hashMap.put(string, list);
					}
					list.add(employeeName);
				}
				
				return hashMap;
			}
		});
		
		return cubeToCount;
	}
	
	
	
	
	
	public List<String> getEmployeesInCube(String cubeId) {
		String sql = 
			"select employee_name from associate where seat_no = :seat_no";
		Map<String,String> map = new HashMap<String, String>();
		map.put("seat_no", cubeId);
		List<String> employees =  namedJdbcTemplate.query(sql, map, new RowMapper<String>() {

			public String mapRow(ResultSet arg0, int arg1) throws SQLException {
				return arg0.getString("employee_name");
			}
			
		});
		return employees;
	}
	
	String searchPre = 
			"SELECT a.sl_no, a.Seat_no, a.Employee_Id, a.Employee_Name, " +
			"a.Email_Id, a.Card_No, a.Designation,a.Mobile_No, a.WON_SWON, a.Extn, a.Asset_Id1, " +
			"a.Project_Name, a.SHIFT, b.office_name as Building, b.floor, b.wing, a.Project_ID, a.Occupied_From_Date, " +
			"a.Allocation_Till_Date, a.Client_Group, a.Sub_Unit_Sub_ISU, a.Unit_ISU, " +
			"a.Unallocated, a.BASE_BRANCH, a.MANAGER  FROM Associate a, floor_list b WHERE ";
	
	class AssociateInfoRowMapper implements ParameterizedRowMapper<AssociateInfoMin>{

		boolean fullDetails = false;
		
		public AssociateInfoMin mapRow(ResultSet rs, int rowNum) throws SQLException {

			int slNo = rs.getInt("sl_no");
			String seatNo = rs.getString("Seat_no");
			int employeeId = rs.getInt("Employee_Id");
			String employeeName = rs.getString("Employee_Name");
			String emailId = rs.getString("Email_Id");
			int cardNo = rs.getInt("Card_No");
			String designation = rs.getString("Designation");
			String mobileNo = rs.getString("Mobile_No");
			int wonSwon = rs.getInt("WON_SWON");
			int extn = rs.getInt("Extn");
			String assetId1 = rs.getString("Asset_Id1");
			String projectName = rs.getString("Project_Name");
			String shift = rs.getString("SHIFT");

			AssociateInfoMin associateInfoMin = null;
			if(fullDetails) {
				AssociateInfo associateInfo = new AssociateInfo();
				associateInfo.setSlNo(slNo);
				associateInfo.setSeatNo(seatNo);
				associateInfo.setEmployeeId(employeeId);
				associateInfo.setEmployeeName(employeeName);
				associateInfo.setEmailId(emailId);
				associateInfo.setCardNo(cardNo);
				associateInfo.setDesignation(designation);
				associateInfo.setMobileNo(mobileNo);
				associateInfo.setWonSwonNo(wonSwon);
				associateInfo.setExtNo(extn);
				associateInfo.setAssetId1(assetId1);
				associateInfo.setProjectName(projectName);
				associateInfo.setShift(shift);
				associateInfo.setCardNo(cardNo);
				associateInfo.setMobileNo(mobileNo);
				associateInfo.setWonSwonNo(wonSwon);
				associateInfo.setBuilding(rs.getString("Building"));
				associateInfo.setFloor(rs.getString("floor"));
				associateInfo.setWing(rs.getString("wing"));
				associateInfo.setProjectId(rs.getString("Project_ID"));
				associateInfo.setOccupiedFromDate(rs.getString("Occupied_From_Date"));
				associateInfo.setAllocationTillDate(rs.getString("Allocation_Till_Date"));
				associateInfo.setClientGroup(rs.getString("Client_Group"));
				associateInfo.setSubUnitSubISU(rs.getString("Sub_Unit_Sub_ISU"));
				associateInfo.setUnitISU(rs.getString("Unit_ISU"));
				associateInfo.setUnallocated(rs.getString("Unallocated"));
				associateInfo.setBaseBranch(rs.getString("BASE_BRANCH"));
				associateInfo.setManager(rs.getString("MANAGER"));
				associateInfoMin = associateInfo;
			} else {
				associateInfoMin = new AssociateInfoMin(employeeId, employeeName,
						designation, emailId, seatNo, assetId1, 
						extn, projectName, shift);
				associateInfoMin.setSlNo(slNo);
			}
			return associateInfoMin;
		}
		public boolean isFullDetails() {
			return fullDetails;
		}
		public void setFullDetails(boolean fullDetails) {
			this.fullDetails = fullDetails;
		}
	}


	public int retrieveHitCount(String floorId){
		
		
		String sqlForHitCount = "SELECT Hit_Count from floor_list WHERE odc_id =:FLOOR_ID";

		final Map<String, String> idHitCountMap = new HashMap<String, String>();
		idHitCountMap.put("FLOOR_ID", floorId);
		int hitCount = namedJdbcTemplate.queryForInt(sqlForHitCount, idHitCountMap);
		
		String sql = "UPDATE floor_list set Hit_Count = :Hit_Count where odc_id =:FLOOR_ID ";
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("Hit_Count", ++hitCount);
		values.put("FLOOR_ID", floorId);
		
		namedJdbcTemplate.update(sql, values);
		
		return hitCount;
	}
	
	

	public List<AssociateInfoMin> compactSearch(String seatNo, String employeeName, 
			int employeeId, String projectName, String assetId1, String shift, String floorId) {

		List<AssociateInfoMin> associateInfo; 
		final Map<String, Object> values = new HashMap<String, Object>();

		StringBuffer searchQuery = new StringBuffer();
		searchQuery.append(searchPre);

		appendSearchString(searchQuery, values, seatNo, employeeName, employeeId, projectName, assetId1, shift, floorId);

		associateInfo = (List<AssociateInfoMin>)namedJdbcTemplate.query(searchQuery.toString(), 
				values, new AssociateInfoRowMapper());

		return associateInfo;
	}
	
	public List<AssociateInfoMin> fullSearch(String seatNo, String employeeName, 
			int employeeId, String projectName, String assetId1, String shift, String floorId) {


		List<AssociateInfoMin> associateInfo; 
		final Map<String, Object> values = new HashMap<String, Object>();

		StringBuffer searchQuery = new StringBuffer();
		searchQuery.append(searchPre);

		appendSearchString(searchQuery, values, seatNo, employeeName, employeeId, projectName, assetId1, shift, floorId);
		AssociateInfoRowMapper rowMapper = new AssociateInfoRowMapper();
		rowMapper.setFullDetails(true);
		associateInfo = (List<AssociateInfoMin>)namedJdbcTemplate.query(searchQuery.toString(), 
				values, rowMapper);

		return associateInfo;
	}
	
	private void appendSearchString(
			StringBuffer searchQueryBase, final Map<String, Object> values, String seatNo, 
			String employeeName, int employeeId, String projectName, String assetId1, 
			String shift, String floorId) {
		
		StringBuffer searchQuery = new StringBuffer();
		searchQuery.append(" and ucase(a.floor_id) = ucase(b.odc_id)");
		if(null != floorId){
			searchQuery.append(" and  ucase(floor_id) = :floorid ");
			values.put("floorid", floorId.toUpperCase());
		}
		if(null != seatNo){
			searchQuery.append(" and  ucase(Seat_no) = :Seat_no ");
			values.put("Seat_no", seatNo.toUpperCase());
		}
		if(null != employeeName){
			searchQuery.append(" and  ucase(Employee_Name) like :Employee_Name ");
			values.put("Employee_Name", "%" + employeeName + "%");
		}
		if(0 != employeeId){
			searchQuery.append(" and  Employee_Id = :Employee_Id ");
			values.put("Employee_Id", employeeId);
		}
		if(null != projectName){
			searchQuery.append(" and  ucase(Project_Name) like :Project_Name ");
			values.put("Project_Name", "%" + projectName + "%");
		}
		if(null != assetId1){
			searchQuery.append(" and  ucase(Asset_Id1) like :Asset_Id1");
			values.put("Asset_Id1", "%" + assetId1 + "%");
		}

		if(null != shift){
			
			searchQuery.append(" and ucase(shift) in (" + getShifts(shift) + ")");
			//values.put("shift", );
		}
		
		searchQueryBase.append(searchQuery.substring(searchQuery.indexOf("and") + 3));
	}
	
	private String getShifts(String shift) {
		String shifts = "";
		if(null != shift) {
			char[] charArray = shift.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				char c = charArray[i];
				shifts += "'" + c +"',"; 
			}
		}
		if(!"".equals(shifts)) {
			shifts = shifts.substring(0, shifts.length() - 1);
		}
		
		return shifts;
	}


	public static void main(String[] args) {
		System.out.println(new AtsDaoImpl().getShifts("A"));
	}



	public List<ODC> getAvailableFloors() {
		
		String sql = 
			"select odc_id, odc_name, office_name, city from floor_list";
		Map<String,String> map = new HashMap<String, String>();
		List<ODC> employees =  namedJdbcTemplate.query(sql, map, new RowMapper<ODC>() {

			public ODC mapRow(ResultSet rs, int arg1) throws SQLException {
				ODC odc = new ODC();
				odc.setCityName(rs.getString("city"));
				odc.setEstablishmentName(rs.getString("office_name"));
				odc.setOdcName(rs.getString("odc_name"));
				odc.setOdcId(rs.getString("odc_id"));
				
				return odc;
			}
			
		});
		return employees;
	}
	
	
	
	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}





	public ODC getODC(String floorId) {
		String sql = 
			"select odc_id, odc_name, office_name, city from floor_list " +
			"where odc_id = :floorid";
		Map<String,String> map = new HashMap<String, String>();
		map.put("floorid", floorId);
		ODC odc = namedJdbcTemplate.queryForObject(sql, map, new RowMapper<ODC>() {

			public ODC mapRow(ResultSet rs, int arg1) throws SQLException {
				ODC odc = new ODC();
				odc.setCityName(rs.getString("city"));
				odc.setEstablishmentName(rs.getString("office_name"));
				odc.setOdcName(rs.getString("odc_name"));
				odc.setOdcId(rs.getString("odc_id"));
				
				return odc;
			}
		});
		
		return odc;
	}


	public int removeAssociate(int slNo) {
		
		String sql =
			"delete from associate where sl_no = :serailNo";
		Map<String, Integer> deleteMap = new HashMap<String, Integer>();
		deleteMap.put("serailNo", slNo);
		int numbers = namedJdbcTemplate.update(sql, deleteMap);
		
		return numbers;
	}




	public AssociateInfo getAssociate(int recordId, String floorId) {
		
		String sql = searchPre + " a.sl_no=:serailNo and ucase(a.floor_id) = :floorId and ucase(a.floor_id) = ucase(b.odc_id)" ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serailNo", recordId);
		map.put("floorId", floorId.toUpperCase());
		
		AssociateInfoRowMapper mapper = new AssociateInfoRowMapper();
		mapper.setFullDetails(true);
		AssociateInfoMin associateInfoMin = namedJdbcTemplate.queryForObject(sql, map, mapper);
		
		return (AssociateInfo)associateInfoMin;
	}

	public int updateAssociate(int slNo, String seatNo, int employeeId,
			String employeeName, String emailId, int cardNo,
			String designation, String mobileNo, int extn,
			String occupiedFromDate, String allocationTillDate, int wonSwon,
			String projectId, String clientGroup, String subISU,
			String unitISU, String assetId1, String projectName,
			String unallocated, String baseBranch, String shift, String manager) {


		String updateQuery = 
			"UPDATE Associate " +
			"SET 	Seat_no = :Seat_no, Employee_Id = :Employee_Id, Employee_Name = :Employee_Name, Email_Id = :Email_Id ," +
			" 		Card_No = :Card_No, Designation = :Designation, Mobile_No = :Mobile_No, " +
			" 		Extn = :Extn, Occupied_From_Date = :Occupied_From_Date, Allocation_Till_Date = :Allocation_Till_Date, WON_SWON = :WON_SWON, " +
			" 		Project_Id = :Project_Id, Client_Group = :Client_Group, Sub_Unit_Sub_ISU = :Sub_Unit_Sub_ISU, Unit_ISU = :Unit_ISU, " +
			" 		Asset_Id1 = :Asset_Id1, Project_Name = :Project_Name, Unallocated = :Unallocated, " +
			" 		BASE_BRANCH = :BASE_BRANCH, SHIFT = :SHIFT, MANAGER = :MANAGER " +
			"WHERE 	SL_No = :SL_No ";

		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("Seat_no", seatNo);
		values.put("Employee_Id", employeeId);
		values.put("Employee_Name", employeeName);
		values.put("Email_Id", emailId);
		values.put("Card_No", cardNo);
		values.put("Designation", designation);
		values.put("Mobile_No", mobileNo);
		values.put("Extn", extn);
		values.put("Occupied_From_Date", occupiedFromDate);
		values.put("Allocation_Till_Date", allocationTillDate);
		values.put("WON_SWON", wonSwon);
		values.put("Project_Id", projectId);
		values.put("Client_Group", clientGroup);
		values.put("Sub_Unit_Sub_ISU", subISU);
		values.put("Unit_ISU", unitISU);
		values.put("Asset_Id1", assetId1);
		values.put("Project_Name", projectName);
		values.put("Unallocated", unallocated);
		values.put("BASE_BRANCH", baseBranch);
		values.put("SHIFT", shift);
		values.put("MANAGER", manager);
		values.put("SL_No", slNo);

		int update = namedJdbcTemplate.update(updateQuery, values);

		return update;
	
	}

	public void addNewFloor(String odcId, String odcName,
			String establishmentName, String city, int floorNumber, String wing) {
		String sql = "insert into floor_list(odc_id, odc_name, office_name, city, floor, wing) values (:idcId, :odcName, :establishmentName, :city, :floorNumber, :wing)";
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("idcId", odcId);
		hashMap.put("odcName", odcName);
		hashMap.put("establishmentName", establishmentName);
		hashMap.put("city", city);
		hashMap.put("floorNumber", floorNumber);
		hashMap.put("wing", wing);
		
		namedJdbcTemplate.update(sql, hashMap);
		
	}





	public int getMaxFloorId() {
		String sql = "select max(sl_no) from associate";
		int queryForInt = namedJdbcTemplate.queryForInt(sql, new HashMap<String, String>());
		return queryForInt;
	}





	public void addFloorRecord(int maxFloorId, String cubeId, int rowNum,
			int colNum, int posInCube, String visible, String specialText,
			String odcId) {
		String sql = "insert into floor_plan(id, cube_id, row_num, column_num, cube_num, visible, specialtxt, floor_id) values (:id, :cube_id, :row_num, :column_num, :cube_num, :visible, :specialtxt, :floor_id)";
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		hashMap.put("id", maxFloorId);
		hashMap.put("cube_id", cubeId);
		hashMap.put("row_num", rowNum);
		hashMap.put("column_num", colNum);
		hashMap.put("cube_num", posInCube);
		hashMap.put("visible", visible);
		hashMap.put("specialtxt", specialText);
		hashMap.put("floor_id", odcId);
		
		namedJdbcTemplate.update(sql, hashMap);
		
	}


	public void removeFloor(String odcId) {
		
		String deleteSql1 = "delete from associate where floor_id = :floorId";
		String deleteSql2 = "delete from floor_plan where floor_id = :floorId";
		String deleteSql3 = "delete from floor_list where odc_id = :floorId";
		
		HashMap<String,String> hashMap = new HashMap<String, String>();
		
		hashMap.put("floorId", odcId);
		
		namedJdbcTemplate.update(deleteSql1, hashMap);
		namedJdbcTemplate.update(deleteSql2, hashMap);
		namedJdbcTemplate.update(deleteSql3, hashMap);
	}





	public NewOdcVO getFloorById(String floorId) {
		
		String sql = "select * from floor_list where odc_id=:floorId";
		HashMap<String, String> paramSrc = new HashMap<String, String>();
		paramSrc.put("floorId", floorId);
		NewOdcVO queryForObject = namedJdbcTemplate.queryForObject(sql, paramSrc, new RowMapper<NewOdcVO>() {

			public NewOdcVO mapRow(ResultSet arg0, int arg1)
					throws SQLException {
				NewOdcVO newOdcVO = new NewOdcVO();
				
				newOdcVO.setOdcName(arg0.getString("odc_name"));
				newOdcVO.setCity(arg0.getString("city"));
				newOdcVO.setFloorNumber(arg0.getInt("floor"));
				newOdcVO.setWing(arg0.getString("wing"));
				newOdcVO.setEstablishmentName(arg0.getString("office_name"));
				
				return newOdcVO;
			}
		});
		
		return queryForObject;
	}

	@Override
	public byte[] getConfiguredImage() {
		
		System.gc();
		String sql = "select image from fts_meta where meta_id='logo'";
		byte[] blob = namedJdbcTemplate.queryForObject(sql, Collections.<String, Object>emptyMap(), new RowMapper<byte[]>() {

			@Override
			public byte[] mapRow(ResultSet arg0, int arg1) throws SQLException {
				Blob blob2 = arg0.getBlob(1);
				InputStream binaryStream = blob2.getBinaryStream();
				try {
					return IOUtils.toByteArray(binaryStream);
				} catch (IOException e) {
					throw new AtsException("Error Reading blob for image", e);
				}
			}
		});
		
		return blob;
		
	}
}

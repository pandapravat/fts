<%@page import="org.apache.commons.lang.WordUtils"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tcs.bbsr.ats.domain.CubeLayout"%>
<%@page import="com.tcs.bbsr.ats.domain.Cube"%>
<%@page import="com.tcs.bbsr.ats.domain.RowLayout"%>
<%@page import="java.util.List"%>
<%@page import="com.tcs.bbsr.ats.domain.FloorLayout"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="../js/addUpdate.js"></script>

<%
	FloorLayout floorLayout = (FloorLayout) request
			.getAttribute("FLOOR_LAYOUT");
%>
<div style="margin-top: 10px;">
	<input type="submit" class="g-button" id="addNewEmp" value="Allocate Floater" onclick="showAdd('Floater')">
</div>
<div>
	<div id="floorplan" class="hz-scroll-outer" >
		<div class="hz-scroll-inner">
	<%
		
		List<RowLayout> rowLayout = floorLayout.getRowLayout();
		for (RowLayout aRow : rowLayout) {
	%>
			<div class="row">
			<%
				List<CubeLayout> rowData = aRow.getRowData();
					for (CubeLayout aSqaureCube : rowData) {
						%>
						<div class="squarecube">
						<%
						List<Cube> cubesInACubicle = aSqaureCube.getCubes();
						int count = 0;
						for(Cube aCube : cubesInACubicle) {
							
							String splTxt = aCube.getSpecialTxt();
							String buttonVal = aCube.getCubeId();
							if(!StringUtils.isEmpty(splTxt)) {
								//buttonVal += (" ("+splTxt+")");
							}
							String cube1BtnClass = "";
							String cube1liClass = "";
							
							if (aCube.isVisible())
								cube1BtnClass = "cubeBtn";
							else {
								cube1liClass = "invisible";
								aCube.setCubeId("dumbBtm");
							}
							List<String> employeeNames = aCube.getEmployeeNames();
							
							String employeeNamesStr = "";
							if (!CollectionUtils.isEmpty(employeeNames)) {
								employeeNamesStr = employeeNames.toString();
								employeeNamesStr = employeeNamesStr.substring(1, employeeNamesStr.length()-1);
								employeeNamesStr = WordUtils.capitalize(StringUtils.lowerCase(employeeNamesStr));
							}
								
							if (CollectionUtils.isEmpty(employeeNames))
								
								cube1BtnClass = "emptyCube";
							else {
								if (employeeNames.size() > 1)
									
									cube1BtnClass = "multiOcuupied";
							}
							
							if (!StringUtils.isEmpty(splTxt))
								cube1BtnClass = "splCube";
				
							if(count == 0) {
								%><ul><%
							}
							if(count == 2) {
								%>	</ul>
									<ul><%
							}
							%><li class="<%=cube1liClass %>"><button class="<%=cube1BtnClass %>" id="<%=aCube.getCubeId()%>" title="<%=employeeNamesStr %>"><%=buttonVal%></button></li><%
							if(count == 3) {
								%></ul><%
							}
							count ++;
						}
									
						%></div> 
	
						<!-- square cube end -->
				<%
					}
				%>
			</div> <!-- row class end -->
			<%
				}
			%>
			
		</div>  <!-- hz-scroll-inner class end -->
		
		<!-- div class="demarcations">
			<div style="position: relative; left: 30em">Entrance Near ODC5</div>
			<div style="position: relative; left: 35em">Conf Room</div>
			<div style="position: relative; left: 40em">GL Room</div>
			<div style="position: relative; left: 45em">Entrance Near StairCase</div>
			<div style="position: relative; left: 50em">Discussion Room</div>
		</div-->
		
		
	</div>  <!-- hz-scroll-outer class end -->
	<div class="legends">
			<div>
				<div class="legendColor" style="background-color: #5B74A8"></div>
				<div>Single Occupancy</div>
			</div>
			<div>
				<div class="legendColor" style="background-color: #E9967A"></div>
				<div>Multiple Occupancy</div>
			</div>
			<div>
				<div class="legendColor" style="background-color: #006400"></div>
				<div>Empty Cubes</div>
			</div>
			<div>
				<div class="legendColor" style="background-color: #DCDCDC"></div>
				<div>Other Cubes (printer, server etc)</div>
			</div>
		
	</div>
		
</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#addFloaterBtn").click(function() {
				location.replace("./");
			});
		});
	</script>		

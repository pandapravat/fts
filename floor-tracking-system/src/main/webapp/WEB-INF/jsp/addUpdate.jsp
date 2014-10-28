<%@page import="com.tcs.bbsr.ats.domain.AssociateInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add or UpdateAssociate</title>
<script type="text/javascript" src="../js/addUpdate.js"></script>
<style>
.divTable {
	width: 100%;
	margin-top: 10px;
}

.divCol {
	width: 45%;
}

.divCol .left {
	font-weight: bold;
}

input[type=text], select {
	width: 150px;
}
</style>
</head>
<body>
	<c:set var="updateVal" value="update"></c:set>
	<c:set var="addVal" value="add"></c:set>

	<div style="padding: 10px;">
		<c:if test="${action eq updateVal}">
			<form:form action="./processUpdate.ats" method="post" id="updateForm"
				modelAttribute="associate">
				<div class="formerror">
					<form:errors path="*"></form:errors>
				</div>
				<div class="divTable">
					<div class="divRow">
						<form:hidden path="slNo" />
						<div class="divCol">
							<div class="left">Building</div>
							<div class="right">
								<form:input path="building" readonly="true" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Floor</div>
							<div class="right">
								<form:input path="floor" readonly="true" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">WING</div>
							<div class="right">
								<form:input path="wing" readonly="true" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Seat_no</div>
							<div class="right">
								<form:input path="seatNo" readonly="true" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Employee_Id</div>
							<div class="right">
								<form:input path="employeeId" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Employee_Name</div>
							<div class="right">
								<form:input path="employeeName" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Email_Id</div>
							<div class="right">
								<form:input path="emailId" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Card_No</div>
							<div class="right">
								<form:input path="cardNo" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Designation</div>
							<div class="right">
								<form:input path="designation" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Mobile_No</div>
							<div class="right">
								<form:input path="mobileNo" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Extn</div>
							<div class="right">
								<form:input path="extNo" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Occupied_From_Date</div>
							<div class="right">
								<form:input path="occupiedFromDate" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Allocation_Till_Date</div>
							<div class="right">
								<form:input path="allocationTillDate" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">WON_SWON</div>
							<div class="right">
								<form:input path="wonSwonNo" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Project_Id</div>
							<div class="right">
								<form:input path="projectId" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Client_Group</div>
							<div class="right">
								<form:input path="clientGroup" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Sub_Unit_Sub_ISU</div>
							<div class="right">
								<form:input path="subUnitSubISU" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Unit_ISU</div>
							<div class="right">
								<form:input path="unitISU" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Asset_Id1</div>
							<div class="right">
								<form:input path="assetId1" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Project_Name</div>
							<div class="right">
								<form:input path="projectName" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Unallocated</div>
							<div class="right">
								<form:input path="unallocated" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">BASE_BRANCH</div>
							<div class="right">
								<form:input path="baseBranch" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">SHIFT</div>
							<div class="right">
								<form:select items="${shiftsList}" path="shift" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">MANAGER</div>
							<div class="right">
								<form:input path="manager" />
							</div>
						</div>
					</div>
					<div class="row"></div>
				</div>

				<!-- Display submit button based on request -->
			</form:form>
			<c:if test="${action eq updateVal}">
				<form:form action="./processDelete.ats" id="deleteForm"
					commandName="associate">
					<form:hidden path="slNo" />
				</form:form>
				<div class="footerclass" align=center>
					<input type="submit" id="submitUpdate" value="Update Associate" />
					<input type="submit" id="submitRemove" value="Deallocate Associate" />
				</div>
			</c:if>
		</c:if>
		<c:if test="${action eq addVal}">
			<form:form action="./processAdd.ats" method="post" id="addForm"
				modelAttribute="associate">
				<div class="formerror">
					<form:errors path="*"></form:errors>
				</div>
				<div class="divTable">
					<form:hidden path="slNo" />
					<div class="divRow">
						<div class="divCol">
							<div class="left">Building</div>
							<div class="right">
								<form:input path="building" readonly="true" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Floor</div>
							<div class="right">
								<form:input path="floor" readonly="true" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">WING</div>
							<div class="right">
								<form:input path="wing" readonly="true" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Seat_no</div>
							<div class="right">
								<form:input path="seatNo" readonly="true" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Employee_Id</div>
							<div class="right">
								<form:input path="employeeId" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Employee_Name</div>
							<div class="right">
								<form:input path="employeeName" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Email_Id</div>
							<div class="right">
								<form:input path="emailId" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Card_No</div>
							<div class="right">
								<form:input path="cardNo" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Designation</div>
							<div class="right">
								<form:input path="designation" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Mobile_No</div>
							<div class="right">
								<form:input path="mobileNo" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Extn</div>
							<div class="right">
								<form:input path="extNo" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Occupied_From_Date</div>
							<div class="right">
								<form:input path="occupiedFromDate" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Allocation_Till_Date</div>
							<div class="right">
								<form:input path="allocationTillDate" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">WON_SWON</div>
							<div class="right">
								<form:input path="wonSwonNo" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Project_Id</div>
							<div class="right">
								<form:input path="projectId" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Client_Group</div>
							<div class="right">
								<form:input path="clientGroup" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Sub_Unit_Sub_ISU</div>
							<div class="right">
								<form:input path="subUnitSubISU" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Unit_ISU</div>
							<div class="right">
								<form:input path="unitISU" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Asset_Id1</div>
							<div class="right">
								<form:input path="assetId1" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">Project_Name</div>
							<div class="right">
								<form:input path="projectName" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">Unallocated</div>
							<div class="right">
								<form:input path="unallocated" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">BASE_BRANCH</div>
							<div class="right">
								<form:input path="baseBranch" />
							</div>
						</div>
					</div>
					<div class="divRow">
						<div class="divCol">
							<div class="left">SHIFT</div>
							<div class="right">
								<form:select items="${shiftsList}" path="shift" />
							</div>
						</div>
						<div class="divCol">
							<div class="left">MANAGER</div>
							<div class="right">
								<form:input path="manager" />
							</div>
						</div>
					</div>
					<div class="row"></div>
				</div>

				<!-- Display submit button based on request -->
				<c:if test="${action eq addVal}">
					<div class="footerclass" align=center>
						<input type="submit" value="Allocate Associate" id="submitAdd" />
					</div>
				</c:if>


			</form:form>

		</c:if>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		
		

		$("#backToFloor").click(function() {
			location.replace("./showFloorDetails.ats");
		});
	});
	</script>
</body>
</html>
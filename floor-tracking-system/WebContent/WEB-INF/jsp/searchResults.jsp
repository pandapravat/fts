<%@page import="org.springframework.util.CollectionUtils"%>
<%@page import="com.tcs.bbsr.ats.domain.AssociateInfoMin"%>
<%@page import="com.tcs.bbsr.ats.domain.AssociateInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="../js/searchResult.js"></script>
<script type="text/javascript" src="../js/addUpdate.js"></script>

<style>

</style>
<c:if test="${!empty associateList}">
	<div class="headerClass">Search results</div>
	<div class="hz-scroll-outer" style="height: 40%;" >
		<table class="tablesorter">
			<thead>
				<tr>
					<th>Trace On map</th>
					<th>Update</th>
					<th>Employee Id</th>
					<th>Employee Name</th>
					<th>Designation</th>
					<th>Email</th>
					<th>Seat No</th>
					<th>Asset Id</th>
					<th>Extension</th>
					<th>Project</th>
					<th>Shift</th>
					<c:if test="${showDetails}">
						<th>Card No</th>
						<th>WON/SWON</th>
						<th>MobileNumber</th>
						<th>Building</th>
						<th>Floor</th>
						<th>Wing</th>
						<th>Project ID</th>
						<th>Occupancy From</th>
						<th>Allocation Till</th>
						<th>Client Group</th>
						<th>Unit/ISU</th>
						<th>Sub Unit/ISU</th>
						<th>Is Unallocated</th>
						<th>Base Branch</th>
						<th>Manager</th>
					</c:if>
				</tr>
			</thead>
			<tbody >
			<c:forEach var="anAssociate" items="${associateList}" varStatus="status">
				<c:set var="rowclass" value="odd"/>
				<c:if test="${status.count % 2 == 0}">
					<c:set var="rowclass" value="even"/>
				</c:if>
				<tr class="${rowclass }">
					<td><button onclick="trace('${anAssociate.seatNo }')">Trace</button></td>
					<td><button onclick="showUpdate('${anAssociate.slNo}')">Update</button></td>
					<td>${anAssociate.employeeId}</td>
					<td>${anAssociate.employeeName}</td>
					<td>${anAssociate.designation}</td>
					<td>${anAssociate.emailId}</td>
					<td>${anAssociate.seatNo}</td>
					<td>${anAssociate.assetId1}</td>
					<td>${anAssociate.extNo}</td>
					<td>${anAssociate.projectName}</td>
					<td>${anAssociate.friendlyShift}</td>
					<c:if test="${showDetails}">
						<td>${anAssociate.cardNo}</td>
						<td>${anAssociate.wonSwonNo}</td>
						<td>${anAssociate.mobileNo}</td>
						<td>${anAssociate.building }</td>
						<td>${anAssociate.floor }</td>
						<td>${anAssociate.wing }</td>
						<td>${anAssociate.projectId }</td>
						<td>${anAssociate.occupiedFromDate }</td>
						<td>${anAssociate.allocationTillDate }</td>
						<td>${anAssociate.clientGroup }</td>
						<td>${anAssociate.unitISU }</td>
						<td>${anAssociate.subUnitSubISU }</td>
						<td>${anAssociate.unallocated }</td>
						<td>${anAssociate.baseBranch }</td>
						<td>${anAssociate.manager }</td>
					</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

</c:if>
<c:set var="allocateNew" value="false"/>
<c:set var="showExport" value="true"/>
<c:if test="${empty associateList}">
	<c:if test="${source eq 'map'}">
		<c:set var="allocateNew" value="true"/>
		<c:set var="showExport" value="false"/>
		<div class="warnmsg">No Employees allocated to this cube.</div> 
	
	</c:if>
	<c:if test="${!(source eq 'map')}">
		<c:set var="showExport" value="false"/>
		<div class="warnmsg">Your search returned no results. </div>
	</c:if>
</c:if>
<c:if test="${source eq 'map'}">
	<c:set var="allocateNew" value="true"/>
</c:if>


<div id="buttonsPanel" class="center">
	<c:if test="${showExport eq true }">
		<form action="./exportResults.ats" method="post" id="exportForm">
			<input type="hidden" id="exportdata" name="data"/>
			<input type="button" class="g-button" id="exportSearch" value="Export Results"/>
		</form>
	</c:if>
	<c:if test="${allocateNew eq true }">
		<input type="submit" class="g-button" id="addNewEmp" value="Allocate New" onclick="showAdd('${cubeId }')">
	</c:if>
	<input type="button" class="g-button"  value="Another Search" id="anotherSrchBtn"/>
</div>


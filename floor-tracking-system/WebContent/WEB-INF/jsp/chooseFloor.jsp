<%@page import="java.util.Set"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Choose floor for details</title>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	<div style="position: relative; top:20px;">
		<a href="./configureNewFloor.ats?step=1">Configure new floor</a>
	</div>
	<div style="margin: 30px; text-align: center;">
	<form:form method="post" id="chooseFloorForm" action="./showFloorDetails.ats" commandName="map">
		<div>
			Choose A Floor <form:select path="odcId" items="${alldata}"></form:select>
		</div>
		<div style="margin-top: 20px;">
			<input type="submit" value="Show Floor Plan">
			<button class="g-button" id="removeFloor" disabled="disabled">Remove Floor</button>
		</div>
	</form:form>
	<div id="siteviewinfo">
		Best viewed in IE 8+, Google Chrome, Mozilla Firefox 8+ with 1280*960 resolution
	</div>

</div>
<script type="text/javascript">

$(document).ready(function() {
	$("#removeFloor").click(function() {
		if(confirm("Are you sure to remove this floor?")) {
			$("#chooseFloorForm").attr("action", "./removeFloor.ats");
			$("#chooseFloorForm").submit();
		} else {
			return false;
		}
	});
});
</script>
</body>
</html>
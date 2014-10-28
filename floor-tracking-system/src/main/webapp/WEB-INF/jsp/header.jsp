<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="StyleSheet" href="../css/style.css">
<link rel="StyleSheet" href="../css/error-style.css">
<link rel="StyleSheet" href="../css/sorted-grid-style.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="../js/header.js"></script>

<div>
	<div class='logo'>
		<img alt="TCS Logo" class="logoImg" src="<%=request.getContextPath() %>/ats/getLogo.ats"
			title="TCS logo">
	</div>
	<div class="headerTxt">Floor Tracking System</div>
	<div class="headersSubText">${sessionScope.floorname }</div>
	<c:if test="${not empty message }">
		<c:if test="${not empty cubeId }">
			<div id="affectedCube">
				<c:out value="${cubeId }"></c:out>
			</div>
		</c:if>
		<div id="message">
			<c:out value="${message }"></c:out>
		</div>
	</c:if>
	<div id="credits">
		<div id="title">+ Credits</div>
		<div id="contents" style="display: none">
			<div class="even">Pravat Kumar Panda, TCS - Bhubaneswar</div>
			<div class="odd">Debidutta Maharana, TCS - Bhubanswar</div>
			<div class="even">Mahesh Mahapatra, TCS - Bhubaneswar</div>
			<div class="odd">Upendra Biswal, TCS - Bhubanswar</div>
		</div>
	</div>
	<div class="asyncView" id="updateFormHolder">
		<div id="title" class="asyncViewContents">
			Update Associate Details <img src="../images/close.jpg"
				title="Close screen" alt="close" width="15px" height="15px"
				style="float: right;" class="closeView">
		</div>
		<div id="contents">
			<div
				style="width: 10%; margin: auto; margin-top: 30px; margin-bottom: 30px;">
				<img src="../images/loading.gif" title="loading.."
					alt="loading Data">
			</div>
		</div>
	</div>
	<div class="asyncView" id="addFormHolder">
		<div id="title" class="asyncViewContents">
			Add Associate Details <img src="../images/close.jpg"
				title="Close screen" alt="close" width="15px" height="15px"
				style="float: right;" class="closeView">
		</div>
		<div id="contents">
			<div
				style="width: 10%; margin: auto; margin-top: 30px; margin-bottom: 30px;">
				<img src="../images/loading.gif" title="loading.."
					alt="loading Data">
			</div>
		</div>
	</div>
	<jsp:include page="../html/help.html"></jsp:include>

	<!-- div id="location">
	<img alt="Direction" src="../images/images.jpg" width="50px" height="50px" title="Compass Direction">
	</div-->

</div>
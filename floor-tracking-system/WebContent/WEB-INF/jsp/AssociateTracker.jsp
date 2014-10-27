<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Associate tracking System</title>
<script type="text/javascript" src="../js/associateTracker.js"></script>

<style type="text/css">
#searchpane .divTable{
	width:25em;
}

#searchpane .divRow .divCol{
	width:100%;
}
</style>
</head>
<body>
<div class="container">
	<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	<div id="siteHits"> Total visits:<c:out value="${hitcount }"></c:out> </div>
	<jsp:include page="/WEB-INF/jsp/FloorPlan.jsp"></jsp:include>
	<br><br><br>
	<div  class="backgroundHider"></div>
	<div class="details">
			<div id="searchpane" class="blockCenter">
				<jsp:include page="/WEB-INF/jsp/searchForm.jsp"></jsp:include>
			</div>
			<div id="searchresults" >
				<%-- PLACE HOLDER FOR SEARCH RESULTS --%>			
			</div>
	</div>
	<input type="button" id="backToChooseFloor" class="g-button" value="Back"/>
</div>


<script type="text/javascript" src="../js/associateTracker.js"></script>
</body>

</html>
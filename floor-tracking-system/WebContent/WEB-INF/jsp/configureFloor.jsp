<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Configure Floor</title>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<style type="text/css">
/*Override default css table style */
#configureStep1.divTable{
	width:30%;
}

#configureStep1 .divRow .divCol{
	width:100%;
}
</style>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<c:if test="${step == 1 }">
	<form:form action="./configureNewFloor.ats?step=2" commandName="odcVo">
		<div class="formerror">
			<form:errors path="*" ></form:errors>
		</div>
		<div id="configureStep1" class="center divTable">
				<div class="tableTitle">Provide Floor Details</div>
				
				<div class="divRow">
					<div class="divCol">
						<div class="left">ODC Name (*)</div>
						<div class="right"><form:input path="odcName"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Floor (*)</div>
						<div class="right"><form:input path="floorNumber"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Wing (*)</div>
						<div class="right"><form:input path="wing"/></div>
					</div>
				</div>
				<div class="divRow">
				<div class="divCol">
					<div class="left">Establishment Name (*)</div>
					<div class="right"><form:input path="establishmentName"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">City (*)</div>
						<div class="right"><form:input path="city" /></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Rows (*)</div>
						<div class="right"><form:input path="rows" /></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Columns (*)</div>
						<div class="right"><form:input path="columns" /></div>
					</div>
				</div>
				<div class="divRow">
				<div class="divCol">
					<div class="left">Floor ID (*)</div>
					<div class="right"><form:input path="odcId" /></div>
					</div>
				</div>
				<c:if test="${step == 1 }">
					<div class="divRow">
						<div class="divCol" style="margin-top: 30px;">
							<div class="left"><input type="submit" value="Next" /></div>
							<div class="right"><input type="button" class="g-button" value="Back" onclick="location.replace('./chooseFloor.ats')"/></div>
						</div>
					</div>
				</c:if>
		</div>
	</form:form>
</c:if>
<c:if test="${step eq 2 }">
	<div id="configureStep2">
		<form:form action="./configureNewFloor.ats?step=3" commandName="odcVo" id="confStep2">
			<div id="configureStep1" class="center divTable">
					<div class="tableTitle">Provide Floor Details</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">ODC Name (*)</div>
						<div class="right"><form:input path="odcName" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Floor (*)</div>
						<div class="right"><form:input path="floorNumber" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Wing (*)</div>
						<div class="right"><form:input path="wing" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Establishment Name (*)</div>
						<div class="right"><form:input path="establishmentName" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">City (*)</div>
						<div class="right"><form:input path="city" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Rows (*)</div>
						<div class="right"><form:input path="rows" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Columns (*)</div>
						<div class="right"><form:input path="columns" readonly="true"/></div>
					</div>
				</div>
				<div class="divRow">
					<div class="divCol">
						<div class="left">Floor ID (*)</div>
						<div class="right"><form:input path="odcId" readonly="true"/></div>
					</div>
				</div>
			</div>
			<div id="floorplan" class="hz-scroll-outer" style="margin-top:30px">
				<div class="hz-scroll-inner">
					<c:forEach var="aRow" items="${odcVo.floorLayout.rowLayout}" varStatus="rowCnt">
						<div class="row">
							<c:forEach var="aCol" items="${aRow.rowData}" varStatus="colCount">
								<div class="squarecube border1">
									<c:forEach var="aCube" items="${aCol.cubes}" varStatus="stat">
										<c:if test="${stat.count eq 1 }">
											<ul>
										</c:if>
										<c:if test="${stat.count eq 3 }">
											</ul>
											<ul>
										</c:if>
											<li><form:input cssClass="cubeBtn" path ="floorLayout.rowLayout[${rowCnt.count-1 }].rowData[${colCount.count-1 }].cubes[${stat.count-1 }].cubeId" style="width : 50px; HEIGHT:50PX"></form:input>
											<form:checkbox cssClass="splInd" path="floorLayout.rowLayout[${rowCnt.count-1 }].rowData[${colCount.count-1 }].cubes[${stat.count-1 }].special"></form:checkbox></li>
										<c:if test="${stat.count eq 4 }">
											</ul>
										</c:if>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
			<div><input type="submit" id="doneBtn" value="Submit"/> 
			<input class="g-button" type="button" id="prvBtn" value="Preview"/>
			<input class="g-button" type="button" id="backButton" value="Back To Previous"/>
			</div>
		</form:form>
		<div id="previewPane"></div>
			<div><input class="g-button" type="button" id="prvBack" value="Back"/></div>	
	</div>
</c:if>

</body>
<script type="text/javascript">
$(document).ready(function() {
	var maxCubes=0;
	$(".hz-scroll-inner").children().each(function(index) {
		if($(this).children().size() > maxCubes) {
			maxCubes = $(this).children().size(); // need to set the width of inner scroll
		}
    });
	$(".hz-scroll-inner").css("width" , (maxCubes)* ($(".squarecube").width() + 10) + "px");
	$("#floorPrv").hide();
	$("#prvBack").hide();
	$("#prvBtn").click(function() {
		$("#previewPane").show(2000);
		$("#previewPane").children().remove();
		$("#previewPane").append($("#floorplan").clone());
		$("#floorplan").hide(2000);
		$("#doneBtn").hide();
		$("#prvBack").show();
		$("#prvBtn").hide();

		$("#previewPane").find(".squarecube").removeClass("border1");
		var cubes = $("#previewPane").find(".cubeBtn");
		$(cubes).each(function() {
			var value = $(this).val();
			if(value == '') {
				$(this).parent("li").addClass("invisible");
				$(this).replaceWith("<button class='cubeBtn' id='"+ value + "'>" + value +"</button>");
			} else {
				var btnClass = "emptyCube";
				var checkedval = $(this).siblings(".splInd").attr("checked");;
				if(checkedval == true) {
					btnClass = "splCube";
				}
				$(this).siblings(".splInd").hide();
				$(this).replaceWith("<button class='"+ btnClass +"' id='"+ value + "'>" + value +"</button>");
			}
		});		
		addScroll("previewPane");
	});
	$("#prvBack").click(function() {
		$("#floorplan").show(2000);
		$("#previewPane").hide(2000);
		$("#doneBtn").show();
		$("#prvBack").hide();
		$("#prvBtn").show();
		
	});
	$("#backButton").click(function() {
		$("#confStep2").attr("action", "./configureNewFloor.ats?step=1");
		$("#confStep2").submit();
		
	});
});

function addScroll(targetId) {
	var maxCubes=0;
	$("#"+targetId+"").find(".hz-scroll-inner").children().each(function(index) {
        
		if($(this).children().size() > maxCubes) {
			maxCubes = $(this).children().size(); // need to set the width of inner scroll
		}
        
    });
	$("#"+targetId+"").find(".hz-scroll-inner").css("width" , (maxCubes)* ($(".squarecube").width() + 10) + "px");
}
</script>
</html>
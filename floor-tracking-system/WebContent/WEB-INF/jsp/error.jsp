<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page import="java.io.StringReader"%>
<%@page import="com.tcs.bbsr.ats.bi.AtsException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%
	AtsException aex = (AtsException)request.getAttribute("ERROR");
	String stackTrace = "";
	if(null != aex.getRootException()) {
		StringWriter reader = new StringWriter();
		aex.getRootException().printStackTrace(new PrintWriter(reader));
		stackTrace = reader.toString();
	}
	String errorCode = aex.getErrorCode() == null? "" : aex.getErrorCode();
	String errorShort = aex.getShortText() == null? "" : aex.getShortText();
	String errorDesc = aex.getDescription() == null? "" : aex.getDescription();
%>
<div class="errortitle">
an error occurred
</div>
<div class="errorShort">
<%=errorCode =errorShort%>
</div>
<div class="errorDesc">
<%=errorDesc%>
</div>
<div class="errorStack">
<%=stackTrace%>
</div>
<div style="background-color: blue; color:#fff; font-weight: bold; cursor:pointer; width:3em; text-decoration: underline;" onclick="javascript:window.history.back()">
Back
</div>
</body>
</html>
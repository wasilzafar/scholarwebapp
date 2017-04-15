<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="././css/scholarstyle.css">
<link rel="stylesheet" type="text/css" href="././jqueryui/css/ui-darkness/jquery-ui-1.10.3.custom.css" />
<script src="././jqueryui/js/jquery-1.9.1.js"></script>
<script src="././jqueryui/js/jquery-ui-1.10.3.custom.js"></script>
<script src="././js/jsfunctions.js"></script>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Reports</title>
<script type="text/javascript">
$(function() {
	$( "#menu" ).menu({
		select: function( event, ui ) {
			
		}
	});
});
</script>
</head>
<body>
<div id="status">
<jsp:include page="../common/status.jsp"></jsp:include>
</div>
<br>
<%@ include file="../common/srheader.jsp"  %>
<br>

<div id="reports">
<ul id="menu">
<li ><a href="http://localhost:8080/PHDWebApp/scholarmonthlyreports">Monthly Reports</a></li>
<li><a href="http://localhost:8080/PHDWebApp/scholarquarterlyreports">Quarterly Reports</a></li>
</ul>
</div>
<div id="display">
<div id="content">






</div>
</div>

</body>
</html>
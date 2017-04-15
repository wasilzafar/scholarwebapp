<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/supervisorstyle.css">
<link rel="stylesheet" type="text/css" href="./css/jsDatePick_ltr.min.css" />
<script src="./js/jquery-1.8.3.min.js"></script>
<script src="./js/jsDatePick.min.1.3.js"></script>
<script src="./js/jsfunctionsadmin.js"></script>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supervisor Analysis</title>
</head>
<body>
<div>
<%@ include file="../common/srheader.jsp"  %>
</div>
<div id="status">
<jsp:include page="../common/status.jsp"></jsp:include>
</div>
<br>
<div id="reports">
<jsp:include page="../admin/adminmenu.jsp"></jsp:include>
</div>
<div id="display">
<div id="content">






</div>
</div>

</body>
</html>
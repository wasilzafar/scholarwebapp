<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<link rel="stylesheet" type="text/css" href="././jqueryui/css/ui-darkness/jquery-ui-1.10.3.custom.css" />
<script src="././jqueryui/js/jquery-1.9.1.js"></script>
<script src="././jqueryui/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="././jqueryui/jquerytablesorter/jquery.tablesorter.min.js"></script>
<script src="././js/jsfunctionsadmin.js"></script>
<link rel="stylesheet" type="text/css" href="././css/supervisorstyle.css"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<title>Supervisor Analysis</title>
</head>
<body>
<div id="hideBg" style="position:absolute;top:0;left:0" onclick="closeDisplayPanel()"></div>
<img alt="Image" src="././images/bg.jpg" id="fsbgi" />
<div id="status">
<%@ include file="../common/srheader.jsp"  %>
</div>
<div id="status">
<jsp:include page="../common/status.jsp"></jsp:include>
</div>
<br>

<div id="reports">
<jsp:include page="../admin/adminmenu.jsp"></jsp:include>
</div>
<div id="showpanel" style="display:none;position:absolute"></div>

<div id="display">
<div id="content">
</div>
</div>

</body>
</html>
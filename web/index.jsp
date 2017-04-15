<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jsfunctions.js"></script>
<title>Login Screen</title>
<link rel="stylesheet" type="text/css" href="./css/loginstyle.css">
<style type="text/css">
#bgimage {
	z-index: -999;
	min-height: 100%;
	min-width: 1024px;
	width: 100%;
	height: auto;
	position: fixed;
	top: 0;
	left: 0;
}
#mainform{
height: 300px;
width: 620px;
position : relative;
margin-left:auto;
margin-right:auto;
top : 200px;
opacity :0.8;
background-color: grey;
}
#loginPanel{
height: 200px;
width: 300px;
background-color: green;
float:left;
position : relative;
top : 50px
}
#fbpanel{
height: 200px;
width: 300px;
background-color: green;
float:right;
position : relative;
top : 50px
}
#formfields{
height: 130px;
width: 200px;
position : relative;
margin-left:auto;
margin-right:auto;
}
#fbfields{
height: 130px;
width: 200px;
position : relative;
margin-left:auto;
margin-right:auto;
}
</style>
</head>
<body>
<img alt="" src="./images/scene.jpg" id="bgimage" />
<div id="mainform">
<div id="loginPanel">
<s:fielderror/><br>
<div id="formfields">
<s:form action="login" theme="simple">
<s:textfield value="User ID " name="id"></s:textfield><br><br>
<s:password value="Password " name="password"></s:password><br><br>
<s:radio name="identity" list="#{'scholar':'Scholar', 'supervisor':'Supervisor'}" />
<br><br>
<s:submit type="submit" value="Login"></s:submit>
</s:form>
</div>
</div>
<div id="fbpanel">
<br>
<div id="fbfields">
<input value="Login with Facebook"></input>
</div></div>
</div>
</body>
</html>
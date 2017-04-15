<!DOCTYPE s:form PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form cssClass="searchreports" action="searchreports" target="targetIframe" theme="simple" cssStyle="position: relative;left: 7%;top:10px">
<fieldset style="width: 70%">
<legend>Search Criteria</legend><br>
<div>
<s:doubleselect title="Department : " list="#request.depSupMap.keySet()"
 name="departmentName" 
 doubleListTitle="Supervisor : " doubleName="supervisorID" 
headerValue="ALL"  headerKey="-1" 
doubleList="#request.depSupMap[top]" 
doubleListKey="supervisorID" doubleListValue="supervisorFirstName" 
doubleHeaderKey="-1" doubleHeaderValue="ALL"  theme="simple"/>
</div><br>
<div style="display:inline">
<label style="display: inline">From :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><s:textfield  cssClass="calender" name="fromDate"></s:textfield>
</div>
<div style="display:inline">
<label style="display: inline">To :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><s:textfield cssClass="calender" name="toDate"></s:textfield>
</div><br>
<label style="display: inline">Scholar Type :</label><s:select name="scholarType" list="{'JRF','UGCNET','ALL'}"></s:select><br>
<s:submit cssClass="submitButton" title="Search" value="Search"></s:submit>
</fieldset>
</s:form>
<iframe name="targetIframe" id="targetIframe" src="" height="400px" width="100%"></iframe>
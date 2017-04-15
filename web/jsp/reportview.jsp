<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="./js/jquery-1.8.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:property value="#request.reportType"/></title>
<style type="text/css">
@CHARSET "ISO-8859-1";

    form {
    background: linear-gradient(top, #fff, #f8f8f8);
    border: 1px solid #d0d2d5;
    border-bottom: 1px solid #bebfc2;
    border-radius: 4px;
    margin: 0 0 20px 0;
    padding: 60px;
    width: 10in;
    position : relative;
    left:10%;
    font-family: 'Trebuchet MS' ,'Droid Sans', Arial, 'Helvetica Neue', 'Lucida Grande', sans-serif;
    }
    label {
    color: #404853;
    display: block;
    font-weight: bold;
    }
    input,select {
    background: #fff;
    border: 1px solid #c6c9cc;
    border-radius: 4px;
    box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.1), 0 1px 0 #fff;
    color: #555;
    font: 13px/20px 'Trebuchet MS','Droid Sans', Arial, 'Helvetica Neue', 'Lucida Grande', sans-serif;
    margin: 0 0 20px 0;
    padding: 3px;
    width: 200px;
    }
    fieldset {
    background: linear-gradient(top, #ebeced, #dedfe0);
    border: none;
    border-top: 1px solid #d0d2d5;
    border-radius: 0 0 4px 4px;
    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.75);
    margin: 5px 0 -20px -20px;
    padding: 18px 20px;
    width: 212px
    }
    fieldset input {
    margin: 0 0 20px 0;
    width: auto;
    }
    fieldset input[type="button"] {
     background:  #687587;
    border: none;
    color: #fff;
    font-weight: bold;
    padding: 5px 10px;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.6);
    }
    fieldset input[type="button"]:hover {
     background: #404853;
    }
    fieldset input[type="button"]:active {
     background: #333942;
    }
    .submitButton {
    background:  #687587;
    border: none;
    color: #fff;
    font-weight: bold;
    float: left;
    padding: 5px 10px;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.6);
    }
    .submitButton:hover {
    background: #404853;
    }
    .submitButton:active {
    background: #333942;
    }
    fieldset label {
    color: #888;
    cursor: pointer;
    font-size: 16px;
    font-weight: normal;
    margin: 5px 0 5px 0;
    }
    fieldset label input {
    margin: -2px 2px 0 0;
    padding: 0;
    }
</style>
</head>
<body>
<div id="reports">
<div id="menu">
</div>
</div>
<div id="display">
<div id="content">
<s:if test="#request.reportType=='Monthly Report'">
<div id="mrcontent">
<s:form action="submitMonthly" theme="simple" enctype="multipart/form-data" method="post">
<fieldset>
<h1>Monthly Progress Report</h1><br><br><br><br>
<s:label>Name of Candidate</s:label><s:textfield disabled="true" name="scholar.scholarScreenName" value="%{#request.rep.scholar.scholarFirstName} %{#request.rep.scholar.scholarMiddleName} %{#request.rep.scholar.scholarLastName}"></s:textfield><br>
<s:label>Name of Department</s:label><s:textfield disabled="true" name="scholar.department.departmentname" value="%{#request.rep.scholar.department.departmentname}"></s:textfield><br>
<s:label>Name of Supervisor</s:label><s:textfield disabled="true" name="approver.supervisorScreenName" value="%{#request.rep.scholar.supervisor.supervisorFirstName} %{#request.rep.scholar.supervisor.supervisorMiddleName} %{#request.rep.scholar.supervisor.supervisorLastName}"></s:textfield><br>
<s:label>Date of Registration</s:label><s:textfield disabled="true" name="scholar.dateOfRegistration" value="%{#request.rep.scholar.dateOfRegistration}"></s:textfield><br><br>

<s:label>From</s:label><s:textfield disabled="true" title="From" name="fromDate" value="%{#request.rep.fromDate}"></s:textfield><br>
<s:label>To</s:label><s:textfield disabled="true" title="To" name="toDate" value="%{#request.rep.toDate}"></s:textfield><br>
<h3><s:label>Research Topic</s:label></h3><s:textarea disabled="true" rows="1" cols="90" name="scholar.topic" value="%{#request.rep.scholar.topic}"></s:textarea><br><br>

 Certified that I have been present in the department and worked on my Ph.D./M.Phil. during the period
 for which claim of fellowship is preferred in this bill.I have availed <s:textfield disabled="true" theme="simple" name="leaves" value="%{#request.rep.leaves}"></s:textfield>
  leaves during the aforesaid period. I have not been on any paid employment during the period.
<br>

<h3><s:label>Work Completed</s:label></h3><br><s:textarea disabled="true" rows="8" cols="90" name="workCompleted" value="%{#request.rep.workCompleted}"></s:textarea><br><br>
<h3><s:label>Attachments </s:label></h3>
<s:property value="%{#request.rep.attachmentFileName}"/>
<s:url action="invoke" var="murl" escapeAmp="false">
<s:param name="object">monthlyreport</s:param>
<s:param name="operation">view</s:param>
<s:param name="input"><s:property value="%{#request.rep.mreportid}"/></s:param>
</s:url>
<a href="<s:property value="murl"/>" >View Attachment</a>
<br>
<s:file name="attachment"></s:file>
<h3><s:label>Remarks</s:label></h3><br><s:textarea rows="4" cols="90" name="remark"></s:textarea>
<br><br>

<table>
<tr align="center"><td><s:submit value="Review"></s:submit></td><td><input value="Approve" type="button"/></td></tr>
</table>
</fieldset>
</s:form>
</div>
</s:if>
<s:else> 
<div id="qrcontent">
<s:form action="submitQuarterly" theme="simple" enctype="multipart/form-data" method="post">
<fieldset>
<h1>Quarterly Progress Report</h1><br><br><br><br>
<s:label>Name of Candidate</s:label><s:textfield disabled="true" name="scholar.scholarScreenName" value="%{#request.rep.scholar.scholarFirstName} %{#request.rep.scholar.scholarMiddleName} %{#request.rep.scholar.scholarLastName}"></s:textfield><br>
<s:label>Name of Department</s:label><s:textfield disabled="true" name="scholar.department.departmentname" value="%{#request.rep.scholar.department.departmentname}"></s:textfield><br>
<s:label>Name of Supervisor</s:label><s:textfield disabled="true" name="approver.supervisorScreenName" value="%{#request.rep.scholar.supervisor.supervisorFirstName} %{#request.rep.scholar.supervisor.supervisorMiddleName} %{#request.rep.scholar.supervisor.supervisorLastName}"></s:textfield><br>
<s:label>Date of Registration</s:label><s:textfield disabled="true" name="scholar.dateOfRegistration" value="%{#request.rep.scholar.dateOfRegistration}"></s:textfield><br><br>

<s:label>From</s:label><s:textfield disabled="true" title="From" name="fromDate" value="%{#request.rep.fromDate}"></s:textfield><br>
<s:label>To</s:label><s:textfield disabled="true" title="To" name="toDate" value="%{#request.rep.toDate}"></s:textfield><br>

<h3><s:label>Research Topic</s:label></h3><s:textarea disabled="true" name="scholar.topic" rows="1" cols="90" value="%{#request.rep.scholar.topic}"></s:textarea><br><br>


<h3><s:label>Work Completed</s:label></h3><br><s:textarea disabled="true" rows="8" cols="90" value="%{#request.rep.workCompleted}"></s:textarea><br>
<h3><s:label>Attachments </s:label></h3>
<s:property value="%{#request.rep.attachmentFileName}"/>
<s:url action="invoke" var="murl" escapeAmp="false">
<s:param name="object">quarterlyreport</s:param>
<s:param name="operation">view</s:param>
<s:param name="input"><s:property value="%{#request.rep.qreportid}"/></s:param>
</s:url>
<a href="<s:property value="murl"/>">View Attachment</a>
<br>
<s:file name="attachment"></s:file><br>

<h3><s:label>Quantum of Ph.D. work completed : </s:label></h3><br>
<input type="text" name="progress" id="progressMade"></input><br><div id="slider"></div><br>
<h3><s:label>Remarks</s:label></h3><br><s:textarea rows="4" cols="90"></s:textarea>
<br><br>

<table>
<tr align="center"><td><s:submit value="Review"></s:submit></td><td><input value="Approve" type="button"/></td></tr>
</table>
</fieldset>
</s:form>
</div>
</s:else>
</div>
</div>
</body>
</html>
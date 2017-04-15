<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="rhead">
<div id="submitSuccess" title="Success">
The form has been submitted successfully.
</div>
<div id="submitFailure" title="Failure">
Form submission failed.
</div>
<div id="mrcontent">
<s:form cssClass="monthlyFormToSubmit" action="submitMonthly" theme="simple" enctype="multipart/form-data" method="post">
<fieldset>
<legend>Monthly Progress Report</legend><br><br><br><br>
<label>Name of Candidate</label><br><s:textfield name="scholar.scholarScreenName" value="%{#session.user.scholarFirstName} %{#session.user.scholarMiddleName} %{#session.user.scholarLastName}"></s:textfield><br>
<label>Name of Department</label><br><s:textfield name="scholar.department.departmentname" value="%{#session.user.department.departmentname}"></s:textfield><br>
<label>Name of Supervisor</label><br><s:textfield name="approver.supervisorScreenName" value="%{#session.user.supervisor.supervisorFirstName} %{#session.user.supervisor.supervisorMiddleName} %{#session.user.supervisor.supervisorLastName}"></s:textfield><br>
<label>Date of registration</label><br><s:textfield name="scholar.dateOfRegistration" value="%{#session.user.dateOfRegistration}"></s:textfield><br><br>
<label>From</label><br><s:textfield title="From" cssClass="calender" name="fromDate"></s:textfield><br><label>To</label><br><s:textfield title="To" cssClass="calender" name="toDate"></s:textfield><br><br>
<label>Research Topic</label><br><s:textarea rows="1" cols="90" name="scholar.topic" value="%{#session.user.topic}"></s:textarea><br>

 Certified that I have been present in the department and worked on my Ph.D./M.Phil. during the period
for which claim of fellowship is preferred in this bill.I have availed <s:textfield theme="simple" name="leaves" value="0"></s:textfield>
 leaves during the aforesaid period. I have not been on any paid employment during the period.
<br>

<label>Work Completed</label><br><s:textarea name="workCompleted"></s:textarea><br>
<label>Attachments </label><br><s:file name="attachment"></s:file><br><br>
<label>Remarks</label><br><s:textarea disabled="true" name="remark"></s:textarea><br>
<br><br>
<s:submit cssClass="submitButton" value="Submit"></s:submit>
</fieldset>
</s:form>
</div>
<div id="qrcontent">
<s:form cssClass="quarterlyFormToSubmit" action="submitQuarterly" theme="simple" enctype="multipart/form-data" method="post">
<fieldset>
<legend>Quarterly Progress Report</legend><br><br><br><br>
<label>Name of Candidate</label><br><s:textfield name="scholar.scholarScreenName" value="%{#session.user.scholarFirstName} %{#session.user.scholarMiddleName} %{#session.user.scholarLastName}"></s:textfield><br>
<label>Name of Department</label><br><s:textfield name="scholar.department.departmentname" value="%{#session.user.department.departmentname}"></s:textfield><br>
<label>Name of Supervisor</label><br><s:textfield name="approver.supervisorScreenName" value="%{#session.user.supervisor.supervisorFirstName} %{#session.user.supervisor.supervisorMiddleName} %{#session.user.supervisor.supervisorLastName}"></s:textfield><br>
<label>Date of registration</label><br><s:textfield name="scholar.dateOfRegistration" value="%{#session.user.dateOfRegistration}"></s:textfield><br><br>

<label>From</label><br><s:textfield title="From" cssClass="calender" name="fromDate"></s:textfield><br><label>To</label><br><s:textfield title="To" cssClass="calender" name="toDate"></s:textfield><br><br>

<label>Research Topic</label><br><s:textarea name="scholar.topic" rows="1" cols="90" value="%{#session.user.topic}"></s:textarea><br>


<label>Work Completed</label><br><s:textarea name="workCompleted"></s:textarea><br>
<label>Attachments </label><s:file name="attachment"></s:file><br><br>

<label>Quantum of Ph.D. work completed : </label><br>
<input type="text" name="progress" id="progressMade"></input><br><div id="slider"></div><br>
<label>Remarks</label><br><s:textarea name="remark" disabled="true"></s:textarea>
<br><br>
<s:submit  cssClass="submitButton" value="Submit"></s:submit>
</fieldset>
</s:form>
</div>
<div id="reportTable">
</div>
</div>
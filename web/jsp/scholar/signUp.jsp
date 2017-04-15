<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="././jqueryui/css/ui-darkness/jquery-ui-1.10.3.custom.css" />
<script src="././jqueryui/js/jquery-1.9.1.js"></script>
<script src="././jqueryui/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="././jqueryui/jquerytablesorter/jquery.tablesorter.min.js"></script>
<script src="././js/jquery.validate.min.js"></script>
<script src="././js/jsfunctionsext.js"></script>
<link rel="stylesheet" type="text/css" href="././css/scholarstyle.css">
<link rel="stylesheet" type="text/css" href="././css/form.css">
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Scholar Form</title>
<style>
<!--
table{ 
     no-wrap : true;
	 width:70%;
	 border-style:solid;
	 border-width:5px;
	 border-color: green;
}
table td,th {
white-space : nowrap;
width : 33%;
font-size:1em;
font-family: 'Trebuchet MS' ,'Droid Sans', Arial, 'Helvetica Neue', 'Lucida Grande', sans-serif;
border:1px solid #98bf21;
}

table td input {
margin : 1px;
padding : 5px;
border : 0px;
font-family: 'Trebuchet MS' ,'Droid Sans', Arial, 'Helvetica Neue', 'Lucida Grande', sans-serif;
}

.hiddenElements{
display:none;
}
-->
</style>
</head>
<body>
<div id="status">
<%@ include file="../common/srheader.jsp"  %>
</div>
<div id="status">
<div style="height: 30px;background-color: grey"></div>
</div>
<br>
<div id="reports">
</div>
<div id="display">
<div id="submitError" title="Success">
Error in form. Correct the values in Red.
</div>
<div id="content">
<s:fielderror/>
<s:form cssClass="submitToRegister" action="registerSubmit" theme="simple"> 
<fieldset>
<legend>Sign Up </legend>
<s:textfield name="scholarFirstName" value="First Name"></s:textfield><s:textfield name="scholarMiddleName" value="Middle Name"></s:textfield><s:textfield name="scholarLastName" value="Last Name"></s:textfield><br>
<s:textfield name="scholarFatherFirstName" value="Father's First Name"></s:textfield><s:textfield name="scholarFatherLastName" value="Father's Last Name"></s:textfield><br>
<s:textfield name="scholarSpouseFirstName" value="Spouse's First Name"></s:textfield><s:textfield name="scholarSpouseLastName" value="Spouse's Last Name"></s:textfield><br>
<label>Gender</label><br>
Male &nbsp;<input name="gender" type="radio" value="Male"/>&nbsp;&nbsp;&nbsp;Female&nbsp;<input name="gender" type="radio" value="Female"/><br>
<label>Date of Birth</label><s:textfield name="dob" cssClass="calender"></s:textfield>
<label>Nationality</label><s:textfield name="nationality" value="Indian"></s:textfield><br>
<label>Mobile Number : </label><s:textfield name="mobileNumber"></s:textfield>
<label>Landline Number : </label><s:textfield name="landlineNumber"></s:textfield><br>
<label>Email ID : </label><s:textfield name="emailID"></s:textfield><br>
<label>Correspondence Address</label><br><s:textarea name="correspondenceAddress" cols="60"></s:textarea><br>
<label>State and District</label>

<s:doubleselect list="#session.statDistMap.keySet()" name="correspondenceAddressState.statename" doubleName="correspondenceAddressDistrict.districtid"
 doubleList="#session.statDistMap[top]" doubleListKey="districtid" doubleListValue="districtname"/>

<br>

<label>Pin Code : </label><s:textfield name="correspondenceAddressZipCode"></s:textfield><br>	

<label>Permanent Address</label><br><s:textarea name="permanentAddress" cols="60"></s:textarea><br>

<label>State and District</label>
<s:doubleselect list="#session.statDistMap.keySet()" name="permanentAddressState.statename" doubleName="permanentAddressDistrict.districtid"
 doubleList="#session.statDistMap[top]" doubleListKey="districtid" doubleListValue="districtname"/>

<br>

<label>Pin Code : </label><s:textfield name="permanentAddressZipCode"></s:textfield><br>





<label>Course Name : </label><s:textfield name="coursename"></s:textfield><br>


<s:select cssClass="instituteInfo" list="#session.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="-1" headerValue="Select Institute"></s:select>
<br>

<label>Faculty : </label>
<select class="facultyInfo" name="faculty.facultyid"></select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<select class="departmentInfo" name="department.departmentid"></select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		


<label>Category : </label><s:textfield name="category"></s:textfield><br>
<label>Category Code : </label><s:textfield name="categorycode"></s:textfield><br>
<label>Enrollment Number( if any ) : </label><s:textfield name="enrollmentNumber"></s:textfield><br>
<label>Date of Registration : </label><s:textfield name="dateOfRegistration" cssClass="calender"></s:textfield><br>
<label>Proposed Topic of Research : </label><br><s:textarea name="topic" cols="60"></s:textarea><br>
Is topic modified &nbsp;&nbsp;<input id="istopicmod" name="istopicmod" type="checkbox" />
<span id="topicModified" style="display: none">
<label>Previous Topic : </label><br><s:textarea name="topicModified" cols="60"></s:textarea><br>
<label>Date of Modification : </label><s:textfield name="dateOfTopicModification" cssClass="calender"></s:textfield><br>
</span>
<label>Languages known : </label><br><s:textfield name="languagesKnown" cols="60"></s:textfield><br>

<label>Supervisor's Name </label><br>
<select class="reportToInfo" name="supervisor.supervisorID"></select><input class="supervisorFetcher" value="Fetch Supervisors" type="button"/>
<br>

<label>Co-Supervisor's Name </label>
<select class="reportToInfo" name="coSupervisor.supervisorID"></select>
<br>   


<label>Total Experience : </label><br><s:textfield name="totalExperience" cols="60"></s:textfield><br>

<label>Details of previous examinations</label><br>
<table class="examsTable">
<thead><tr><th>Exam Passed</th><th>Name of university</th><th>Year of passing</th><th>Percentage</th><th>Subjects</th></tr></thead>
<tbody>
<tr><td><input type="text" value="UG"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
<tr><td><input type="text" value="PG"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
<tr><td><input type="text" value="M.Phil."/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
<tr><td><input type="text" value="Others"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
</tbody>
</table>
<s:textfield cssClass="hiddenElements" name="examsPassed"></s:textfield>
<s:textfield cssClass="hiddenElements" name="institutionsName"></s:textfield>
<s:textfield cssClass="hiddenElements" name="yearsOfPassing"></s:textfield>
<s:textfield cssClass="hiddenElements" name="percentages"></s:textfield>
<s:textfield cssClass="hiddenElements" name="subjects"></s:textfield>
<br>
<input type="button" value="Add Row" onclick="addRowToExamsTable()"/>&nbsp;&nbsp;<input type="button" value="Delete Row" onclick="deleteRowFromExamsTable()"/>
<br>
<label>(In case of exempted from the Entrance Test).</label><br>

<s:checkbox name="un">UGC NET </s:checkbox>
<span id="ugcnet" style="display: none">
<label>Roll No. : </label><s:textfield name="un_rno"></s:textfield><br>
<label>Examination Year  : </label><s:textfield name="un_xam_yr"></s:textfield><br>
<label>UGC Reference No.  : </label><s:textfield name="un_ref_no"></s:textfield><br>
<label>E- Certificate No.  : </label><s:textfield name="un_ecert_no"></s:textfield><br>
</span>

<s:checkbox name="sl">SLET  </s:checkbox>
<span id="slet" style="display: none">
<label>Roll No. : </label><s:textfield name="sl_rno"></s:textfield><br>
<label>Examination Year  : </label><s:textfield name="sl_xam_yr"></s:textfield><br>
<label>SLET Reference No.  : </label><s:textfield name="sl_ref_no"></s:textfield><br>
<label>E- Certificate No.  : </label><s:textfield name="sl_ecert_no"></s:textfield><br>
</span>

<s:checkbox name="cun">CSIR UGC NET </s:checkbox>
<span id="csir" style="display: none">
<label>Roll No. : </label><s:textfield name="cun_rno"></s:textfield><br>
<label>Examination Year  : </label><s:textfield name="cun_xam_yr"></s:textfield><br>
<label>CSIR UGC NET Reference No.  : </label><s:textfield name="cun_ref_no"></s:textfield><br>
<label>E- Certificate No.  : </label><s:textfield name="cun_ecert_no"></s:textfield><br>
</span>


<label>Employer Detail (if Employed) : </label><br>
<table class="employerTable">
<thead><tr><th>Name of employer</th><th>Position held</th><th>From</th><th>To</th></tr></thead>
<tbody><tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr></tbody>
</table>
<s:textfield cssClass="hiddenElements" name="employersname"></s:textfield>
<s:textfield cssClass="hiddenElements" name="positionsHeld"></s:textfield>
<s:textfield cssClass="hiddenElements" name="employedfrom"></s:textfield>
<s:textfield cssClass="hiddenElements" name="employedto"></s:textfield>
<br>
<input type="button" value="Add Row" onclick="addRowToEmployerTable()"/>&nbsp;&nbsp;<input type="button" value="Delete Row" onclick="deleteRowFromEmployerTable()"/>
<br>

<s:submit cssClass="submitButton" type="submit" value="Submit" ></s:submit>
</fieldset>
</s:form>
</div>
</div>

</body>
</html>
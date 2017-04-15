<%@ taglib prefix="s" uri="/struts-tags" %>
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
<s:form cssClass="submitToRegister" action="updatescholarsubmit" theme="simple"> 
<fieldset>
<legend>Sign Up </legend>
<s:hidden name="scholarID" value="%{#request.sch.scholarID}" ></s:hidden>
<s:textfield name="scholarFirstName" value="%{#request.sch.scholarFirstName}"></s:textfield><s:textfield name="scholarMiddleName" value="%{#request.sch.scholarMiddleName}"></s:textfield><s:textfield name="scholarLastName" value="%{#request.sch.scholarLastName}"></s:textfield><br>
<s:textfield name="scholarFatherFirstName" value="%{#request.sch.scholarFatherFirstName}"></s:textfield><s:textfield name="scholarFatherLastName" value="%{#request.sch.scholarFatherLastName}"></s:textfield><br>
<s:textfield name="scholarSpouseFirstName" value="%{#request.sch.scholarSpouseFirstName}"></s:textfield><s:textfield name="scholarSpouseLastName" value="%{#request.sch.scholarSpouseFirstName}"></s:textfield><br>
<label>Gender</label><br>
<s:if test="#request.sch.gender == 'Male'">
Male &nbsp;<input name="gender" type="radio" checked="checked" value="Male"/>&nbsp;&nbsp;&nbsp;Female&nbsp;<input name="gender" type="radio" value="Female"/><br>
</s:if><s:else>
Male &nbsp;<input name="gender" type="radio" value="Male"/>&nbsp;&nbsp;&nbsp;Female&nbsp;<input name="gender" type="radio" checked="checked" value="Female"/><br>
</s:else>

<label>Date of Birth</label><s:textfield name="dob" cssClass="calender" value="%{#request.sch.dob}"></s:textfield>
<label>Nationality</label><s:textfield name="nationality" value="%{#request.sch.nationality}"></s:textfield><br>
<label>Mobile Number : </label><s:textfield name="mobileNumber" value="%{#request.sch.mobileNumber}"></s:textfield>
<label>Landline Number : </label><s:textfield name="landlineNumber" value="%{#request.sch.landlineNumber}"></s:textfield><br>
<label>Email ID : </label><s:textfield name="emailID" value="%{#request.sch.emailID}"></s:textfield><br>
<label>Correspondence Address</label><br><s:textarea name="correspondenceAddress" value="%{#request.sch.correspondenceAddress}" cols="60"></s:textarea><br>
<label>State and District</label>

<s:doubleselect list="#request.statDistMap.keySet()" name="correspondenceAddressState.statename" doubleName="correspondenceAddressDistrict.districtid"  headerValue="%{#request.sch.correspondenceAddressState.statename}" headerKey="%{#request.sch.correspondenceAddressState.statename}"
 doubleList="#request.statDistMap[top]" doubleListKey="districtid" doubleListValue="districtname" 
doubleHeaderKey="#request.sch.correspondenceAddressDistrict.districtid" 
doubleHeaderValue="#request.sch.correspondenceAddressDistrict.districtname" theme="scholar"/>

<br>

<label>Pin Code : </label><s:textfield name="correspondenceAddressZipCode" value="%{#request.sch.correspondenceAddressZipCode}"></s:textfield><br>	

<label>Permanent Address</label><br><s:textarea name="permanentAddress" cols="60" value="%{#request.sch.permanentAddress}"></s:textarea><br>

<label>State and District</label>
<s:doubleselect list="#request.statDistMap.keySet()" name="permanentAddressState.statename" doubleName="permanentAddressDistrict.districtid" headerValue="%{#request.sch.permanentAddressState.statename}"  headerKey="%{#request.sch.permanentAddressState.statename}"
 doubleList="#request.statDistMap[top]" doubleListKey="districtid" doubleListValue="districtname" 
doubleHeaderKey="#request.sch.permanentAddressDistrict.districtid" 
doubleHeaderValue="#request.sch.permanentAddressDistrict.districtname"  theme="scholar"/>

<br>

<label>Pin Code : </label><s:textfield name="permanentAddressZipCode" value="%{#request.sch.permanentAddressZipCode}"></s:textfield><br>





<label>Course Name : </label><s:textfield name="coursename" value="%{#request.sch.coursename}"></s:textfield><br>


<s:if test="#session.user.role == 'SUPERADMIN'">

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sch.institute.instituteid}" headerValue="%{#request.sch.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid"  headerKey="%{#request.sch.faculty.facultyid}" headerValue="%{#request.sch.faculty.facultyname}" list="{}"></s:select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid"  headerKey="%{#request.sch.department.departmentid}" headerValue="%{#request.sch.department.departmentname}" list="{}"></s:select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:if>

<s:elseif test="#session.user.role == 'ADMIN'">

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sch.institute.instituteid}" headerValue="%{#request.sch.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid"  headerKey="%{#request.sch.faculty.facultyid}" headerValue="%{#request.sch.faculty.facultyname}"  list="{}"></s:select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid"   headerKey="%{#request.sch.department.departmentid}" headerValue="%{#request.sch.department.departmentname}"  list="{}"></s:select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:elseif>

<s:elseif test="#session.user.role == 'DEAN'">

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sch.institute.instituteid}" headerValue="%{#request.sch.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid" headerKey="%{#request.sch.faculty.facultyid}" headerValue="%{#request.sch.faculty.facultyname}" list="{}"></s:select><br>		


<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid" headerKey="%{#request.sch.department.departmentid}" headerValue="%{#request.sch.department.departmentname}" list="{}"></s:select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:elseif>

<s:else>

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sch.institute.instituteid}" headerValue="%{#request.sch.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid" headerKey="%{#request.sch.faculty.facultyid}" headerValue="%{#request.sch.faculty.facultyname}" list="{}"></s:select><br>		

<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid" headerKey="%{#request.sch.department.departmentid}" headerValue="%{#request.sch.department.departmentname}" list="{}"></s:select><br>		
</s:else>

<label>Category : </label><s:textfield name="category" value="%{#request.sch.category}"></s:textfield><br>
<label>Category Code : </label><s:textfield name="categorycode" value="%{#request.sch.categorycode}"></s:textfield><br>
<label>Enrollment Number( if any ) : </label><s:textfield name="enrollmentNumber"  value="%{#request.sch.enrollmentNumber}"></s:textfield><br>
<label>Date of Registration : </label><s:textfield name="dateOfRegistration" cssClass="calender" value="%{#request.sch.dateOfRegistration}"></s:textfield><br>
<label>Proposed Topic of Research : </label><br><s:textarea name="topic" cols="60" value="%{#request.sch.topic}"></s:textarea><br>
Is topic modified &nbsp;&nbsp;<input id="istopicmod" name="istopicmod" type="checkbox" />
<span id="topicModified" style="display: none">
<label>Previous Topic : </label><br><s:textarea name="topicModified" cols="60"  value="%{#request.sch.topicModified}"></s:textarea><br>
<label>Date of Modification : </label><s:textfield name="dateOfTopicModification" cssClass="calender"   value="%{#request.sch.dateOfTopicModification}"></s:textfield><br>
</span>
<label>Languages known : </label><br><s:textfield name="languagesKnown" cols="60" value="%{#request.sch.languagesKnown}"></s:textfield><br>

<label>Supervisor's Name </label><br>
<s:select cssClass="reportToInfo" name="supervisor.supervisorID" headerKey="%{#request.sch.supervisor.supervisorID}" headerValue="%{#request.sch.supervisor.supervisorFirstName}%{#request.sch.supervisor.supervisorMiddleName}%{#request.sch.supervisor.supervisorLastName}" list="{}"></s:select><input class="supervisorFetcher" value="Fetch Supervisors" type="button"/>
<br>

<label>Co-Supervisor's Name </label>
<s:select cssClass="reportToInfo" name="coSupervisor.supervisorID" headerKey="%{#request.sch.coSupervisor.supervisorID}" headerValue="%{#request.sch.coSupervisor.supervisorFirstName}%{#request.sch.coSupervisor.supervisorMiddleName}%{#request.sch.coSupervisor.supervisorLastName}" list="{}"></s:select>
<br>   


<label>Total Experience : </label><br><s:textfield name="totalExperience" cols="60"  value="%{#request.sch.totalExperience}"></s:textfield><br>

<label>Details of previous examinations</label><br>
<s:property value="#request.examsTable" escapeHtml="false"/>
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
<label>Roll No. : </label><s:textfield name="un_rno"  value="%{#request.sch.un_rno}"></s:textfield><br>
<label>Examination Year  : </label><s:textfield name="un_xam_yr" value="%{#request.sch.un_xam_yr}"></s:textfield><br>
<label>UGC Reference No.  : </label><s:textfield name="un_ref_no"  value="%{#request.sch.un_ref_no}"></s:textfield><br>
<label>E- Certificate No.  : </label><s:textfield name="un_ecert_no"  value="%{#request.sch.un_ecert_no}"></s:textfield><br>
</span>

<s:checkbox name="sl">SLET  </s:checkbox>
<span id="slet" style="display: none">
<label>Roll No. : </label><s:textfield name="sl_rno" value="%{#request.sch.sl_rno}"></s:textfield><br>
<label>Examination Year  : </label><s:textfield name="sl_xam_yr" value="%{#request.sch.sl_xam_yr}"></s:textfield><br>
<label>SLET Reference No.  : </label><s:textfield name="sl_ref_no" value="%{#request.sch.sl_ref_no}"></s:textfield><br>
<label>E- Certificate No.  : </label><s:textfield name="sl_ecert_no" value="%{#request.sch.sl_ecert_no}"></s:textfield><br>
</span>

<s:checkbox name="cun">CSIR UGC NET </s:checkbox>
<span id="csir" style="display: none">
<label>Roll No. : </label><s:textfield name="cun_rno" value="%{#request.sch.cun_rno}"></s:textfield><br>
<label>Examination Year  : </label><s:textfield name="cun_xam_yr" value="%{#request.sch.cun_xam_yr}"></s:textfield><br>
<label>CSIR UGC NET Reference No.  : </label><s:textfield name="cun_ref_no" value="%{#request.sch.cun_ref_no}"></s:textfield><br>
<label>E- Certificate No.  : </label><s:textfield name="cun_ecert_no" value="%{#request.sch.cun_ecert_no}"></s:textfield><br>
</span>


<label>Employer Detail (if Employed) : </label><br>
<s:property value="#request.employerTable"  escapeHtml="false"/>
<s:textfield cssClass="hiddenElements" name="employersname"></s:textfield>
<s:textfield cssClass="hiddenElements" name="positionsHeld"></s:textfield>
<s:textfield cssClass="hiddenElements" name="employedfrom"></s:textfield>
<s:textfield cssClass="hiddenElements" name="employedto"></s:textfield>
<br>
<input type="button" value="Add Row" onclick="addRowToEmployerTable()"/>&nbsp;&nbsp;<input type="button" value="Delete Row" onclick="deleteRowFromEmployerTable()"/>
<br>
<s:label value="Date Created : "/><s:textfield name="dateCreated" value="%{#request.sch.dateCreated}" readonly="true"></s:textfield><br>

<s:submit cssClass="submitButton" type="submit" value="Update Scholar" title="Update Scholar"></s:submit>
</fieldset>
</s:form>
<script type="text/javascript">
<!--
$(document).ready(function() {
	var dialogOpts = {
			autoOpen: false
			};
	var dialogProgOpts = {
	show: true,
	hide: true,
	autoOpen: false
	};
	$('#submitSuccess').dialog(dialogOpts);
	$('#submitError').dialog(dialogProgOpts);
	$('#submitFailure').dialog(dialogOpts);
	
});


function getDoc(frame) {
    var doc = null;

    // IE8 cascading access check
    try {
        if (frame.contentWindow) {
            doc = frame.contentWindow.document;
        }
    } catch(err) {
    }

    if (doc) { // successful getting content
        return doc;
    }

    try { // simply checking may throw in ie8 under ssl or mismatched protocol
        doc = frame.contentDocument ? frame.contentDocument : frame.document;
    } catch(err) {
        // last attempt
        doc = frame.document;
    }
    return doc;
}



$(document).ready(function() { 

	$("input[name='istopicmod']").click(function () {
	    $("#topicModified").toggle(this.checked);
	});
	$("input[name='un']").click(function () {
	    $("#ugcnet").toggle(this.checked);
	});
	$("input[name='sl']").click(function () {
	    $("#slet").toggle(this.checked);
	});
	$("input[name='cun']").click(function () {
	    $("#csir").toggle(this.checked);
	});
	
	$(".submitToRegister").submit(function(e){
		e.preventDefault();
		if(validateForm())
		{
		startSubmit();
   var formObj = $(this);
   var formURL = formObj.attr("action");

   if(window.FormData !== undefined)  // for HTML5 browsers
   {

       var formData = new FormData(this);
       $.ajax({
           url: formURL,
           type: 'POST',
           async:false,
           data:  formData,
           contentType:false,
           cache: false,
           processData:false,
           success: function(result)
           {
        	   if(!$("#submitSuccess").dialog("isOpen")) {
        		   $("#submitSuccess").dialog("open");
        		   } else {
        		   $("#submitSuccess").dialog("close");
        		   }
           },
           error: function(error)
           {
        	   if(!$("#submitFailure").dialog("isOpen")) {
        		   $("#submitFailure").dialog("open");
        		   } else {
        		   $("#submitFailure").dialog("close");
        		   }
           }

      });
       return false;
  }
  else  //for olden browsers
   {
       //generate a random id
       var  iframeId = 'unique' + (new Date().getTime());

       //create an empty iframe
       var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');

       //hide it
       iframe.hide();

       //set form target to iframe
       formObj.attr('target',iframeId);

       //Add iframe to body
       iframe.appendTo('body');
       iframe.load(function()
       {
           var doc = getDoc(iframe[0]);
           var docRoot = doc.body ? doc.body : doc.documentElement;
           var data = docRoot.innerHTML;
           //data is returned from server.

       });

   }
	}
		return false;
});
	
	$(".facultyFetcher").click(function(){
		var insId = $(".instituteInfo").val();
		$.get('http://localhost:8080/PHDWebApp/invoke?object=faculty&operation=fetch&input='+insId+'@',
				appendOptionToFaculty);

	});


	$(".departmentFetcher").click(function(){
		var facId = $(".facultyInfo").val();
		$.get('http://localhost:8080/PHDWebApp/invoke?object=department&operation=fetch&input='+facId+'@',
				appendOptionToDepartment);

	});
	$(".supervisorFetcher").click(function(){
		var insId = $(".instituteInfo").val();
		var facId = $(".facultyInfo").val();
		var depId = $(".departmentInfo").val();
		$.get('http://localhost:8080/PHDWebApp/invoke?object=supervisor&operation=fetch&input='+insId+'@'+facId+'@'+depId+'@',
				appendOptionToReportTo);

	});
	
	$( ".calender" ).datepicker({
		dateFormat: "dd-mm-yy",
		changeMonth: true,
		changeYear: true
	});
	
});
//$(".monthlyFormToSubmit").submit();

//=======================================
function validateForm() {
	var err = false;
	var fn = $('input[name="scholarFirstName"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else{
		if(checkForSpecialChars(fn.val()))
			fn.css("border-color", "red");
		else
		fn.css("border-color", "white");
		
	}
	var fn = $('input[name="scholarLastName"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else{
		if(checkForSpecialChars(fn.val()))
			fn.css("border-color", "red");
		else
		fn.css("border-color", "white");
		
	}
	var fn = $('input[name="scholarFatherFirstName"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else{
		if(checkForSpecialChars(fn.val()))
			fn.css("border-color", "red");
		else
		fn.css("border-color", "white");
		
	}
	var fn = $('input[name="scholarFatherLastName"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} {
		if(checkForSpecialChars(fn.val()))
			fn.css("border-color", "red");
		else
		fn.css("border-color", "white");
		
	}
	var fn = $('input[name="dob"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('input[name="mobileNumber"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else{
		if(checkForIntegers(fn.val()))
			fn.css("border-color", "red");
		else
		fn.css("border-color", "white");
		
	}
	var fn = $('input[name="emailID"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else{
		if(!isValidEmailAddress(fn.val()))
			fn.css("border-color", "red");
		else
		fn.css("border-color", "white");
		
	}
	var fn = $('select[name="institute.instituteid"]');
	if (fn.val() == -1) {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('select[name="faculty.facultyid"]');
	if (fn.val() == null) {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('select[name="department.departmentid"]');
	if (fn.val() == null) {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('select[name="supervisor.supervisorID"]');
	if (fn.val() == null) {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('select[name="coSupervisor.supervisorID"]');
	if (fn.val() == null) {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('input[name="dateOfRegistration"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");
	var fn = $('textarea[name="topic"]');
	if (fn.val() == "") {
		fn.css("border-color", "red");
		err = true;
	} else
		fn.css("border-color", "white");

	if (!$("#submitError").dialog("isOpen") && err == true) {
		$("#submitError").dialog("open");
	} else {
		$("#submitError").dialog("close");
	}
	return !err;
}
function checkForSpecialChars(value){
	if(/^[a-zA-Z0-9- ]*$/.test(value) == false) {
	    return true;
	}
	return false;
}

function checkForIntegers(value){
	if(/^[0-9- ]*$/.test(value) == false) {
	    return true;
	}
	return false;
}

function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
    return pattern.test(emailAddress);
}
function startSubmit(){
	var eName = "";
	var tdLen = $(".examsTable thead tr th").length;
	var trLen = $(".examsTable tbody tr").length;
	for(var i = 1; i <= tdLen; i++){
	var selector = ".examsTable tbody tr td:nth-child("+i+") input";
	$(selector).each(function(){ var $this = $(this);eName = eName + $this.val()+'@'; });
	if(i===1){
		$('input[name="examsPassed"]').val(eName);
	}else if(i===2){
		$('input[name="institutionsName"]').val(eName);
	}else if(i===3){
		$('input[name="yearsOfPassing"]').val(eName);
	}else if(i===4){
		$('input[name="percentages"]').val(eName);
	}else if(i===5){
		$('input[name="subjects"]').val(eName);
	}
	eName="";
	}
	
	var tdLen1 = $(".employerTable thead tr th").length;
	var trLen1 = $(".employerTable tbody tr").length;
	for(var i = 1; i <= tdLen1; i++){
	var selector = ".employerTable tbody tr td:nth-child("+i+") input";
	$(selector).each(function(){ var $this = $(this);eName = eName + $this.val()+'@'; });
	if(i===1){
		$('input[name="employersname"]').val(eName);
	}else if(i===2){
		$('input[name="positionsHeld"]').val(eName);
	}else if(i===3){
		$('input[name="employedfrom"]').val(eName);
	}else if(i===4){
		$('input[name="employedto"]').val(eName);
	}
	eName="";
	}
	
	alert('Exams Passed'+$('input[name="examsPassed"]').val());
	alert('Employer Name'+$('input[name="employersname"]').val());
	
	}



function appendOptionToFaculty(xml) {
	 $(".facultyInfo").html("");
	$(xml).find('option').each(function(){
	      var sTitle = $(this).find('value').text();
	      var sPublisher = $(this).find('text').text();
	      var temp = "<option value="+sTitle+"></option>";
	      $(temp).html(sPublisher).appendTo(".facultyInfo");
});
}

function appendOptionToDepartment(xml) {
	 $(".departmentInfo").html("");
	$(xml).find('option').each(function(){
	      var sTitle = $(this).find('value').text();
	      var sPublisher = $(this).find('text').text();
	      var temp = "<option value="+sTitle+"></option>";
	      $(temp).html(sPublisher).appendTo(".departmentInfo");
});
}
function appendOptionToReportTo(xml) {
	$(xml).find('option').each(function(){
	      var sTitle = $(this).find('value').text();
	      var sPublisher = $(this).find('text').text();
	      var temp = "<option value="+sTitle+"></option>";
	      $(temp).html(sPublisher).appendTo(".reportToInfo");
});
	}
function addRowToExamsTable(){
	$(".examsTable tr:last").after("<tr><td><input type='text'/></td><td><input type='text'/></td><td><input type='text'/></td><td><input type='text'/></td><td><input type='text'/></td></tr>");
	}
function deleteRowFromExamsTable(){
	$(".examsTable tr:last").remove();
	}


function addRowToEmployerTable(){
	$(".employerTable tr:last").after("<tr><td><input type='text'/></td><td><input type='text'/></td><td><input type='text'/></td><td><input type='text'/></td></tr>");
	}
function deleteRowFromEmployerTable(){
	$(".employerTable tr:last").remove();
	}


//-->
</script>

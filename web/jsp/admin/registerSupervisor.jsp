<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style> .errorMessage { color:black; }</style>
<div id="submitSuccess" title="Success">
Supervisor added successfully.
</div>
<div id="submitFailure" title="Failure">
Error while adding Supervisor.
</div>
<div id="submitError" title="Error">
Error in form. Correct the values in Red.
</div>
<script type="text/javascript">
<!--

$(document).ready(function() {

	var dialogOpts = {
			autoOpen : false
		};
	$('#submitSuccess').dialog(dialogOpts);
	$('#submitError').dialog(dialogOpts);
	$('#submitFailure').dialog(dialogOpts);
	
		$( ".calender" ).datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true
		});
	$(".addSupervisorToRegister").submit( function(e){
		e.preventDefault();
	if(validateForm())
	{
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
});
	
	function validateForm() {
		var err = false;
		var fn = $('input[name="supervisorFirstName"]');
		if (fn.val() == "") {
			fn.css("border-color", "red");
			err = true;
		} else{
			if(checkForSpecialChars(fn.val()))
				fn.css("border-color", "red");
			else
			fn.css("border-color", "white");
			
		}
		var fn = $('input[name="supervisorLastName"]');
		if (fn.val() == "") {
			fn.css("border-color", "red");
			err = true;
		} else{
			if(checkForSpecialChars(fn.val()))
				fn.css("border-color", "red");
			else
			fn.css("border-color", "white");
			
		}
		var fn = $('input[name="supervisorFatherFirstName"]');
		if (fn.val() == "") {
			fn.css("border-color", "red");
			err = true;
		} else{
			if(checkForSpecialChars(fn.val()))
				fn.css("border-color", "red");
			else
			fn.css("border-color", "white");
			
		}
		var fn = $('input[name="supervisorFatherLastName"]');
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
		
		var fn = $('select[name="role"]');
		if (fn.val() == -1) {
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
		 $(".reportToInfo").html("");
		$(xml).find('option').each(function(){
		      var sTitle = $(this).find('value').text();
		      var sPublisher = $(this).find('text').text();
		      var temp = "<option value="+sTitle+"></option>";
		      $(temp).html(sPublisher).appendTo(".reportToInfo");
	});
	
	
	}
	});



//-->
</script>

<s:fielderror/>
<s:form cssClass="addSupervisorToRegister" method="POST" action="registerSupervisorSubmit" theme="simple"> 
<fieldset>
<legend>Supervisor Registration Form</legend><br><br>
<s:label key="label.supervisor.name" for="fName"/>
<s:select name="salutation" list="{'Prof.','Dr.','Mr.','Mrs.','Ms.'}"/><br><s:textfield label="First Name" name="supervisorFirstName" value="First Name"></s:textfield><s:textfield label="Middle Name" name="supervisorMiddleName" value="Middle Name"></s:textfield><s:textfield label="Last Name" name="supervisorLastName" value="Last Name"></s:textfield><br>
<s:label key="label.supervisor.father" for="ffName"/>
<s:textfield label="First Name" name="supervisorFatherFirstName" value="First Name"></s:textfield><s:textfield label="Last Name" name="supervisorFatherLastName" value="Last Name"></s:textfield><br>
<s:label key="label.supervisor.spouse" for="mfName"/>
<s:textfield label="First Name" name="supervisorSpouseFirstName" value="First Name"></s:textfield><s:textfield label="Last Name" name="supervisorSpouseLastName" value="Last Name"></s:textfield><br>
<s:label value="Date of Birth"/><s:textfield name="dob" cssClass="calender"></s:textfield><br>
<s:label value="Mobile Number : "/><s:textfield name="mobileNumber"></s:textfield><br>
<s:label value="Landline Number : "/><s:textfield name="landlineNumber"></s:textfield><br>
<s:label value="Email ID : "/><s:textfield name="emailID"></s:textfield><br>
<s:label value="Correspondence Address"/><br><s:textarea name="correspondenceAddress" cols="60"></s:textarea><br>
<s:debug/>
<label>State and District</label>
<s:doubleselect list="#session.statDistMap.keySet()" name="correspondenceAddressState.statename" doubleName="correspondenceAddressDistrict.districtid"
 doubleList="#session.statDistMap[top]" doubleListKey="districtid" doubleListValue="districtname"/>

<br>

<label>Pin Code : </label><s:textfield name="correspondenceAddressZipCode"></s:textfield><br>	

<s:label value="Permanent Address"/><br><s:textarea name="permanentAddress" cols="60"></s:textarea><br>

<label>State and District</label>
<s:doubleselect list="#session.statDistMap.keySet()" name="permanentAddressState.statename" doubleName="permanentAddressDistrict.districtid"
 doubleList="#session.statDistMap[top]" doubleListKey="districtid" doubleListValue="districtname"/>

<br>

<label>Pin Code : </label><s:textfield name="permanentAddressZipCode"></s:textfield><br>
<s:if test="#session.user.role == 'SUPERADMIN'">

<s:select cssClass="instituteInfo" list="#session.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="-1" headerValue="Select Institute"></s:select>
<br>

<label>Faculty : </label>
<select class="facultyInfo" name="faculty.facultyid"></select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<select class="departmentInfo" name="department.departmentid"></select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:if>

<s:elseif test="#session.user.role == 'ADMIN'">

<s:select cssClass="instituteInfo" list="#session.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename"></s:select>
<br>

<label>Faculty : </label>
<select class="facultyInfo" name="faculty.facultyid"></select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<select class="departmentInfo" name="department.departmentid"></select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:elseif>

<s:elseif test="#session.user.role == 'DEAN'">

<s:select cssClass="instituteInfo" list="#session.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename"></s:select>
<br>

<label>Faculty : </label>
<select class="facultyInfo" name="faculty.facultyid"><option value='<s:property value="#session.user.faculty.facultyid"/>'><s:property value="#session.user.faculty.facultyname"/></option></select><br>		


<label>Department : </label>
<select class="departmentInfo" name="department.departmentid"></select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:elseif>

<s:else>

<s:select cssClass="instituteInfo" list="#session.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename"></s:select>
<br>

<label>Faculty : </label>
<select class="facultyInfo" name="faculty.facultyid"><option value='<s:property value="#session.user.faculty.facultyid"/>'><s:property value="#session.user.faculty.facultyname"/></option></select><br>		

<label>Department : </label>
<select class="departmentInfo" name="department.departmentid"><option value='<s:property value="#session.user.department.departmentid"/>'><s:property value="#session.user.department.departmentname"/></option></select><br>		
</s:else>

<label>Designation : </label><s:select name="designation" list="{'Professor','Assistant Professor','Associate Professor','Reader'}"/>

<label>Role : </label>
<s:select label="Choose role " 
		headerKey="-1" headerValue="Select role ..."
		list="#session.roles" 
		name="role" />

<br>
<s:submit cssClass="submitButton" type="submit" value="Submit"></s:submit>
</fieldset>
</s:form>

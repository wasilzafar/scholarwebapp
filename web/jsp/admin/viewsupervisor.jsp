<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="submitSuccess" title="Success">
Supervisor updated	successfully.
</div>
<div id="submitFailure" title="Failure">
Error while updating supervisor.
</div>
<div id="submitError" title="Error">
Error in form. Correct the values in Red.
</div>


<s:form cssClass="updateSupervisorToRegister" method="POST" action="updatesupervisorsubmit" theme="simple"> 
<fieldset>
<legend>Supervisor Registration Form</legend><br>
<s:hidden name="supervisorID" value="%{#request.sup.supervisorID}"></s:hidden><br>
<s:label key="label.supervisor.name" for="fName"/>
<s:select name="salutation" list="{'Prof.','Dr.','Mr.','Mrs.','Ms.'}" headerValue="%{#request.sup.salutation}"/><br><s:textfield label="First Name" name="supervisorFirstName" value="%{#request.sup.supervisorFirstName}"></s:textfield><s:textfield label="Middle Name" name="supervisorMiddleName" value="%{#request.sup.supervisorMiddleName}"></s:textfield><s:textfield label="Last Name" name="supervisorLastName" value="%{#request.sup.supervisorLastName}"></s:textfield><br>
<s:label key="label.supervisor.father" for="ffName"/>
<s:textfield label="First Name" name="supervisorFatherFirstName" value="%{#request.sup.supervisorFatherFirstName}"></s:textfield><s:textfield label="Last Name" name="supervisorFatherLastName" value="%{#request.sup.supervisorFatherLastName}"></s:textfield><br>
<s:label key="label.supervisor.spouse" for="mfName"/>
<s:textfield label="First Name" name="supervisorSpouseFirstName" value="%{#request.sup.supervisorSpouseFirstName}"></s:textfield><s:textfield label="Last Name" name="supervisorSpouseLastName" value="%{#request.sup.supervisorSpouseLastName}"></s:textfield><br>
<s:label value="Date of Birth"/><s:textfield name="dob" value="%{#request.sup.dob}" cssClass="calender"></s:textfield><br>
<s:label value="Mobile Number : "/><s:textfield name="mobileNumber" value="%{#request.sup.mobileNumber}"></s:textfield><br>
<s:label value="Landline Number : "/><s:textfield name="landlineNumber" value="%{#request.sup.landlineNumber}"></s:textfield><br>
<s:label value="Email ID : "/><s:textfield name="emailID" value="%{#request.sup.emailID}"></s:textfield><br>
<s:label value="Correspondence Address"/><br><s:textarea name="correspondenceAddress" cols="60" value="%{#request.sup.correspondenceAddress}"></s:textarea><br>
<label>State and District</label>
<s:doubleselect list="#request.statDistMap.keySet()" name="correspondenceAddressState.statename" headerValue="%{#request.sup.correspondenceAddressState.statename}" headerKey="%{#request.sup.correspondenceAddressState.statename}" 
doubleName="correspondenceAddressDistrict.districtid" doubleList="#request.statDistMap[top]" 
doubleListKey="districtid" doubleListValue="districtname" 
doubleHeaderKey="#request.sup.correspondenceAddressDistrict.districtid" 
doubleHeaderValue="#request.sup.correspondenceAddressDistrict.districtname" theme="scholar"/>

<br>

<label>Pin Code : </label><s:textfield name="correspondenceAddressZipCode" value="%{#request.sup.correspondenceAddressZipCode}"></s:textfield><br>	

<s:label value="Permanent Address"/><br><s:textarea name="permanentAddress" cols="60" value="%{#request.sup.permanentAddress}"></s:textarea><br>

<label>State and District</label>
<s:doubleselect list="#request.statDistMap.keySet()" name="permanentAddressState.statename" headerValue="%{#request.sup.permanentAddressState.statename}"  headerKey="%{#request.sup.permanentAddressState.statename}" 
doubleName="permanentAddressDistrict.districtid" doubleList="#request.statDistMap[top]" 
doubleListKey="districtid" doubleListValue="districtname" 
doubleHeaderKey="#request.sup.permanentAddressDistrict.districtid" 
doubleHeaderValue="#request.sup.permanentAddressDistrict.districtname"  theme="scholar"/>

<br>

<label>Pin Code : </label><s:textfield name="permanentAddressZipCode" value="%{#request.sup.permanentAddressZipCode}"></s:textfield><br>
<s:if test="#session.user.role == 'SUPERADMIN'">

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sup.institute.instituteid}" headerValue="%{#request.sup.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid"  headerKey="%{#request.sup.faculty.facultyid}" headerValue="%{#request.sup.faculty.facultyname}" list="{}"></s:select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid"  headerKey="%{#request.sup.department.departmentid}" headerValue="%{#request.sup.department.departmentname}" list="{}"></s:select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:if>

<s:elseif test="#session.user.role == 'ADMIN'">

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sup.institute.instituteid}" headerValue="%{#request.sup.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid"  headerKey="%{#request.sup.faculty.facultyid}" headerValue="%{#request.sup.faculty.facultyname}"  list="{}"></s:select> <input class="facultyFetcher" value="Fetch Faculties" type="button"/><br>		


<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid"   headerKey="%{#request.sup.department.departmentid}" headerValue="%{#request.sup.department.departmentname}"  list="{}"></s:select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:elseif>

<s:elseif test="#session.user.role == 'DEAN'">

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sup.institute.instituteid}" headerValue="%{#request.sup.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid" headerKey="%{#request.sup.faculty.facultyid}" headerValue="%{#request.sup.faculty.facultyname}" list="{}"></s:select><br>		


<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid" headerKey="%{#request.sup.department.departmentid}" headerValue="%{#request.sup.department.departmentname}" list="{}"></s:select> <input class="departmentFetcher" value="Fetch Departments" type="button"/><br>		
</s:elseif>

<s:else>

<label>Institute : </label>
<s:select cssClass="instituteInfo" list="#request.institutes" name="institute.instituteid" listKey="instituteid" listValue="institutename" headerKey="%{#request.sup.institute.instituteid}" headerValue="%{#request.sup.institute.institutename}"></s:select>
<br>

<label>Faculty : </label>
<s:select cssClass="facultyInfo" name="faculty.facultyid" headerKey="%{#request.sup.faculty.facultyid}" headerValue="%{#request.sup.faculty.facultyname}" list="{}"></s:select><br>		

<label>Department : </label>
<s:select cssClass="departmentInfo" name="department.departmentid" headerKey="%{#request.sup.department.departmentid}" headerValue="%{#request.sup.department.departmentname}" list="{}"></s:select><br>		
</s:else>

<label>Designation : </label><s:select name="designation" list="{'Professor','Assistant Professor','Associate Professor','Reader'}" headerValue="%{#request.sup.designation}"/>

<label>Role : </label>
<s:select label="Choose role " 
		headerKey="-1" headerValue="%{#request.sup.role}"
		list="#request.roles" 
		name="role" />

<br><br>
<s:label value="Date Created : "/><s:textfield name="dateCreated" value="%{#request.sup.dateCreated}" readonly="true"></s:textfield><br>
<s:submit cssClass="submitButton" type="submit" title="Update Supervisor"
			value="Update Supervisor"></s:submit>
</fieldset>
</s:form>

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
	$(".updateSupervisorToRegister").submit( function(e){
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
	});



//-->
</script>
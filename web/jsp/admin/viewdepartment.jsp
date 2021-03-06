<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="submitSuccess" title="Success">
Department updated	successfully.
</div>
<div id="submitFailure" title="Failure">
Error while updating department.
</div>
<div id="submitError" title="Error">
Error in form. Correct the values in Red.
</div>


<s:form cssClass="updateDepartmentToSubmit" action="updatedepartmentsubmit"
	theme="simple" method="POST">
	<fieldset>
		<legend>Update Department</legend>
		<br>

		<s:doubleselect list="#request.institutesfacultiesmap.keySet()" headerValue="%{#request.dep.institute.institutename}" 
			name="faculty.institute.institutename" doubleName="faculty.facultyid" doubleHeaderValue="%{request.dep.faculty.facultyname}"
			doubleList="#request.institutesfacultiesmap[top]"
			doubleListKey="facultyid" doubleListValue="facultyname" />

		<br> <label>Department Name : </label><s:textfield
			name="departmentname" value="%{#request.dep.departmentname}"></s:textfield>
			
			<br><s:textfield name="departmentid" cssStyle="display:none" value="%{#request.dep.departmentid}"></s:textfield>

		<s:submit cssClass="submitButton" title="Update Department"
			value="Update Department"></s:submit>
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
	$(".updateDepartmentToSubmit").submit( function(e){
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
		var fn = $('select[name="faculty.institute.institutename"]');
		if (fn.val() == -1) {
			fn.css("border-color", "red");
			err = true;
		} else{
			if (checkForSpecialChars(fn.val()))
				fn.css("border-color", "red");
			else
				fn.css("border-color", "white");

		}

		var fn = $('select[name="faculty.facultyid"]');
		if (fn.val() == -1) {
			fn.css("border-color", "red");
			err = true;
		} else
			fn.css("border-color", "white");
		
		var fn = $('input[name="departmentname"]');
		if (fn.val() == "") {
			fn.css("border-color", "red");
			err = true;
		} else {
			if (checkForSpecialChars(fn.val()))
				fn.css("border-color", "red");
			else
				fn.css("border-color", "white");

		}

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
	

	});



//-->
</script>
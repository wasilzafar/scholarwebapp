<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style> .errorMessage { color:black; }</style>
<div id="submitSuccess" title="Success">Faculty added
	successfully.</div>
<div id="submitFailure" title="Failure">Error while adding
	Faculty.</div>
<div id="submitError" title="Error">Error in form. Correct the
	values in Red.</div>
<s:fielderror />
<s:form cssClass="addFacultyToSubmit" action="addfacultysubmit"
	theme="simple" method="POST">
	<fieldset>
		<legend>Add Faculty</legend>
		<br>
		<s:select label="Choose institute " headerKey="-1"
			headerValue="Select institute ..." listKey="instituteid"
			listValue="institutename" list="#session.institutes"
			name="institute.instituteid" />
		<br> <label>Faculty Name : </label><input name="facultyname"
			type="text"></input>
		<s:submit cssClass="submitButton" title="Add Faculty"
			value="Add Faculty"></s:submit>
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
	$(".addFacultyToSubmit").submit( function(e){
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
		var fn = $('input[name="facultyname"]');
		if (fn.val() == "") {
			fn.css("border-color", "red");
			err = true;
		} else {
			if (checkForSpecialChars(fn.val()))
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


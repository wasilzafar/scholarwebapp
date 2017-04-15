<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="submitSuccess" title="Success">
Institute updated	successfully.
</div>
<div id="submitFailure" title="Failure">
Error while updating Institute.
</div>
<div id="submitError" title="Error">
Error in form. Correct the values in Red.
</div>

<s:form cssClass="updateInstitutionToSubmit" action="updateinstitutesubmit" method="POST" theme="simple">
	<fieldset>
		<legend>Update Institute</legend>
		<br> <label>Institute Name : </label><s:textfield name="institutename" value="%{#request.ins.institutename}"></s:textfield>
		<br><s:textfield name="instituteid" cssStyle="display:none" value="%{#request.ins.instituteid}"></s:textfield>
		<s:submit cssClass="submitButton" title="Update Institute" value="Update Institute"></s:submit>
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
	$(".updateInstitutionToSubmit").submit( function(e){
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
           success: function(data, status, xhr)
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
		var fn = $('input[name="institute.institutename"]');
		if (fn.val() == "") {
			fn.css("border-color", "red");
			err = true;
		} else{
			if(checkForSpecialChars(fn.val()))
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
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


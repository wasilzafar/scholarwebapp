<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style> .errorMessage { color:black; }
#coll {
border: 1px solid #d0d2d5;
    padding : 20px;
    width : 100%
}
table{ 
     no-wrap : true;
	 width:100%;
	 border-style:solid;
	 border-width:2px;
	 border-color: green;
}
table td,th {
white-space : nowrap;
font-size:1em;
border:1px solid #98bf21;
padding: 12px 7px 12px 7px;
}
</style>
<div id="submitSuccess" title="Success">
Colloquium scheduled successfully.
</div>
<div id="submitFailure" title="Failure">
Error while scheduling colloquium.
</div>
<script src="././js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript">
<!--

$(function() {

	$( "#autocomplete" ).autocomplete(
	{
		source: function(request, response) {
			$.getJSON("http://localhost:8080/PHDWebApp/collScholar", {
				q: request.term
			}, function(data) {
				// data is an array of objects and must be transformed for autocomplete to use
				var array = data.error ? [] : $.map(data.scholars, function(m) {
					return {
						label: m.scholarname + " (" + m.supervisorname + ")"+ " (" + m.cosupervisorname + ")",
						value: m.id,
						supervisor: m.supervisorname,
						cosupervisor: m.cosupervisorname,
						topic : m.topic
					};
				});
				response(array);
			});
		},
		focus: function(event, ui) {
			// prevent autocomplete from updating the textbox
			event.preventDefault();
			// manually update the textbox
			$(this).val(ui.item.label);
		},
		select: function(event, ui) {
			// prevent autocomplete from updating the textbox
			event.preventDefault();
			// manually update the textbox and hidden field
			$(this).val(ui.item.label);
			$("#supervisor").val(ui.item.supervisor);
			$("#cosupervisor").val(ui.item.cosupervisor);
			$("#topic").val(ui.item.topic);
			$("#scholar").val(ui.item.value);
		}
	});
	
	$('#datetime').datetimepicker({
		dateFormat: "dd-mm-yy",
		timeFormat: "hh:mm TT"
	});
});
$(document).ready(function() { 
	var dialogOpts = {
			autoOpen : false
		};
	$('#submitSuccess').dialog(dialogOpts);
	$('#submitFailure').dialog(dialogOpts);
	
	$(".addColloquiumToSubmit").submit(function(){

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
  else  //for older browsers
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

});
}

);
function duplicateColl(){
	$( "#coll" ).clone().appendTo("#collMore");
}

	function addRowToScholarTable() {
		var schid = $("#scholar").val();
		var sch = $("#autocomplete").val();
		var sup = $("#supervisor").val();
		if (!(sch == "") && !(sup == "")) {
			$("#schTable tr:last").after(
					"<tr><td>" + $("#autocomplete").val() + "</td><td>"
							+ $("#supervisor").val() + "</td><td>"
							+ $("#cosupervisor").val() + "</td><td>"
							+ $("#topic").val() + "</td><td style='display:none'><input type='text' name='scholarID' value='"+schid+"'></input></td></tr>");
			$("#autocomplete").val("");
			$("#supervisor").val("");
			$("#cosupervisor").val("");
			$("#topic").val("");
		}
	}
	function deleteRowFromScholarTable() {
		$("#schTable tr:last").remove();
	}
//-->
</script>
<s:fielderror/>
<s:form cssClass="addColloquiumToSubmit" action="newcolloquiumsubmit" theme="simple">
<fieldset>
<legend>Colloquium</legend><br>
<div id="coll">
<label>Name of Observer : </label><input name="observerName" type="text"></input><br>
<label>Date and Time : </label><input id="datetime" name="datetime" type="text"></input><br><br>
<label>Notify Supervisors : </label><s:checkbox name="notifySupervisors"></s:checkbox><br><br>
<label>Additional Notifications (Email IDs separated by comma): </label><br><textarea name="additionalNotification"></textarea><br><br>
<table id="schTable">
<thead>
<tr>
<th>Scholar</th>
<th>Supervisor</th>
<th>Co-Supervisor</th>
<th>Topic of Study</th>
<th style="display: none"></th>
</tr>
</thead>
<tbody class="schTablemainBody">
</tbody>
</table>
<br><br>
<button type="button" onclick="deleteRowFromScholarTable()">Remove</button><br><br>
</div>
<br><br>
<s:submit cssClass="submitButton" title="Submit" value="Submit"></s:submit>
</fieldset>
</s:form>
<s:form>
<fieldset>
<legend>Find Scholar</legend><br>
<label>Name of Scholar : </label><input id="autocomplete" name="scholar" type="text"></input><br>
<label>Name of Supervisor : </label><input id="supervisor" name="supervisor" type="text"></input><br>
<label>Name of Co-Supervisor : </label><input id="cosupervisor" name="cosupervisor" type="text"></input><br>
<label>Topic of Study : </label><textarea id="topic" name="topic"></textarea><br><br><br>
<input id="scholar" name="scholarID" hidden="true" type="text"></input><br><br><br>
<button type="button" onclick="addRowToScholarTable()">Add</button><br><br>
</fieldset>
</s:form>


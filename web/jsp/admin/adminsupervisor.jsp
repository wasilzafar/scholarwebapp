<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
<!--
table{ 
     no-wrap : true;
	 width:100%;
	 border-style:solid;
	 border-width:5px;
	 border-color: green;
}
table td,th {
white-space : nowrap;
font-size:1em;
border:2px solid #98bf21;
padding: 12px 7px 12px 7px;
}
table tr td input {
margin : 0;
padding :0
}
table tr th input[type=checkbox] {
margin : 0;
padding :0
}
table tr td input[type=checkbox] {
margin : 0;
padding :0
}
input[type=button] {
    font-weight: bold;
    padding: 5px 10px;
    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.6);
    }
input[type=button]:hover {
    background: grey;
    cursor : Pointer
    }
input[type=button]:active {
    background: #333942;
    }
-->
</style>
<script type="text/javascript">
<!--
function dphandler(operation) {
	var count = 0;
	$(".mainBody tr").each(function(){
			if($(this).find('td input[type="checkbox"]').prop('checked') === true){
				count = count + 1;
			}
		});
	if(count > 1){
	alert('Please select only one at a atime. No of selected items : '+ count);
	return;
	}
	if (operation === 'supervisor'){
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=supervisor&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,showPanel);
	}
}
function showPanel(returnData){
	var h = window.innerHeight;
	var w = window.innerWidth;
$("#showpanel").css("top",h/4);
$("#showpanel").css("overflow","scroll");
$("#showpanel").css("left",w/6);
$("#showpanel").css("width",(2*w)/3);
$("#showpanel").css("height",h/2);
$("#showpanel").css("background-color","white");
$("#hideBg").css("width",w);
$("#hideBg").css("height",h);
$("#hideBg").css("margin",0);
$("#hideBg").css("padding",0);
$("#hideBg").css("opacity",0.5);
$("#hideBg").css("background-color","white");
$("#hideBg").css("z-index",100);
$("#showpanel").css("z-index",200);
$("#hideBg").show();
$("#showpanel").html(returnData);
$(".updateInstitutionToSubmit").css("width",(5*w)/8);
$(".updateInstitutionToSubmit").css("position","relative");
$(".updateInstitutionToSubmit").css("left",w/16);
$(".updateInstitutionToSubmit").css("top",h/15);
$(".updateInstitutionToSubmit").css("margin",0);
$(".updateInstitutionToSubmit").css("padding",0);
$(".updateInstitutionToSubmit fieldset").css("position","relative");
$(".updateInstitutionToSubmit fieldset").css("width",w/4);
$(".updateInstitutionToSubmit fieldset").css("left",w/8);
$(".updateInstitutionToSubmit fieldset").css("margin",0);
$(".updateInstitutionToSubmit fieldset").css("padding",0);
$(".updateInstitutionToSubmit fieldset label").css("width",w/8);
$(".updateInstitutionToSubmit fieldset input").css("width",w/8);
$(".updateInstitutionToSubmit fieldset select").css("width",w/8);
$("#showpanel").show();
}
//-->
</script>
<table id="main">
<thead>
<tr align="center">
<th><input style="width: 20%" type="checkbox" onclick="checkAll()"></th>
<th>Supervisor</th>
<th>Institute</th>
<th>Faculty</th>
<th>Department</th>
<th>Status</th>
<th>Role</th>
<th style="display: none"></th>
</tr>
</thead>
<tbody class="mainBody">
<s:iterator value="#session.supervisors" status="maprowStatus">
  <tr align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><input style="width: 20%" type="checkbox"/></td>
      <td style="background: grey"><s:property value="supervisorFirstName"/><s:property value="supervisorMiddleName"/><s:property value="supervisorLastName"/></td>
      <td style="background: grey"><s:property value="institute.institutename"/></td>
      <td style="background: grey"><s:property value="faculty.facultyname"/></td>
      <td style="background: grey"><s:property value="department.departmentname"/></td>
      <td style="background: grey"><s:property value="status"/> </td>
      <td style="background: grey"><s:property value="role"/></td>
      <td style="display : none" id="id"><s:property value="supervisorID"/></td>
    </s:if>
    <s:else>
      <td><input style="width: 20%" type="checkbox"/></td>
      <td><s:property value="supervisorFirstName"/><s:property value="supervisorMiddleName"/><s:property value="supervisorLastName"/></td>
      <td><s:property value="institute.institutename"/></td>
      <td><s:property value="faculty.facultyname"/></td>
      <td><s:property value="department.departmentname"/></td>
      <td><s:property value="status"/> </td>
      <td><s:property value="role"/></td>
      <td style="display : none" id="id"><s:property value="supervisorID"/></td>
    </s:else>
  </tr>
</s:iterator>
</tbody>
</table>
<table>
<tr align="center"><td ><input value="VIEW" type="button" onclick="dphandler('supervisor')"/></td><td ><input value="NEW" type="button"  onclick="clickhandler('newsupervisor')"/></td><td ><input value="DELETE" type="button" onclick="clickhandler('deletesupervisor')"/></td></tr>
</table>


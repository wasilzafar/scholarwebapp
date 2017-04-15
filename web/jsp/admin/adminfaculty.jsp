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
	if (operation === 'faculty'){
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=faculty&";
		url = url + "id=" + getSelectedIDs();
		$.get(url,showPanel);
	}
}

function showPanel(returnData){
	var h = window.innerHeight;
	var w = window.innerWidth;
$("#showpanel").css("top",h/3);
$("#showpanel").css("left",w/4);
$("#showpanel").css("width",w/2);
$("#showpanel").css("height",(2*h)/5);
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
$(".updateFacultyToSubmit").css("width",(3*w)/8);
$(".updateFacultyToSubmit").css("position","relative");
$(".updateFacultyToSubmit").css("left",w/16);
$(".updateFacultyToSubmit").css("top",h/15);
$(".updateFacultyToSubmit").css("margin",0);
$(".updateFacultyToSubmit").css("padding",0);
$(".updateFacultyToSubmit fieldset").css("position","relative");
$(".updateFacultyToSubmit fieldset").css("width",w/4);
$(".updateFacultyToSubmit fieldset").css("left",w/8);
$(".updateFacultyToSubmit fieldset").css("margin",0);
$(".updateFacultyToSubmit fieldset").css("padding",0);
$(".updateFacultyToSubmit fieldset label").css("width",w/8);
$(".updateFacultyToSubmit fieldset input").css("width",w/8);
$(".updateFacultyToSubmit fieldset select").css("width",w/8);
$("#showpanel").show();
}
//-->
</script>
<table id="main">
<thead>
<tr>
<th><input style="width: 20%" type="checkbox"  onclick="checkAll()"/></th>
<th style="display: none"></th>
<th>Institute</th>
<th style="display: none"></th>
<th>Faculty</th>
</tr>
</thead>
<tbody class="mainBody">
<s:iterator value="#session.faculties" status="maprowStatus">
  <tr align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><input style="width: 20%" type="checkbox"/></td>
      <td style="display: none"><s:property value="institute.instituteid"/> </td>
      <td style="background: grey"><s:property value="institute.institutename"/></td>
      <td style="display: none"  id="id"><s:property value="facultyid"/></td>
      <td style="background: grey"><s:property value="facultyname"/></td>
    </s:if>
    <s:else>
      <td><input style="width: 20%" type="checkbox"/></td>
      <td style="display: none"><s:property value="institute.instituteid"/> </td>
      <td><s:property value="institute.institutename"/></td>
      <td style="display: none"  id="id"><s:property value="facultyid"/></td>
      <td><s:property value="facultyname"/></td>
    </s:else>
  </tr>
</s:iterator>
</tbody>
</table>
<table>
<tr align="center"><td ><input  value="VIEW" type="button" onclick="dphandler('faculty')"/></td><td ><input value="NEW" type="button"  onclick="clickhandler('newfaculty')"/></td><td ><input value="DELETE" type="button" onclick="clickhandler('deletefaculty')"/></td></tr>
</table>


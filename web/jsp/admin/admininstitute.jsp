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
	if (operation === 'institute'){
		var url = "http://localhost:8080/PHDWebApp/viewhandler?";
		url = url + "object=institute&";
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
$("#showpanel").css("height",h/3);
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
$(".updateInstitutionToSubmit").css("width",(3*w)/8);
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
<th><input style="width: 20%" type="checkbox"  onclick="checkAll()"/></th>
<th style="display: none"></th>
<th colspan="2">Institute</th>
</tr>
</thead>
<tbody class="mainBody">
<s:iterator value="#session.institutes" status="maprowStatus">
  <tr align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><input style="width:20%;" type="checkbox"/></td>
      <td style="display: none"  id="id"><s:property value="instituteid"/></td>
      <td colspan="2" style="background: grey"><s:property value="institutename"/></td>
    </s:if>
    <s:else>
      <td><input style="width:20%;" type="checkbox"/></td>
      <td style="display: none"  id="id"><s:property value="instituteid"/></td>
      <td colspan="2"><s:property value="institutename"/></td>
    </s:else>
  </tr>
  
  
</s:iterator>
</tbody>
</table>
<table>
<tr align="center"><td ><input  value="VIEW" type="button" onclick="dphandler('institute')"/></td><td ><input value="NEW" type="button"  onclick="clickhandler('newinstitute')"/></td><td ><input value="DELETE" type="button" onclick="clickhandler('deleteinstitute')"/></td></tr>
</table>
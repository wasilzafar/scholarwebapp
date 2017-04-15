<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
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
<table id="main">
<thead>
<tr align="center">
<th><input style="width: 20%" type="checkbox" onclick="checkAll()"/></th>
<th>Scholar Name </th>
<th>From</th>
<th>To </th>
<th>Status </th>
</tr>
</thead>
<tbody class="mainBody">
<s:iterator value="#session.reps" status="maprowStatus">
  <tr id="mr" align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><input style="width: 20%" type="checkbox"/></td>
      <td style="background: grey"><s:property value="scholar.scholarFirstName"/><s:property value="scholar.scholarMiddleName"/><s:property value="scholar.scholarLastName"/></td>
      <td style="background: grey"><s:property value="fromDate"/></td>
      <td style="background: grey"><s:property value="toDate"/></td>
      <td style="background: grey"><s:property value="status"/> </td>
      <td style="display : none" id="id"><s:property value="mreportid"/></td>
      <td style="background: grey"><button onclick="window.open('http://localhost:8080/PHDWebApp/viewreport?type=monthlyreport&id=<s:property value="mreportid"/>')">View</button> </td>
    </s:if>
    <s:else>
      <td><input style="width: 20%" type="checkbox"/></td>
      <td><s:property value="scholar.scholarFirstName"/><s:property value="scholar.scholarMiddleName"/><s:property value="scholar.scholarLastName"/></td>
      <td><s:property value="fromDate"/></td>
      <td><s:property value="toDate"/></td>
      <td><s:property value="status"/> </td>
      <td style="display : none" id="id"><s:property value="mreportid"/></td>
      <td><button onclick="window.open('http://localhost:8080/PHDWebApp/viewreport?type=monthlyreport&id='+'encode(<s:property value="mreportid"/>)')">View</button> </td>
    </s:else>
  </tr>
</s:iterator>
</tbody>
</table>
<table>
<tr align="center"><td><input value="VIEW" type="button" onclick="viewSelected('monthlyreport')"/></td><td><input value="APPROVE" type="button" onclick="reportHandler('monthlyreport','approve')"/></td><td><input value="DELETE" type="button" onclick="clickhandler('deletemonthlyreport')"/></td></tr>
</table>
</body>
</html>
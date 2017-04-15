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
-->
</style>
<table id="main">
<thead>
<tr align="center">
<th><input style="width: 20%" type="checkbox" onclick="checkAll()"></th>
<th>From Date </th>
<th>To Date</th>
<th>Status</th>
<th style="display: none"></th>
</tr>
</thead>
<tbody>
<s:iterator value="#session.reps" status="maprowStatus">
  <tr align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><input style="width: 20%" type="checkbox"/></td>
      <td><s:property value="fromDate"/></td>
      <td><s:property value="toDate"/></td>
      <td><s:property value="status"/> </td>
      <td style="display : none"><s:property value="mreportid"/></td>
    </s:if>
    <s:else>
      <td><input style="width: 20%" type="checkbox"/></td>
      <td><s:property value="fromDate"/></td>
      <td><s:property value="toDate"/></td>
      <td><s:property value="status"/> </td>
      <td style="display : none"><s:property value="mreportid"/></td>
    </s:else>
  </tr>
</s:iterator>
</tbody>
</table>
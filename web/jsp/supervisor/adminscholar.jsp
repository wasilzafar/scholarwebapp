<%@ taglib prefix="s" uri="/struts-tags" %>

<table id="main">
<tr>
<th><input type="checkbox" onclick="checkAll()"></th>
<th>Scholar</th>
<th>Supervisor</th>
<th>Institute</th>
<th>Faculty</th>
<th>Department</th>
<th>Status</th>
</tr>

<s:iterator value="#session.scholars" status="maprowStatus">
  <tr align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><input type="checkbox"/></td>
      <td style="background: grey"><s:property value="scholarFirstName"/><s:property value="scholarMiddleName"/><s:property value="scholarLastName"/></td>
      <td style="background: grey"><s:property value="supervisor.supervisorFirstName"/>&nbsp; <s:property value="supervisor.supervisorMiddleName"/>&nbsp; <s:property value="supervisor.supervisorLastName"/></td>
      <td style="background: grey"><s:property value="institute.institutename"/></td>
      <td style="background: grey"><s:property value="faculty.facultyname"/></td>
      <td style="background: grey"><s:property value="department.departmentname"/></td>
      <td style="background: grey"><s:property value="status"/> </td>
      <td style="display : none"><s:property value="scholarID"/></td>
      <td style="display : none"><s:property value="supervisor.supervisorID"/></td>
    </s:if>
    <s:else>
      <td><input type="checkbox"/></td>
      <td><s:property value="scholarFirstName"/><s:property value="scholarMiddleName"/><s:property value="scholarLastName"/></td>
      <td><s:property value="supervisor.supervisorFirstName"/> &nbsp; <s:property value="supervisor.supervisorMiddleName"/> &nbsp; <s:property value="supervisor.supervisorLastName"/></td>
      <td><s:property value="institute.institutename"/></td>
      <td><s:property value="faculty.facultyname"/></td>
      <td><s:property value="department.departmentname"/></td>
      <td><s:property value="status"/> </td>
      <td style="display : none"><s:property value="scholarID"/></td>
      <td style="display : none"><s:property value="supervisor.supervisorID"/></td>
    </s:else>
  </tr>
</s:iterator>

</table>

<table>
<tr align="center"><td><input value="VIEW" type="button" onclick="goToViewScholar()"/></td><td><input value="NEW" type="button" onclick="goToNewScholar()"/></td><td><input value="DELETE" type="button" onclick="alert('Continue to delete ..')"/></td></tr>
</table>

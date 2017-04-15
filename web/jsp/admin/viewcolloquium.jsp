<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form cssClass="updateColloquiumToSubmit" action="updatecolloquiumsubmit"
	theme="simple" method="POST">
<label>Name of Observer : </label><s:textfield name="observerName" type="text" value="%{#request.colloq.observerName}"></s:textfield><br>
<label>Date and Time : </label><s:textfield id="datetime" name="datetime" type="text" value="%{#request.colloq.datetime}"></s:textfield><br>
<table id="collTable">
<thead>
<tr>
<th>Scholar</th>
<th>Supervisor</th>
<th>Co-Supervisor</th>
<th>Topic of Study</th>
<th style="display: none"></th>
</tr>
</thead>
<tbody class="collTablemainBody">
<s:iterator value="#request.scholars" status="maprowStatus">
  <tr align="center">
    <s:if test="#maprowStatus.odd == true">
      <td><s:property/></td>
      <td></td>
      <td></td>
      <td></td>
      <td style='display:none'><input type='text' name='scholarID' value=''></input></td>
    </s:if>
    <s:else>
      <td><s:property/></td>
      <td></td>
      <td></td>
      <td></td>
      <td style='display:none'><input type='text' name='scholarID' value=''></input></td>
    </s:else>
  </tr>
</s:iterator>
</tbody>
</table>
</s:form>
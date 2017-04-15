<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="sideMenu">
	<ul id="menu">
	<s:if test="#session.loggedinAs != 'supervisor'">
		<s:if test="#session.user.role == 'SUPERADMIN'">
			<li id="insmanage"><a href="#">Institute Management</a></li>
			<li id="facmanage"><a href="#">Faculty Management</a></li>
			<li id="depmanage"><a href="#">Department Management</a></li>
			<li id="supmanage"><a href="#">Supervisor Management</a></li>
			<li id="schmanage"><a href="#">Scholar Management</a></li>
			<li id="monthly"><a href="#">Monthly Reports</a></li>
			<li id="quarterly"><a href="#">Quarterly Reports</a></li>
			<li id="others"><a href="#">Others</a></li>
		</s:if>
		<s:elseif test="#session.user.role == 'ADMIN'">
			<li id="facmanage"><a href="#">Faculty Management</a></li>
			<li id="depmanage"><a href="#">Department Management</a></li>
			<li id="supmanage"><a href="#">Supervisor Management</a></li>
			<li id="schmanage"><a href="#">Scholar Management</a></li>
			<li id="monthly"><a href="#">Monthly Reports</a></li>
			<li id="quarterly"><a href="#">Quarterly Reports</a></li>
			<li id="others"><a href="#">Others</a></li>
		</s:elseif>
		<s:elseif test="#session.user.role == 'DEAN'">
			<li id="depmanage"><a href="#">Department Management</a></li>
			<li id="supmanage"><a href="#">Supervisor Management</a></li>
			<li id="schmanage"><a href="#">Scholar Management</a></li>
			<li id="monthly"><a href="#">Monthly Reports</a></li>
			<li id="quarterly"><a href="#">Quarterly Reports</a></li>
			<li id="others"><a href="#">Report Controller</a></li>
		</s:elseif>
		<s:elseif test="#session.user.role == 'HEAD'">
			<li id="supmanage"><a href="#">Supervisor Management</a></li>
			<li id="schmanage"><a href="#">Scholar Management</a></li>
			<li id="monthly"><a href="#">Monthly Reports</a></li>
			<li id="quarterly"><a href="#">Quarterly Reports</a></li>
			<li id="others"><a href="#">Colloquium</a></li>
		</s:elseif>
		</s:if>
		<s:else>
			<li id="schmanage"><a href="#">Scholar Management</a></li>
			<li id="monthly"><a href="#">Monthly Reports</a></li>
			<li id="quarterly"><a href="#">Quarterly Reports</a></li>
		</s:else>
	</ul>
</div>
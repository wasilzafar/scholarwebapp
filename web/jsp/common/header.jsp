<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="height: 120px;">
	<img alt="Image" src="././images/grey.png" id="tdbgi" />
	<div style="float: left"></div>
	<div style="float: right"></div>
	<div style="float: right; font-size: large;">
		<div style="float: right; font-size: large; color: #003366;">Logged
			in as Scholar</div>
		<br> <br>
		<div id='cssmenu' style="float: right">
			<ul style="float: right; font-size: large;">
				<li class='has-sub'><a href='#'><img
						src="././images/prev.png" alt="Previous" width="36px"
						height="29px"></img></a>
				<li class='has-sub'><a href='#'><img
						src="././images/next.png" alt="Next" width="36px" height="29px"></img></a>
				<li class='has-sub'><a href='#'><img
						src="././images/setting.png" alt="Options" width="71px"
						height="29px"></img></a>
					<ul>
						<s:if test="#session.loggedinAs != 'scholar'">
							<s:if test="#session.user.role != 'SUPERVISOR'">
								<s:if test="#session.loggedinAs == 'supervisor'">
									<li><a href='http://localhost:8080/PHDWebApp/switchlogin'><span><s:property
													value="#session.user.role" /></span></a></li>
								</s:if>
								<s:else>
									<li><a href='http://localhost:8080/PHDWebApp/switchlogin'><span>Supervisor</span></a></li>
								</s:else>
							</s:if>
						</s:if>
						<li><a href='#'><span>Preferences</span></a></li>
						<li><a href='#'><span>Help</span></a></li>
						<li class='last'><a
							href='http://localhost:8080/PHDWebApp/logout'><span>Logout</span></a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>
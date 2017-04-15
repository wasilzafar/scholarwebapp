<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="height: 30px;background-color: grey">
<div style="float: left;font-size: large;">Welcome <s:property value="#session.user.screenName"/></div>
<div id='cssmenu' style="float: right">
<ul>
   <li class='has-sub'><a href='#'><img src="././images/setting.png" alt="*" width="30px" height="30px"></img></a>
      <ul>
         <li><a href='#'><span>Themes</span></a></li>
         <s:if test="#session.loggedinAs != 'scholar'">
         <s:if test="#session.user.role != 'SUPERVISOR'">
         <s:if test="#session.loggedinAs == 'supervisor'">
         <li><a href='http://localhost:8080/PHDWebApp/switchlogin'><span><s:property value="#session.user.role"/></span></a></li>
         </s:if>
         <s:else>
         <li><a href='http://localhost:8080/PHDWebApp/switchlogin'><span>Supervisor</span></a></li>
         </s:else>
         </s:if>
         </s:if>
         <li class='last'><a href='http://localhost:8080/PHDWebApp/logout'><span>Logout</span></a></li>
      </ul>
   </li>
</ul>
</div>
<div style="float: right;font-size: large;">Logged in as <s:property value="#session.loggedinAs"/></div>
</div>
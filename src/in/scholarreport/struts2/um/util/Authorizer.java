package in.scholarreport.struts2.um.util;

import java.util.Arrays;
import java.util.List;

enum Permission {
	SUPERADMIN(new String[]{"institute delete","institute update","faculty delete","faculty fetch","faculty update","department fetch",
			"department update","department delete","supervisor delete","supervisor update","supervisor fetch","supervisor activate",
			"supervisor inactivate","scholar delete","scholar fetch","scholar update","scholar activate","scholar inactivate",
			"monthlyreport APPROVED","monthlyreport REVISE_DEAN","monthlyreport delete","monthlyreport view","quarterlyreport APPROVED",
			"quarterlyreport REVISE_DEAN","quarterlyreport delete","quarterlyreport view"}),
			
	ADMIN(new String[]{"faculty delete","faculty fetch","faculty update","department fetch",
			"department update","department delete","supervisor delete","supervisor update","supervisor fetch","supervisor activate",
			"supervisor inactivate","scholar delete","scholar fetch","scholar update","scholar activate","scholar inactivate",
			"monthlyreport APPROVED","monthlyreport REVISE_DEAN","monthlyreport delete","monthlyreport view","quarterlyreport APPROVED",
			"quarterlyreport REVISE_DEAN","quarterlyreport delete","quarterlyreport view"}),
			
	DEAN(new String[]{"department fetch","department update","department delete","supervisor delete","supervisor update",
			"supervisor fetch","supervisor activate","supervisor inactivate","scholar delete","scholar fetch","scholar update",
			"scholar activate","scholar inactivate","monthlyreport APPROVED","monthlyreport REVISE_DEAN","monthlyreport delete",
			"monthlyreport view","quarterlyreport APPROVED","quarterlyreport REVISE_DEAN","quarterlyreport delete","quarterlyreport view"}), 
			
	HEAD(new String[]{"supervisor delete","supervisor update","supervisor fetch","supervisor activate","supervisor inactivate",
			"scholar delete","scholar fetch","scholar update","scholar activate","scholar inactivate","monthlyreport PENDING_DEAN",
			"monthlyreport REVISE_HEAD","monthlyreport review","monthlyreport delete","monthlyreport view","quarterlyreport PENDING_DEAN",
			"quarterlyreport REVISE_HEAD","quarterlyreport delete","quarterlyreport view"}), 
			
	SUPERVISOR(new String[]{"scholar delete","scholar fetch","scholar update","scholar activate","scholar inactivate","monthlyreport PENDING_HEAD",
			"monthlyreport REVISE_SUPERVISOR","monthlyreport delete","monthlyreport view","quarterlyreport PENDING_HEAD",
			"quarterlyreport REVISE_SUPERVISOR","quarterlyreport delete","quarterlyreport view"}),
	SCHOLAR(new String[]{"faculty fetch","department fetch","supervisor fetch"});
	
	private String[] permissions;
	
	Permission(String[] permissions){
		this.permissions = permissions; 
	}
	
	List allPermissions(){
		return Arrays.asList(permissions);
	}
}

enum Role {
	SUPERADMIN(new String[]{"SUPERADMIN","ADMIN","DEAN","HEAD","SUPERVISOR"}),
			
	ADMIN(new String[]{"DEAN","HEAD","SUPERVISOR"}),
			
	DEAN(new String[]{"HEAD","SUPERVISOR"}), 
			
	HEAD(new String[]{"SUPERVISOR"}), 
	
	SUPERVISOR(new String[]{""}),
	
	SCHOLAR(new String[]{"SCHOLAR"});
	
	private String[] roles;
	
	Role(String[] roles){
		this.roles = roles; 
	}
	
	String[] allRoles(){
		return roles;
	}
}

public class Authorizer {
	public static List getPermissions(String currentPermission){
		Permission per;
		if(currentPermission.equalsIgnoreCase("SUPERADMIN")){
			per = Permission.SUPERADMIN;
			return per.allPermissions();
		}else if(currentPermission.equalsIgnoreCase("ADMIN")){
			per = Permission.ADMIN;
			return per.allPermissions();
		} else if(currentPermission.equalsIgnoreCase("DEAN")){
			per = Permission.DEAN;
			return per.allPermissions();
		}else if(currentPermission.equalsIgnoreCase("HEAD")){
			per = Permission.HEAD;
			return per.allPermissions();
		}else if(currentPermission.equalsIgnoreCase("SUPERVISOR")){
			per = Permission.SUPERVISOR;
			return per.allPermissions();
		}else if(currentPermission.equalsIgnoreCase("SCHOLAR")){
			per = Permission.SCHOLAR;
			return per.allPermissions();
		}
		return null;
	} 
	
   public static boolean authorize(String permit, String role){
	   List perm = getPermissions(role);
	   if(perm.contains(permit))
	   return true;
	   
	   return false;
   }
   
   public static String[] getRoles(String currentRole){
		Role role;
		if(currentRole.equalsIgnoreCase("SUPERADMIN")){
			role = Role.SUPERADMIN;
			return role.allRoles();
		}else if(currentRole.equalsIgnoreCase("ADMIN")){
			role = Role.ADMIN;
			return role.allRoles();
		} else if(currentRole.equalsIgnoreCase("DEAN")){
			role = Role.DEAN;
			return role.allRoles();
		}else if(currentRole.equalsIgnoreCase("HEAD")){
			role = Role.HEAD;
			return role.allRoles();
		}else if(currentRole.equalsIgnoreCase("SUPERVISOR")){
			role = Role.SUPERVISOR;
			return role.allRoles();
		}else if(currentRole.equalsIgnoreCase("Scholar")){
			role = Role.SCHOLAR;
			return role.allRoles();
		}
		return null;
	}
}

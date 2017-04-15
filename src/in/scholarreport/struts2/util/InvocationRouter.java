package in.scholarreport.struts2.util;

import in.scholarreport.struts2.Delegate.BaseDelegate;
import in.scholarreport.struts2.Delegate.ReportDelegate;
import in.scholarreport.struts2.Delegate.ScholarDelegate;
import in.scholarreport.struts2.Delegate.SupervisorDelegate;
import in.scholarreport.struts2.actions.InvokeAction;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

enum Operation {
	APPROVED, PENDING_SUPERVISOR, PENDING_HEAD, PENDING_DEAN, REVISE_SUPERVISOR, REVISE_DEAN, REVISE_HEAD, DELETE, ACTIVATE, INACTIVATE, FETCH
};

enum Object {
	MONTHLYREPORT, QUARTERLYREPORT, SCHOLAR, SUPERVISOR, FACULTY, DEPARTMENT, INSTITUTE
}

public class InvocationRouter {
	static Logger logger = Logger.getLogger(InvocationRouter.class);
	Operation op;
	Object obj;
	BaseDelegate delegate;

	public String routeInvocation(String object, String operation, String input) {
		if (object.equalsIgnoreCase("monthlyreport")) {
			obj = Object.MONTHLYREPORT;
			if (operation.equalsIgnoreCase("PENDING_SUPERVISOR"))
				op = Operation.PENDING_SUPERVISOR;
			else if (operation.equalsIgnoreCase("PENDING_HEAD"))
				op = Operation.PENDING_HEAD;
			else if (operation.equalsIgnoreCase("PENDING_DEAN"))
				op = Operation.PENDING_DEAN;
			else if (operation.equalsIgnoreCase("REVISE_SUPERVISOR"))
				op = Operation.REVISE_SUPERVISOR;
			else if (operation.equalsIgnoreCase("REVISE_HEAD"))
				op = Operation.REVISE_HEAD;
			else if (operation.equalsIgnoreCase("REVISE_DEAN"))
				op = Operation.REVISE_DEAN;
			else if (operation.equalsIgnoreCase("APPROVED"))
				op = Operation.APPROVED;
			else if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;

		} else if (object.equalsIgnoreCase("quarterlyreport")) {
			obj = Object.QUARTERLYREPORT;
			if (operation.equalsIgnoreCase("PENDING_SUPERVISOR"))
				op = Operation.PENDING_SUPERVISOR;
			else if (operation.equalsIgnoreCase("PENDING_HEAD"))
				op = Operation.PENDING_HEAD;
			else if (operation.equalsIgnoreCase("PENDING_DEAN"))
				op = Operation.PENDING_DEAN;
			else if (operation.equalsIgnoreCase("REVISE_SUPERVISOR"))
				op = Operation.REVISE_SUPERVISOR;
			else if (operation.equalsIgnoreCase("REVISE_HEAD"))
				op = Operation.REVISE_HEAD;
			else if (operation.equalsIgnoreCase("REVISE_DEAN"))
				op = Operation.REVISE_DEAN;
			else if (operation.equalsIgnoreCase("APPROVED"))
				op = Operation.APPROVED;
			else if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;

		} else if (object.equalsIgnoreCase("scholar")) {
			obj = Object.SCHOLAR;
			if (operation.equalsIgnoreCase("activate"))
				op = Operation.ACTIVATE;
			if (operation.equalsIgnoreCase("inactivate"))
				op = Operation.INACTIVATE;
			else if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;
		} else if (object.equalsIgnoreCase("supervisor")) {
			obj = Object.SUPERVISOR;
			if (operation.equalsIgnoreCase("activate"))
				op = Operation.ACTIVATE;
			if (operation.equalsIgnoreCase("inactivate"))
				op = Operation.INACTIVATE;
			else if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;
			else if (operation.equalsIgnoreCase("fetch"))
				op = Operation.FETCH;
		} else if (object.equalsIgnoreCase("faculty")) {
			obj = Object.FACULTY;
			if (operation.equalsIgnoreCase("fetch"))
				op = Operation.FETCH;
			else if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;
		} else if (object.equalsIgnoreCase("department")) {
			obj = Object.DEPARTMENT;
			if (operation.equalsIgnoreCase("fetch"))
				op = Operation.FETCH;
			else if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;
		}else if (object.equalsIgnoreCase("institute")) {
			obj = Object.INSTITUTE;
			if (operation.equalsIgnoreCase("delete"))
				op = Operation.DELETE;
		}

		if (op != null) {
			switch (obj) {
			case MONTHLYREPORT:
				delegate = new ReportDelegate();
				break;
			case QUARTERLYREPORT:
				delegate = new ReportDelegate();
				break;
			case SCHOLAR:
				delegate = new ScholarDelegate();
				break;
			case SUPERVISOR:
				delegate = new SupervisorDelegate();
				break;
			case INSTITUTE:
				delegate = new BaseDelegate();
				break;
			case FACULTY:
				delegate = new BaseDelegate();
				break;
			case DEPARTMENT:
				delegate = new BaseDelegate();
				break;
			}
		}

		logger.info("Routing with ... " + obj.toString() + " " + op.toString()
				+ " " + input);
		logger.info("Delegate ... " + delegate.getClass().getName());
		return route(obj, op, input, delegate);
	}

	private String route(Object obj2, Operation op2, String input,
			BaseDelegate delegate2) {
		String[] strIDs;
		int i = 0;
		logger.info("Entering for routing .... " + obj.toString() + " "
				+ op.toString() + " " + input + " " + delegate2);
		List ids = (ArrayList) CommonUtilities.getIdsFromInput(input);
		strIDs = new String[ids.size()];
		for (java.lang.Object str : ids.toArray()) {
			strIDs[i] = (String) str;
			i++;
		}
		if (obj2 == Object.MONTHLYREPORT
				&& (op2 == Operation.APPROVED || op2 == Operation.PENDING_DEAN
						|| op2 == Operation.PENDING_HEAD
						|| op2 == Operation.PENDING_SUPERVISOR
						|| op2 == Operation.REVISE_SUPERVISOR
						|| op2 == Operation.REVISE_HEAD || op2 == Operation.REVISE_DEAN)) {
			logger.info("Routed to approveMonthlyReportsById");
			return ((ReportDelegate) delegate2)
					.approveMonthlyReportsById(strIDs, op2.name());
		} else if (obj2 == Object.MONTHLYREPORT && op2 == Operation.DELETE) {
			logger.info("Routed to deleteMonthlyReportsById");
			return ((ReportDelegate) delegate2)
					.deleteMonthlyReportsById(strIDs);
		} else if (obj2 == Object.QUARTERLYREPORT
				&& (op2 == Operation.APPROVED || op2 == Operation.PENDING_DEAN
						|| op2 == Operation.PENDING_HEAD
						|| op2 == Operation.PENDING_SUPERVISOR
						|| op2 == Operation.REVISE_SUPERVISOR
						|| op2 == Operation.REVISE_HEAD || op2 == Operation.REVISE_DEAN)) {
			logger.info("Routed to approveQuarterlyReportsById");
			return ((ReportDelegate) delegate2)
					.approveQuarterlyReportsById(strIDs, op2.name());
		} else if (obj2 == Object.QUARTERLYREPORT && op2 == Operation.DELETE) {
			logger.info("Routed to deleteQuarterlyReportsById");
			return ((ReportDelegate) delegate2)
					.deleteQuarterlyReportsById(strIDs);
		} else if (obj2 == Object.SCHOLAR && op2 == Operation.ACTIVATE) {
			logger.info("Routed to activateScholarsById");
			return ((ScholarDelegate) delegate2).activateScholarsById(strIDs);
		} else if (obj2 == Object.SCHOLAR && op2 == Operation.INACTIVATE) {
			logger.info("Routed to inactivateScholarsById");
			return ((ScholarDelegate) delegate2).inactivateScholarsById(strIDs);
		} else if (obj2 == Object.SCHOLAR && op2 == Operation.DELETE) {
			logger.info("Routed to deleteScholarsById");
			return ((ScholarDelegate) delegate2).deleteScholarsById(strIDs);
		} else if (obj2 == Object.SUPERVISOR && op2 == Operation.ACTIVATE) {
			logger.info("Routed to activateSupervisorsById");
			return ((SupervisorDelegate) delegate2)
					.activateSupervisorsById(strIDs);
		} else if (obj2 == Object.SUPERVISOR && op2 == Operation.INACTIVATE) {
			logger.info("Routed to inactivateSupervisorsById");
			return ((SupervisorDelegate) delegate2)
					.inactivateSupervisorsById(strIDs);
		} else if (obj2 == Object.SUPERVISOR && op2 == Operation.DELETE) {
			logger.info("Routed to deleteSupervisorsById");
			return ((SupervisorDelegate) delegate2)
					.deleteSupervisorsById(strIDs);
		} else if (obj2 == Object.SUPERVISOR && op2 == Operation.FETCH) {
			logger.info("Routed to fetchSupervisorsByDepartmentId");
			return ((SupervisorDelegate) delegate2)
					.fetchSupervisorsByDepartmentId(strIDs);
		} else if (obj2 == Object.FACULTY && op2 == Operation.FETCH) {
			logger.info("Routed to fetchFacultyByInstituteId");
			return ((BaseDelegate) delegate2).fetchFacultyByInstituteId(strIDs);
		} else if (obj2 == Object.DEPARTMENT && op2 == Operation.FETCH) {
			logger.info("Routed to fetchDepartmentByFacultyId");
			return ((BaseDelegate) delegate2)
					.fetchDepartmentByFacultyId(strIDs);
		} else if (obj2 == Object.INSTITUTE && op2 == Operation.DELETE) {
			logger.info("Routed to deleteInstituteById");
			return ((BaseDelegate) delegate2)
					.deleteInstituteById(strIDs);
		}else if (obj2 == Object.FACULTY && op2 == Operation.DELETE) {
			logger.info("Routed to deleteInstituteById");
			return ((BaseDelegate) delegate2)
					.deleteFacultyById(strIDs);
		}else if (obj2 == Object.DEPARTMENT && op2 == Operation.DELETE) {
			logger.info("Routed to deleteInstituteById");
			return ((BaseDelegate) delegate2)
					.deleteDepartmentById(strIDs);
		}

		logger.info("Routed to nowhere ....");

		return null;

	}
}

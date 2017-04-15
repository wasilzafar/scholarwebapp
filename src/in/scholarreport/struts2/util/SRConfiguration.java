package in.scholarreport.struts2.util;

import java.util.HashMap;
import java.util.Map;

public class SRConfiguration {
	private static HashMap configuration;
	public static Map getInstance(){
		if(configuration == null)
			configuration = new HashMap();
		
		return configuration;
			
	}
}

package in.scholarreport.struts2.actions;

import in.scholarreport.spring3.HelloBean;
import in.scholarreport.spring3.Performer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class HelloAction extends ActionSupport implements ModelDriven {
	HelloBean model =  new HelloBean();
	static Logger logger = Logger.getLogger(HelloAction.class);
@Override
public String execute() throws Exception {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	Performer performer = (Performer) ctx.getBean("duke");
	performer.perform();
	logger.info("Hello from HelloAction with message : "+getModel().getMsg());
	return super.execute();
}
public HelloBean getModel() {
	return model;
}
}

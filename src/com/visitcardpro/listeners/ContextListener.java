package com.visitcardpro.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("CONTEXT LISTENER --------------------------------------------");

		/*User user = new User();
		user.setAuth(new Authentication());
		user.getAuth().setEmail("pierre0debruyne@gmail.com");
		user.getAuth().setHashedPassword("lallala");
		user.getAuth().setAccessToken("azqsdfwxdqz");
		user.getAuth().setRole("CLIENT");
		DAOFactory.getInstance().getUserDao().create(user);
		System.out.println("ServletContextListener started");

		System.out.println(user.getAuth().getId());*/

	}
}
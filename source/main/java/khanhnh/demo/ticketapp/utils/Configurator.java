package khanhnh.demo.ticketapp.utils;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import khanhnh.demo.ticketapp.sercurity.AuthenticationFilter;

/**
 * Application Lifecycle Listener implementation class Configurator
 *
 */
@WebListener
public class Configurator implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Configurator() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	ServletContext context = event.getServletContext();
    	FilterRegistration.Dynamic registration = context.addFilter("authenticationFilter", new AuthenticationFilter());
    	registration.setAsyncSupported(true);
    	registration.addMappingForUrlPatterns(null, false, "/sessions", "/tickets", "/chat");
    }
	
}

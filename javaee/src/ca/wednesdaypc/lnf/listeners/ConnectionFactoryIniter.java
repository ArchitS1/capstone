package ca.wednesdaypc.lnf.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ca.wednesdaypc.lnf.db.ConnectionFactory;

/**
 * This will later be replaced/changed to use a connection pool. 
 *
 */
@WebListener
public class ConnectionFactoryIniter implements ServletContextListener {

	/**
	 * The name of the servlet context attribute holding the ConnectionFactory
	 * object.
	 */
	public static final String FACTORY_NAME = "connectionFactory";
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
    	ServletContext sc = sce.getServletContext();
    	sc.setAttribute(FACTORY_NAME, new ConnectionFactory(
        		 sc.getInitParameter("driver"),
        		 sc.getInitParameter("url"),
        		 sc.getInitParameter("schema"),
        		 sc.getInitParameter("extrastuff"),
        		 sc.getInitParameter("dbusername"),
        		 sc.getInitParameter("dbpassword")));
    }
	
}

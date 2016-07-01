package khanhnh.demo.ticketapp.sercurity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {

	@Override
	public void sessionIdChanged(HttpSessionEvent e, String oldSessionId) {
		// TODO Auto-generated method stub
		System.out.println(this.date() + ": Session ID " + oldSessionId +
				" changed to " + e.getSession().getId());
		SessionRegistry.updateSession(e.getSession(), oldSessionId);
	}

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		// TODO Auto-generatedS method stub
		System.out.println(this.date() + ": Session " + e.getSession().getId() + " created.");
		SessionRegistry.addSession(e.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		System.out.println(this.date() + ": Session " + e.getSession().getId() + " destroyed.");
		SessionRegistry.removeSession(e.getSession());
		
	}

	private SimpleDateFormat formatter=new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	private String date(){
		return this.formatter.format(new Date());
	}
}

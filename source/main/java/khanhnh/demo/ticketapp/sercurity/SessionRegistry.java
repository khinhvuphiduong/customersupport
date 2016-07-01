package khanhnh.demo.ticketapp.sercurity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public final class SessionRegistry {
	private static final Map<String, HttpSession> SESSION= new Hashtable<>();
	
	public static void addSession(HttpSession session){
		SESSION.put(session.getId(), session);
	}
	
	public static void updateSession(HttpSession session, String oldSessionId){
		session.removeAttribute(oldSessionId);
		addSession(session);
	}
	
	public static void removeSession(HttpSession session){
		SESSION.remove(session.getId());
	}
	
	public static List<HttpSession> getAllSession(){
		return new ArrayList<>(SESSION.values());
	}
	
	public static int getNumberOfSession(){
		return SESSION.size();
	}
}

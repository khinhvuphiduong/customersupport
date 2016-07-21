package khanhnh.demo.ticketapp.chat;

import java.io.File;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.HandshakeResponse;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import com.wrox.chat.ChatSession;

@ServerEndpoint(
		value = "/chat/{sessionId}",
		encoders=ChatMessageCodec.class,
		decoders=ChatMessageCodec.class
		//configurator=ChatEnpoint.
		)
@WebListener
public class ChatEndpoint implements HttpSessionListener {

	private static final String HTTP_SESSION_PROPERTY = "khanhnh.demo.http.HTTP_SESSION";
	private static final String WS_SESSION_PROPERTY = "khanhnh.demo.ws.WS_SESSION";
	private static long sessionIdSequence = 1L;
	private static final Object sessionIdSequenceLock=new Object();
	private static final Map<Long, ChatSession> chatSessions=new Hashtable<>();
	private static final Map<Session, ChatSession> sessions=new Hashtable<>();
	private static final Map<Session, HttpSession> httpSessions=new Hashtable<>();
	private static final List<ChatSession> pendingSessions=new ArrayList<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("sessionId") long sessionId){
		HttpSession httpSession = (HttpSession)session.getUserProperties().get(ChatEndpoint.HTTP_SESSION_PROPERTY);
		try{
			if(httpSession==null||httpSession.getAttribute("username")==null){
				session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, "you ar not logged in"));
				return;
			}
		}
		catch(IOException | EncodeException e)
		{
			this.onError(session, e);
		}
	}

	@OnMessage
	public void onMessage(Session session, ChatMessage message){
	}

	@OnError
	public void onError(Session session, Throwable e)
	{
		ChatMessage message = new ChatMessage();
		message.setUser((String)session.getUserProperties().get("username"));
		message.setType(ChatMessage.Type.ERROR);
		message.setTimestamp(OffsetDateTime.now());
		message.setContent(message.getUser() + " left the chat due to an error.");
		try
		{
			Session other = this.close(session, message);
			if(other != null)
				other.close(new CloseReason(
						CloseReason.CloseCodes.UNEXPECTED_CONDITION, e.toString()
						));
		}
		catch(IOException ignore) { }
		finally
		{
			try
			{
				session.close(new CloseReason(
						CloseReason.CloseCodes.UNEXPECTED_CONDITION, e.toString()
						));
			}
			catch(IOException ignore) { }
		}
	}

	private Session close(Session s, ChatMessage message)
	{
		ChatSession c = ChatEndpoint.sessions.get(s);
		Session other = this.getOtherSession(c, s);
		ChatEndpoint.sessions.remove(s);
		HttpSession h = ChatEndpoint.httpSessions.get(s);
		if(h != null)
			this.getSessionsFor(h).remove(s);
		if(c != null)
		{
			c.log(message);
			ChatEndpoint.pendingSessions.remove(c);
			ChatEndpoint.chatSessions.remove(c.getSessionId());
			try
			{
				c.writeChatLog(new File("chat." + c.getSessionId() + ".log"));
			}
			catch(Exception e)
			{
				System.err.println("Could not write chat log.");
				e.printStackTrace();
			}
		}
		if(other != null)
		{
			ChatEndpoint.sessions.remove(other);
			h = ChatEndpoint.httpSessions.get(other);
			if(h != null)
				this.getSessionsFor(h).remove(s);
			try
			{
				other.getBasicRemote().sendObject(message);
			}
			catch(IOException | EncodeException e)
			{
				e.printStackTrace();
			}
		}
		return other;
	}

	private Session getOtherSession(ChatSession c, Session s)
	{
		return c == null ? null :
			(s == c.getCustomer() ? c.getRepresentative() : c.getCustomer());
	}

	public static class EndpointConfigurator
	extends ServerEndpointConfig.Configurator
	{
		@Override
		public void modifyHandshake(ServerEndpointConfig config,
				HandshakeRequest request, HandshakeResponse response)
		{
			super.modifyHandshake(config, request, response);
			config.getUserProperties().put(
					ChatEndpoint.HTTP_SESSION_PROPERTY, request.getHttpSession());
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}
}

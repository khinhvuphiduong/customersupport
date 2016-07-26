package khanhnh.demo.ticketapp.chat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import khanhnh.demo.ticketapp.chat.ChatEndpoint;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet(name = "ChatServlet", urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			request.setAttribute("sessions", ChatEndpoint.Pendingsessions());
			request.getRequestDispatcher("/jsp/view/chat/list.jsp").forward(request, response);
		} else
			response.sendRedirect("tickets");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Expries", "Thu, 1 Jan 1970 12:00:00 GM");
		response.setHeader("Cache-Control", "max-age=0, must-revalidate, no-cache");

		String action = request.getParameter("action");
		String view = null;
		switch (action) {
		case "new":
			request.setAttribute("chatSessionId", 0);
			view = "chat";
			break;
		case "join":
			String id = request.getParameter("chatSessionId");
			if (id == null || !NumberUtils.isDigits(id))
				response.sendRedirect("chat?list");
			else {
				request.setAttribute("chatSessionID", Long.parseLong(id));
				view = "chat";
			}
			break;
		default:
			break;
		}

		if (view != null)
			request.getRequestDispatcher("jsp/view/chat/" + view + ".jsp").forward(request, response);
	}

}

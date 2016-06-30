package khanhnh.demo.ticketapp.sercurity;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class LoginServerlet
 */
@WebServlet("/login")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static final Map<String, String> userdatabase=new Hashtable<String, String>();
    static{
    	userdatabase.put("khanhnh", "khanhlaso1");
    	userdatabase.put("hoannt", "hoancho");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//not login
		HttpSession session=request.getSession();
		
		if(request.getParameter("logout")!=null){
			session.invalidate();
			response.sendRedirect("login");
			return;
		}
			
		if(session.getAttribute("username")!=null){
			response.sendRedirect("tickets");
			return;
		}
		
		request.setAttribute("loginFailed", false);
		request.getRequestDispatcher("/jsp/view/tickets/sercurity/loginPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session=request.getSession();
		if(session.getAttribute("username")!=null){
			response.sendRedirect("tickets");
			return;
		}
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(username==null || password==null || 
				!userdatabase.containsKey(username) ||
				!password.equals(userdatabase.get(username))){
			request.setAttribute("loginFailed", true);
			request.getRequestDispatcher("/jsp/view/tickets/sercurity/loginPage.jsp").forward(request, response);
		}
		else{
			session.setAttribute("username", username);
			request.changeSessionId();
			response.sendRedirect("tickets");
		}
	}

}

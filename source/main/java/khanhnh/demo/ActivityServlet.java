package khanhnh.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActivityServlet
 */
@WebServlet("/do/*")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		recordSessionActivity(request, response);
		viewSessionActivity(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void recordSessionActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		HttpSession session=request.getSession();
		if(session.getAttribute("activity")==null)
			session.setAttribute("activity", new Vector<PageVisit>());
		
		@SuppressWarnings("unchecked")
		Vector<PageVisit> visits=(Vector<PageVisit>)session.getAttribute("activity");
		
		if(!visits.isEmpty()){
			PageVisit last=visits.lastElement();
			last.setLeftTimestamp(System.currentTimeMillis());
		}
		
		PageVisit now=new PageVisit();
		now.setEnteredTimestamp(System.currentTimeMillis());
		if(request.getQueryString()==null)
			now.setRequest(request.getRequestURI().toString());
		else
			now.setRequest(request.getRequestURL()+"?"+request.getQueryString());
		
		try{
			now.setIpAddress(InetAddress.getByName(request.getRemoteAddr()));
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		
		visits.add(now);
	}
	
	private void viewSessionActivity(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		request.getRequestDispatcher("/jsp/view/activity/ViewActititySession.jsp").forward(request, response);
	}
}

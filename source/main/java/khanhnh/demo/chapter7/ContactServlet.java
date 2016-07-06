package khanhnh.demo.chapter7;

import java.io.IOException;
import java.time.Instant;
import java.time.Month;
import java.time.MonthDay;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SortedSet<Contact> contacts=new TreeSet<>();
    static{
    	contacts.add(new Contact("Jane", "Sanders", "555-1593", "394 E 22nd Ave",
    			MonthDay.of(Month.JANUARY, 5),
    			Instant.parse("2013-02-01T15:22:23.00Z")
    			));
    			contacts.add(new Contact( "John", "Smith", "555-0712", "315 Maple St",
    			null, Instant.parse("2012-10-15T09:31:17.00Z")
    			));
    			contacts.add(new Contact("Scott", "Johnson", "555-9834", "424 Oak Dr",
    			MonthDay.of(Month.NOVEMBER, 17),
    			Instant.parse("2013-04-04T19:45:01.00Z")
    			));
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("empty")!=null)
			request.setAttribute("contacts", Collections.<Contact>emptySet());
		else
			request.setAttribute("contacts", contacts);
		request.getRequestDispatcher("/jsp/view/chapter7/Contacts.jsp").forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

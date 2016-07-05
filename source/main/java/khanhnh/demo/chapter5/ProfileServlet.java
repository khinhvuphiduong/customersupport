package khanhnh.demo.chapter5;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet(
		name="profileServlet",
		urlPatterns="/profile"
)
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUserId(19384L);
		user.setUsername("khanhnh");
		user.setFirstName("Khanh");
		user.setLastName("Nguyen Huy");
		
		Hashtable<String, Boolean> permissions=new Hashtable<>();
		permissions.put("user", true);
		permissions.put("moderator", true);
		permissions.put("admin", false);
		user.setPermissions(permissions);
				
		request.setAttribute("user", user);
		request.getRequestDispatcher("/jsp/view/chapter5/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

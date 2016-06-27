package khanhnh.demo;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StoreServlet
 */
@WebServlet("/shop")
//		name="storeServlet",
//		urlPatterns="/shop"
//)
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Map<Integer, String> products=new Hashtable<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.products.put(1, "Drug");
        this.products.put(2, "Grils");
        this.products.put(3,  "Toys");
        this.products.put(4, "Gril Doll");
        this.products.put(5, "Beer");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("Hello");
		String action=request.getParameter("action");
		if(action==null)
			action="browse";
		switch (action) {
		case "viewCart":
			this.viewCart(request, response);
			break;
			
		case "browse":
			this.browseProducts(request, response);
			break;
		default:
			break;
		}
	}

	private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("products", products);
		request.getRequestDispatcher("/jsp/shops/viewCart.jsp").forward(request, response);
		
	}
	
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pId=request.getParameter("productId");
		Integer productId=-1;
		try{
			productId=Integer.parseInt(pId);
		}
		catch(Exception e){
			response.sendRedirect("shop");
			return;
		}
				
		HttpSession session=request.getSession();
		if(session.getAttribute("cart")==null){
			session.setAttribute("cart", new Hashtable<Integer, Integer>());
		}
		
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> cart=(Map<Integer, Integer>)session.getAttribute("cart");
		if(!cart.containsKey(productId))
			cart.put(productId, 0);
//			response.getWriter().println("<script type=\"text/javascript\">alert('This product already have been chosen !');</script>");
		else
			cart.put(productId, cart.get(productId)+1);		
	}
	
	private void browseProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("products", products);
		request.getRequestDispatcher("/jsp/shops/showProducts.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if(action==null)
			action="browse";
		switch (action) {
		case "addCart":
			this.addToCart(request, response);
			break;
			
		case "browse":
			this.browseProducts(request, response);
			break;
		default:
			break;
		}
	}

}

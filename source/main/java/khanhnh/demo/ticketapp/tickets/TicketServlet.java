package khanhnh.demo.ticketapp.tickets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 * Servlet implementation class TicketServlet
 */
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private volatile int TICKET_ID_SEQUENCE = 1;
	private Map<Integer, Ticket> ticketDatabase=new LinkedHashMap<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TicketServlet() {
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
		
		HttpSession session=request.getSession();
		if(session.getAttribute("username")==null)
		{
			response.sendRedirect("login");
			return;
		}
		
		String action = request.getParameter("action");
		if (action == null)
			action = "list";
		switch (action) {
		case "create":
			// show ticket form
			this.showTicketForm(response, request);
			break;
		case "view":
			// show tickets
			this.viewTicket(request, response);
			break;
		case "download":
			// download ticket
			this.downloadTicket(request, response);
			break;
		case "list":
			// show list
			this.listTickets(response, request);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String action = request.getParameter("action");
		if (action == null)
			action="list";
			switch (action) {
			case "create":
				// create ticket
				this.createTicket(request, response);
				break;
			default:
				response.sendRedirect("tickets");
				break;
			}
	}
	
	//done
	private void showTicketForm(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/view/tickets/ticketForm.jsp").forward(request, response);
	}

	private void viewTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("ticketId");
        Ticket ticket = this.getTicket(idString, response);
        if(ticket == null)
            return;
        
        request.setAttribute("ticketId", idString);
        request.setAttribute("ticket", ticket);
        request.getRequestDispatcher("/jsp/view/tickets/viewTicket.jsp").forward(request, response);

	}

	private void downloadTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("ticketId");
        Ticket ticket = this.getTicket(idString, response);
        if(ticket == null)
            return;

        String name = request.getParameter("attachment");
        if(name == null)
        {
            response.sendRedirect("tickets?action=view&ticketId=" + idString);
            return;
        }

        Attachment attachment = ticket.getAttachment(name);
        if(attachment == null)
        {
            response.sendRedirect("tickets?action=view&ticketId=" + idString);
            return;
        }

        response.setHeader("Content-Disposition",
                "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        ServletOutputStream stream = response.getOutputStream();
        stream.write(attachment.getContents());
	}
	
	//done
	private void listTickets(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		request.setAttribute("ticketDatabase", this.ticketDatabase);
		request.getRequestDispatcher("/jsp/view/tickets/listTickets.jsp").forward(request, response);
	}
	
	//done
	private void createTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 Ticket ticket = new Ticket();
	        ticket.setCustomerName(request.getParameter("customerName"));
	        ticket.setSubject(request.getParameter("subject"));
	        ticket.setBody(request.getParameter("body"));

	        Part filePart = request.getPart("file1");
	        if(filePart != null && filePart.getSize() > 0)
	        {
	            Attachment attachment = this.processAttachment(filePart);
	            if(attachment != null)
	                ticket.addAttachment(attachment);
	        }

	        int id;
	        synchronized(this)
	        {
	            id = this.TICKET_ID_SEQUENCE++;
	            this.ticketDatabase.put(id, ticket);
	        }

	        response.sendRedirect("tickets?action=view&ticketId=" + id);
	}

	private Attachment processAttachment(Part filePart) throws IOException {
		InputStream inputStream = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int read;
        final byte[] bytes = new byte[1024];

        while((read = inputStream.read(bytes)) != -1)
        {
            outputStream.write(bytes, 0, read);
        }

        Attachment attachment = new Attachment();
        attachment.setName(filePart.getSubmittedFileName());
        attachment.setContents(outputStream.toByteArray());

        return attachment;
	}

	private Ticket getTicket(String idString, HttpServletResponse response) throws ServletException, IOException {
		if(idString == null || idString.length() == 0)
        {
            response.sendRedirect("tickets");
            return null;
        }

        try
        {
            Ticket ticket = this.ticketDatabase.get(Integer.parseInt(idString));
            if(ticket == null)
            {
                response.sendRedirect("tickets");
                return null;
            }
            return ticket;
        }
        catch(Exception e)
        {
            response.sendRedirect("tickets");
            return null;
        }
	}

}

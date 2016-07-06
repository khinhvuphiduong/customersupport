<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
    pageEncoding="UTF-8"%>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="ticketId" type="java.lang.String" --%>
<%--@elvariable id="ticket" type="khanhnh.demo.ticketapp.tickets.ticket" --%>
<%
	String ticketID=(String)request.getAttribute("ticketID");
	Ticket ticket=(Ticket)request.getAttribute("ticket");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Ticket</title>
</head>
<body>
	<div>
		<a href="
			<c:url value="/login?logout" />
		" >
		Logout !
		</a><br/>
	</div>
	<h2>Ticket: #<%= ticketID %>: <%=ticket.getSubject() %></h2>
	<i>Customer Name - <%=ticket.getCustomerName() %></i><br/><br/>
	<%=ticket.getBody() %><br/><br/>
	<%
		if(ticket.getNumberOfAttachments()>0){
			%>Attachments: <%
			int i=0;
			for(Attachment a: ticket.getAttachments()){
				if(i++>0)
					out.print(", ");
				%>
					<a href="
						<c:url value="/tickets">
							<c:param name="action" value="download"/>
							<c:param name="ticketID" value="<%=ticketID %>"/>
							<c:param name="attachment" value="<%=a.getName() %>"/>
						</c:url>"
					>
					<%=a.getName() %>
					</a>				
				<%
			}
				
		}
	%>
	<br/><a href="<c:url value="/tickets" />"> Return to list tickets</a>
</body>
</html>
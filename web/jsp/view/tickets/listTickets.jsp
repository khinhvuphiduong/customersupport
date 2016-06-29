<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jspf/base.jspf" %>
<%
	@SuppressWarnings("unchecked")
	Map<Integer, Ticket> ticketDatabase=(Map<Integer, Ticket>)request.getAttribute("ticketDatabase");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Tickets</h2>
	<a href="
		<c:url value="/tickets" >
			<c:param name="action" value="create" />
		</c:url>
	" >
	Create Ticket
	</a><br/><br>
	<%
		if(ticketDatabase.size()==0){
			%>
				<i>There are no tickets in the system.</i>
			<%
		}
	else{
		for(int id:ticketDatabase.keySet()){
			String idString=Integer.toString(id);
			Ticket ticket=ticketDatabase.get(id);
			%>
				Ticket #<%=idString %>: 
				<a href="
					<c:url value="/tickets" >
						<c:param name="action" value="view"/>
						<c:param name="ticketId" value="<%=idString %>"/>
					</c:url>
				"><%=ticket.getSubject() %>
				</a>
				(customer: <%=ticket.getCustomerName() %>)<br/>
			<%
		}
	}
	%>
</body>
</html>
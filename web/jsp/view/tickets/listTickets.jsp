<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="ticketDatabase" type="java.util.Map"--%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ticket List</title>
</head>
<body>
	<div>
		<a href="
			<c:url value="/login?logout" />
		" >
		Logout !
		</a><br/>
	</div>
	<h2>Tickets</h2>
	<a href="
		<c:url value="/tickets" >
			<c:param name="action" value="create" />
		</c:url>
	" >
	Create Ticket
	</a><br/><br>
	<c:choose>
		<c:when test="${empty ticketDatabase }">
			<i>There are no tickets in the system.</i>
		</c:when>
		<c:otherwise>
			<c:forEach items="${ticketDatabase}" var="entry" >
			Ticket #${entry.key }<br/>
			<a href="
					<c:url value="/tickets" >
						<c:param name="action" value="view"/>
						<c:param name="ticketId" value="${entry.key }"/>
					</c:url>
				"><c:out value="${entry.value.subject}"/>
				</a>
				(customer: <c:out value="${entry.value.customerName}" /> )
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>
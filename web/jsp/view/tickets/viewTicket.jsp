<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
    pageEncoding="UTF-8"%>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="ticketId" type="java.lang.String" --%>
<%--@elvariable id="ticket" type="khanhnh.demo.ticketapp.tickets.ticket" --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Ticket</title>
</head>
<template:header></template:header>
<body>
	<div>
		<a href="
			<c:url value="/login?logout" />
		" >
		Logout !
		</a><br/>
	</div>
	<h2>Ticket: #${ticketId }: <c:out value="${ticket.subject }" /></h2>
	<i>Customer Name - <c:out value="${ticket.customerName }" /></i><br/><br/>
	<c:out value="${ticket.body }" /><br/><br/>
	<c:if test="${(empty ticket.attachments)==false }">
		Attchment(s)(${fn:length(ticket.attachments) })&nbsp;&nbsp;
		<c:forEach items="${ticket.attachments }" var="attachment" varStatus="status">
			<c:if test="${!status.first }">,</c:if>
			<a href="
					<c:url value="/tickets">
						<c:param name="action" value="download"/>
						<c:param name="ticketID" value="${ticketId }"/>
						<c:param name="attachment" value="${attachment.name }"/>
					</c:url>"
				>
					<i>download&nbsp;<c:out value="${attachment.name }" /></i>
			</a>				
		</c:forEach>
	</c:if>
	<br/><a href="<c:url value="/tickets" />"> Return to list tickets</a>
</body>
<template:footer></template:footer>
</html>
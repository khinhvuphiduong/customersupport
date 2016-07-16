<%--@elvariable id="ticketId" type="java.lang.String" --%>
<%--@elvariable id="ticket" type="khanhnh.demo.ticketapp.tickets.ticket" --%>
<%--@elvariable id="ticketId" type="java.lang.String"--%>
<%--@elvariable id="ticket" type="com.wrox.Ticket"--%>
<%@ include file="/jspf/base.jspf" %>
<template:basic htmlTitle="${ticket.subject}" bodyTitle="Ticket #${ticketId}: ${ticket.subject}">
	<h2>Ticket: #${ticketId }: <c:out value="${ticket.subject }" /></h2>
	<i>Customer Name - <c:out value="${ticket.customerName }" /></i><br/><br/>
	<khanhnh:formatDate value="${ticket.dateCreated }" type="both" timeStyle="long" dateStyle="full"/>
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
</template:basic>
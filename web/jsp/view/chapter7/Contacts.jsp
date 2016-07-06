<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="contacts" type="import java.util.SortedSet<Contact>"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<fmt:message key="title.browser"></fmt:message>
</title>
</head>
<body>
	<h2><fmt:message key="title.page"></fmt:message></h2>
	<c:choose>
		<c:when test="${empty contacts}">
			<fmt:message key="title.brower"></fmt:message>
		</c:when>
		<c:otherwise>
			<c:forEach items="${contacts }" var="contact">
				<b>
					<c:out value="${contact.lastName }, ${contact.firstName }" />
				</b><br/>
				<c:out value="${contact.address}" /><br />
				<c:out value="${contact.phoneNumber}" /><br />
				<c:if test="${contact.birthday != null}">
					<fmt:message key="label.birthday"></fmt:message>
					${contact.birthday.month.getDisplayName('FULL', pageContext.response.locale)}
					&nbsp;${contact.birthday.dayOfMonth}
					<br />
				</c:if>
				<fmt:message key="label.creationDate"></fmt:message>
				&nbsp;<fmt:formatDate value="${contact.oldDateCreated }" type="both" dateStyle="long"
				timeStyle="long"/>
				<br/><br/>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>
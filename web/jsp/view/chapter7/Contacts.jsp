<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="contacts" type="import java.util.SortedSet<Contact>"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Contacts</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty contacts}">
			Your list is empty !
		</c:when>
		<c:otherwise>
			<c:forEach items="${contacts }" var="contact">
				<b>
					<c:out value="${contact.lastName }, ${contact.firstName }" />
				</b><br/>
				<c:out value="${contact.address}" /><br />
				<c:out value="${contact.phoneNumber}" /><br />
				<c:if test="${contact.birthday != null}">
				Birthday: ${contact.birthday}<br />
				</c:if>
				Created: ${contact.dateCreated}<br /><br />
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>
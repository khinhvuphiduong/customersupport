<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%!
	private static String toString(long timeInterval){
		if(timeInterval <1000)
			return "less than one second";
		if(timeInterval<60000)
			return (timeInterval/1000)+ "seconds";
		return "about "+ timeInterval/60000 +" minutes";
	}
%>

<%
	int numberOfSessions = (Integer)request.getAttribute("numberOfSessions");
	@SuppressWarnings("unchecked")
	List<HttpSession> sessions =
	(List<HttpSession>)request.getAttribute("sessionList");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Support</title>
</head>
<body>
	<h2> Sessions</h2>
	<div>
		<a href="
			<c:url value="/login?logout" />
		" >
		Logout !
		</a><br/>
	</div>
	There are a total of <%=numberOfSessions %> active sessions in this application <br/><br/>
	<%
		long timestamp = System.currentTimeMillis();
		for(HttpSession aSession : sessions){
			out.print(aSession.getId() + " - " + aSession.getAttribute("username"));
			if(aSession.getId().equals(session.getId()))
				out.print(" (you) ");
			out.print(" - last activie "+toString(timestamp-aSession.getLastAccessedTime()));
			out.print(" ago <br/>");
		}
	%>
</body>
</html>
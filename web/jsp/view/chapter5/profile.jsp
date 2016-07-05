<%@page import="khanhnh.demo.chapter5.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.stream.Stream, java.util.ArrayList"%>
<%@ include file="/jspf/base.jspf" %>

<!-- @elvarible id="user" type="khanhnh.demo.chapter5" -->

<%
		ArrayList<User> users=new ArrayList<>();
		users.add(new User(19384L, "Coder314", "John", "Smith"));
		users.add(new User(19383L, "geek12", "Joe", "Smith"));
		users.add(new User(19382L, "jack123", "Jack", "Johnson"));
		users.add(new User(19385L, "farmer-dude", "Adam", "Fisher"));
		request.setAttribute("users", users);
	%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> ${user.firstName} Profile</title>
</head>
<body>
	<h3>${user.firstName}'s Profile</h3>
	<hr/>
	User ID: ${user.userId}<br/>
	Username: ${user.username } (${user.username.length() } characters)<br/>
	Full Name: ${fn:escapeXml(user.lastName) += ', ' += fn:escapeXml(user.firstName) }<br/>
	<b>Permissions (${fn:length(user.permissions) })</b><br/>
	User: ${user.permissions["user"] }<br/>
	Moderator: ${user.permissions["moderator"] }<br/>
	Administrator: ${user.permissions["admin"] }<br/>
	
	<hr/>
	${ users.stream()
			.filter(u -> fn:contains(u.username, '1'))
			.sorted(
				(u1, u2) -> (
								x=u1.lastName.compareTo(u2.lastName);
								x ==0 ? u1.firstName.compareTo(u2.firstName) : x
							)
			)
			.map(u ->{'username':u.username})
			.toList()
			}
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" session="false"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jspf/base.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Support</title>
</head>
<template:header></template:header>
<body>
	<div>
		<a href="
			<c:url value="/login?logout" />
		">
		Logout !
		</a><br/>
	</div>
	<form method="POST" action="tickets" enctype="multipart/form-data">
		<input type="hidden" name="action" value="create" />
		<h2>Create a Ticket</h2>
		Your name:<br /> <input type="text" name="customerName" /><br />
		Subject: <br />
		<input type="text" name="subject" /><br /> Body:<br />
		<textarea name="body" rows="5" cols="30"></textarea>
		<br /> <b>Attachments</b><br /> <input type="file" name="file1" /><br>
		<br /> <input type="submit" value="Submit" />
	</form>
</body>
<template:footer></template:footer>
</html>
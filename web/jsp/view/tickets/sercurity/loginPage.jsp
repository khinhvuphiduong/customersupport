<%@page import="javax.swing.text.StyledEditorKit.BoldAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="loginFailed" type="java.lang.Boolean"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>You Login Here !</title>
</head>
<body>
<h3>Login here !</h3>
	<c:if test="${loginFailed}">
		<i>The username or password you entered are not correct. Please try again.</i>
	</c:if>
	<form method="POST" action="login">
	<hr/>
	<div style="margin:0px auto 0px auto;width: 300px;height: 300px;">
		<table style="border: solid black;border-width: 1px 1px 1px 1px;border-collapse:collapse;" cellpadding="5px">
			<tr>
				<td>Username:</td>
				<td>
					<input type="text" name="username" width="200px"></input>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
				<input type="password" name="password" width="200px"></input>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="Login"></input>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>
<%--@elvariable id="loginFailed" type="java.lang.Boolean"--%>

<template:loggedOut bodyTitle="Log In" htmlTitle="Log In">
	<%-- 	<c:if test="${loginFailed}"> --%>
	<!-- 		<i>The username or password you entered are not correct. Please try again.</i> -->
	<%-- 	</c:if> --%>
	<hr />
	<form method="POST" action="login">
		<div style="margin: 0px auto 0px auto; width: auot; height: 300px;">
			<table
				style="border: solid black; border-width: 1px 1px 1px 1px; border-collapse: collapse;" 
				cellpadding="5px">
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" width="200px"></input>
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" width="200px"></input>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit"
						value="Login"></input></td>
				</tr>
			</table>
		</div>
	</form>
</template:loggedOut>
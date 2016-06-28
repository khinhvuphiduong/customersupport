<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your Cart</title>
</head>
<body>
	<a href="
		<c:url value="/shop" >
			<c:param name="action" value="browse"/>
		</c:url>
	"> Back to shop !<br/>
	</a>
	
	<%
		@SuppressWarnings("unchecked")
		Map<Integer, String>products=(Map<Integer, String>)request.getAttribute("products");
		@SuppressWarnings("unchecked")
		Map<Integer, Integer> cart=(Map<Integer, Integer>)session.getAttribute("cart");
		if(cart==null || cart.size()==0)
			out.println("<i>Sorry ! We haven't chosen any product.</i>");
			else{
				%>
				Your products are: <br/>
				<%
				for(int id: cart.keySet()){
					out.println(products.get(id)+" (qty: "+cart.get(id)+")<br/>");
					%>
						<a href="
							<c:url value="/shop" >
								<c:param name="action" value="removeCart"/>
								<c:param name="productId" value="<%=Integer.toString(id) %>"/>
							</c:url>
						"> remove!<br/>
						</a>
					<%
				}
			}	
	%>
</body>
</html>
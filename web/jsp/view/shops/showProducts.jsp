<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	@SuppressWarnings("unchecked")
	Map<Integer, String> products=(Map<Integer, String>)request.getAttribute("products");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
</head>
<body>
	<h2>Product List</h2>
	<a href="
		<c:url value="/shop?action=viewCart" />View Your Cart<br/>
	">
	</a>
	<%
		@SuppressWarnings("unchecked")
		Map<Integer, String> cart=(Map<Integer, String>)session.getAttribute("cart");
		if(products.size()==0){
			%><i>Sorry ! We temporarily haven't any product.</i>
			<%
		}
		else{
			for(int id: products.keySet()){
				%>
					<a href="
						<c:url value="/shop" >
							<c:param name="action" value="addtoCart" />
							<c:param name="productId" value="<%=Integer.toString(id) %>" />
						</c:url>
					"><%=products.get(id) %>
					</a><br/>
				<%
			}
		}
	%>
	<a href="
		<c:url value="/shop" >
			<c:param name="action" value="viewCart"/>
		</c:url>
	"> Go to your cart !<br/>
	</a>
</body>
</html>
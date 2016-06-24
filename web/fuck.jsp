<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%!
	private final int five=0;
	protected String cowboy="rodeo";
	
	public long addFive(long number){
		return number+5L;
	}
	
	MyInnerClass myclass= new MyInnerClass();
	
	public class MyInnerClass{
		
	}
%>

<%
	class WeirdClassWithinMethod{
	}

	WeirdClassWithinMethod weird = new WeirdClassWithinMethod();
	
	MyInnerClass innerClass=new MyInnerClass();
	int seven;
	seven=7;
	
	response.getWriter().println("Fuck Java");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
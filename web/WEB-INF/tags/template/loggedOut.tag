<%@tag pageEncoding="UTF-8"  %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
required="true" %>
<%@ include file="/jspf/base.jspf" %>

<template:main bodyTitle="${bodyTitle }" htmlTitle="${htmlTitle }">
	<jsp:attribute name="headContent">
	<link rel="stylesheet"
			href="<c:url value="/resources/stylesheets/login.css" />" />
	</jsp:attribute>
	<jsp:attribute name="navigationContent">
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</template:main>
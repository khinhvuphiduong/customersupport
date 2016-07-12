<%@tag pageEncoding="UTF-8"  %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="htmlTitle" type="java.lang.String" rtexprvalue="true"
required="true" %>
<%@ attribute name="bodyTitle" type="java.lang.String" rtexprvalue="true"
required="true" %>
<%@ attribute name="headContent" fragment="true" required="false" %>
<%@ attribute name="navigationContent" fragment="true" required="true" %>
<%@ include file="/jspf/base.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<title>Customer Support :: <c:out value="${fn:trim(htmlTitle)}" /></title>
		<link rel="stylesheet"
			href="<c:url value="/resources/stylesheets/main.css" />"
		 />
	<jsp:invoke fragment="headContent" />
</head>
<body>
	<h2>Multinational Widget Corporation</h2>
	<table border="0" id="bodyTable">
		<tbody>
			<tr>
				<td class="sidebarCell">
					<jsp:invoke fragment="navigationContent" />
				</td>
				<td class="contentCell">
					<h3>
						<c:out value="${fn:trim(bodyTitle)}" />
					</h3> 
					<jsp:doBody />
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
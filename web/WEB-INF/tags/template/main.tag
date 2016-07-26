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
			href="<c:url value="/resource/stylesheet/main.css" />"/>
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
		<script type="text/javascript" lang="javascript">
			var postInvisibleForm = function(url, fields) {
				var form = $('<form id="mapForm" method="post"></form>').attr({
					action : url,
					style : 'display: none;'
				});
				for ( var key in fields) {
					if (fields.hasOwnProperty(key))
						form.append($('<input type="hidden">').attr({
							name : key,
							value : fields[key]
						}));
				}
				$('body').append(form);
				form.submit();
			};
			var newChat = function() {
				postInvisibleForm('<c:url value="/chat" />', {
					action : 'new'
				});
			};
		</script>
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
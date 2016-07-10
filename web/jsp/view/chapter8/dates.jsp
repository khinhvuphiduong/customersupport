<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jspf/base.jspf" %>
<%--@elvariable id="date" type="java.util.Date"--%>
<%--@elvariable id="calendar" type="java.util.Calendar"--%>
<%--@elvariable id="instant" type="java.time.Instant"--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Displaying Dates</title>
</head>
<body>
	<b>Date:</b>
	<khanhnh:formatDate value="${date}" type="both" dateStyle="full"/>
	<br/>
	<b>Date, time first with separator:</b>
	<khanhnh:formatDate value="${date}" type="both" dateStyle="full" timeStyle="full" timeFirst="true"
	separateDateTimeWith=" on "/>
	<br/>
	<b>Calendar:</b>
	<khanhnh:formatDate value="${calendar}" type="both" dateStyle="full" timeStyle="full" />
	<br/>
	<b>Instant:</b>
	<khanhnh:formatDate value="${instant}" type="both" dateStyle="full" timeStyle="full" />
	<br/>
	<b>Instant, time first with separator:</b>
	<khanhnh:formatDate value="${instant}" type="both" dateStyle="full" timeStyle="full" timeFirst="true" separateDateTimeWith=" on " />
	<br/>
	<hr/>
	${khanhnh:abbreviateString(shortText, 32)}<br/>
	${khanhnh:abbreviateString(longText, 32) }
</body>
</html>
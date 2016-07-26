<%--@elvariable id="chatSessionId" type="long"--%>
<%@ include file="/jspf/base.jspf" %>
<template:basic htmlTitle="Support Chat" bodyTitle="Support Chat">
	 <jsp:attribute name="extraHeadContent">
        <link rel="stylesheet"
              href="<c:url value="/resource/stylesheet/chat.css" />" />
        <script src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
        <script src="<c:url value="/resource/javascript/chat.js" />"></script>
    </jsp:attribute>
    <jsp:body>
    	<div id="chatContainer">
            <div id="chatLog">
            </div>
            <div id="messageContainer">
                <textarea id="messageArea"></textarea>
            </div>
            <div id="buttonContainer">
                <button class="btn btn-primary" onclick="send();">Send</button>
                <button class="btn" onclick="disconnect();">Disconnect</button>
            </div>
        </div>
        <div id="modalError" class="modal hide fade">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3>Error</h3>
            </div>
            <div class="modal-body" id="modalErrorBody">A blah error occurred.</div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-dismiss="modal">OK</button>
            </div>
        </div>
    </jsp:body>
</template:basic>
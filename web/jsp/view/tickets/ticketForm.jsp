<template:basic htmlTitle="Create a Ticket" bodyTitle="Create a Ticket">
	<form method="POST" action="tickets" enctype="multipart/form-data">
		<input type="hidden" name="action" value="create" />
		<h2>Create a Ticket</h2>
		Your name:<br /> <input type="text" name="customerName" /><br />
		Subject: <br /> <input type="text" name="subject" /><br />
		Body:<br />
		<textarea name="body" rows="5" cols="30"></textarea>
		<br /> 
		<b>Attachments</b><br /> <input type="file" name="file1" /><br>
		<br /> 
		<input type="submit" value="Submit" />
	</form>
</template:basic>
</html>
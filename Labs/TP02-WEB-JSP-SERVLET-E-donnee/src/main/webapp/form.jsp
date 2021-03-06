<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="java.util.*,ch.hevs.businessobject.Client"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Transfer</title>
</head>

<body>
<!-- Action must be changed to suit your script and server path. Change names and values as needed -->
<form method="post" action="BankServlet">
<fieldset><legend><b>Transfer Information:</b></legend>
<blockquote>
<Center>
<table border="0" cellspacing="10" cellpadding="0"
	align="center">
	<tr>
		<td align="left"><B>Source</B></td>
		<td><select name="SRC" size="1">
			<%Iterator i = ((List) request.getAttribute("clients")).iterator();
			while (i.hasNext()) {
				Client c = (Client) i.next();%>
			<option value="<%=c.getLastname()%>"><%=c.getLastname()%></option>
			<%
				}
			%>
		</select></td>
	</tr>
	<tr>
		<td align="left"><B>Destination</B></td>
		<td><select name="DEST" size="1">
			<%
				i = ((List) request.getAttribute("clients")).iterator();
				while (i.hasNext()) {
					Client c = (Client) i.next();
			%>
			<option value="<%=c.getLastname()%>"><%=c.getLastname()%></option>
			<%}%>
		</select></td>
	</tr>
	<tr>
		<td align="left"><B>Amount</B></td>
		<td><input type="text" name="MONTANT" size="3" value="10" /> <input
			type="submit" value="Transfer" /></td>
	</tr>
</table>


</Center>
</blockquote>
</fieldset>
</form>
</body>
</html>

<HTML>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Virement</title>
</head>

<body>
<!-- Action must be changed to suit your script and server path. Change names and values as needed -->
<form method="post" action="BankServlet">
<fieldset><legend><b>Result of the transfer</b></legend>
<blockquote>
<CENTER><%= request.getAttribute("result")  %></CENTER>

</blockquote>
<blockquote>
<CENTER><A HREF="ShowForm">Make another transfer</A></CENTER>
</blockquote>
</fieldset>
</form>
</body>
</html>

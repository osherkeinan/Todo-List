<%@ page isErrorPage="true" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Show Error Page</title>
</head>
<body>
<jsp:include page="nevBar.jsp" />
<h1>Opps...</h1>
<p>Sorry, an error occurred.</p>
<pre>

<%
if(exception != null)
{
	exception.printStackTrace();
}
if(request.getAttribute("error") != null)
{
	out.print(request.getAttribute("error"));
}
%>
<button type="button" name="back" onclick="history.back()">back</button>

</pre>
</body>
</html>
<%@page import="org.apache.catalina.SessionListener"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>ToDo List</title>
</head>
<body>
<jsp:include page="nevBar.jsp"/>
<div class="container" style="max-width:600px;margin:60px auto;">
<%
out.write("Total Active Session(s):" + com.lifemichael.controller.SessionsListenerCounter.getTotalActiveSession() + "\n");
%> <br><% 
out.write("Session(s) Log:" + com.lifemichael.controller.SessionsListenerCounter.getSessionsLog());
%>
</div>
</body>
</html>
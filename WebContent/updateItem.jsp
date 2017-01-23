<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<% 	RequestDispatcher dispatcher = request.getRequestDispatcher("/controller/updateItem"); %>
	<jsp:useBean id="newItem" scope="request" class="com.lifemichael.model.Item">
 	<jsp:setProperty name="newItem" property="toDoDescription" param="description"/>
 	<jsp:setProperty name="newItem" property="eStatus" param="status"/>
 	</jsp:useBean>
 	<%   
 	dispatcher.forward(request, response);
    		%>
</body>
</html>
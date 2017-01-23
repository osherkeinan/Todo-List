<%@page import="java.beans.Visibility"%>
<%@page import="com.lifemichael.model.*"%>
<%@page import="java.util.List"%>
<%@ page errorPage="ShowErrorMsg.jsp" %>
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
<%
	String username = (String)session.getAttribute("username"); //border="1"
	List<Item> items= (List<Item>)request.getAttribute("items");
%>
<div class="container" style="max-width:600px;margin:60px auto;">
<div class="jumbotron text-center">
  <h1>My List</h1>
</div>
<table class="table table-striped">
<tr><th>#</th><th>Item description</th><th>Item status</th><th></th><th></th></tr>
<%
	if(items.isEmpty())
	{
		out.write("You have no Items");
	}
%>
<form method="get" action="/Todo_List/controller/deleteItemOrToUpdate">
<%
	for(Item item : items) {
		
		out.println("<tr><td>" + (items.indexOf(item) + 1) + "." + "</td><td>"+item.getToDoDescription()+"</td><td>"+item.geteStatus()+"</td>");
		 %><td>
			<input type="checkbox" name=<%=items.indexOf(item)%> value=<%=String.valueOf(item.getId())%>>
		</td><td><button type="submit" name="actionOrindex" value=<%=items.indexOf(item)%> class="btn btn-default">EDIT</button></td></tr>
		<% 
		 
	}
%>

</table>
<table>
<th>
<input type="submit" name="actionOrindex" value="DELETE" class="btn btn-danger">
</form>
</th>
<th>
<form name="addButton" id="add" method="get" action="/Todo_List/controller/toAdd">
<button type="submit" name="addButton" class="btn btn-success">ADD</button>
</form>
</th>
</table>
<br>
<%
if(request.getAttribute("toADD") != null)
{
	if((Boolean)request.getAttribute("toADD") == true)
	{
		 %><form name="add" method="get" action="/Todo_List/addItem.jsp" class="form-horizontal">
		 <div class="form-group">
		 <label class="control-label col-sm-2" for="description">
		 description:</label><div class="col-sm-10"><input type="text" name="description" id="description" class="form-control"/>
		 </div>
		 </div>
		 <table><th>
		 <div class="form-group">
		 <label class="control-label col-sm-2" for="status">
		 <div class="col-sm-10">  
		 status:&emsp;&ensp;</label></th><th><select name="status" class="form-control">
	  <option value="PENDING">Pending</option>
	  <option value="INPROGRESS">In Progress</option>
	  <option value="DONE">Done</option>
	</select>
	</th></table>
		 <input type="submit" value="ADD Item" class="btn btn-success"/>
		 </form>
	</div>
		 <% 
	}
}

if(request.getAttribute("toUPDATE") != null)
{
	if((Boolean)request.getAttribute("toUPDATE") == true)
	{
		int index = Integer.parseInt(String.valueOf(request.getAttribute("itemIndex")));
		Item item = items.get(index);
		 %><form name="update" method="get" action="/Todo_List/controller/updateItem" class="form-horizontal">
		 <div class="form-group">
		  <label class="control-label col-sm-2" for="description"><input type="hidden" name="index" value=<%=String.valueOf(index) %>>
		 description: </label><div class="col-sm-10"><input type="text" name="description" id="description" class="form-control" value="<%=item.getToDoDescription()%>"/>
		</div>
		</div>
		 <table><th>
		 <div class="form-group">
		 <label class="control-label col-sm-2" for="status">
		 <div class="col-sm-10">  
		 status:&emsp;&ensp;</label></th><th><select name="status" class="form-control">
	  <option value="PENDING">Pending</option>
	  <option value="INPROGRESS">In Progress</option>
	  <option value="DONE">Done</option>
	</select>
	</th></table>
		 <input type="submit" value="Update Item" class="btn btn-default"/>
		 </form>
		 <% 
	}
}
%>
</div>
</body>
</html>
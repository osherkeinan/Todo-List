<%@page import="org.apache.tomcat.dbcp.pool2.UsageTracking"%>
<%@page import="com.lifemichael.model.*"%>
<%@ page errorPage="ShowErrorMsg.jsp" %>
<%@ page language="java" import="java.util.Enumeration" contentType="text/html; charset=windows-1255"
    pageEncoding="UTF-8"%>
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
<jsp:include page="nevBar.jsp" />
<div class="container" style="max-width:600px;margin:60px auto;">
<div class="jumbotron text-center">
  <h1>Sign Up</h1>
</div>
<form method="post" action="/Todo_List/controller/checkRegister" class="form-horizontal">
<div class="form-group">
<label class="control-label col-sm-2" for="username">
username:</label><div class="col-sm-10"><input type="text" name="username" id="username" class="form-control" placeholder="Enter username"/>
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="password">Password:</label>
<div class="col-sm-10">  
<input type="password" name="password" id="password" class="form-control"  placeholder="Enter password"/>
 </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <div class="checkbox">
          <label><input type="checkbox"> Remember me</label>
        </div>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
<input type="submit" value="Sign Up" class="btn btn-primary"/> <input type="reset" value="reset" class="btn btn-default"/>
   </div>
  </div>
</form>
</div>
</body>
</html>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">ToDo List</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/Todo_List/controller/home">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Menu <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/Todo_List/controller/about">About</a></li>
          <%if(request.getSession().getAttribute("username") != null)
          {
        	  %><li><a href="/Todo_List/controller/deleteUser">Delete User</a></li>
        	  <li><a href="/Todo_List/controller/toUpdatePassword">Update Password</a></li><%
      				    if(request.getSession().getAttribute("username").equals("admin"))
        			    {%>
          					<li><a href="/Todo_List/controller/admin">Admin</a></li>
          				<%} 
          			}%>
        </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <%if(request.getSession().getAttribute("username") != null)
    {
    	%><li><a href="#"><span class="glyphicon glyphicon-user"></span>Hello <%=request.getSession().getAttribute("username")%></a></li>
    <%}
    else
    {
      %><li><a href="/Todo_List/controller/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <%}
      if(request.getSession().getAttribute("username") != null)
      {%>
      		<li><a href="/Todo_List/controller/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      <%}else
    	  {
    	  %>
    	  <li><a href="/Todo_List/controller/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    	  <%}%>
    </ul>
  </div>
</nav>
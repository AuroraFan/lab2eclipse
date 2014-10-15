<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="lab2.Book" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>UpdateInfromation</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%String title = request.getParameter("booktitle");%>
    <h1><%=title %></h1>
    <h3>new information as follow:<h3>
     <form action = "Action_update" method = "post">
     <table>
     	<tr>
     		<th>author:</th><td><input class="input-large search-query" type="text" name="name"/></td>
     	</tr>
     	<tr>
     		<th>publisher:</th><td><input class="input-large search-query" type="text" name="publisher"/></td>
     	</tr>
     	<tr>
     		<th>publishdate:</th><td><input class="input-large search-query" type="text" name="publishdate"/></td>
     	</tr>
     	<tr>
     		<th>price:</th><td><input class="input-large search-query" type="text" name="price"/></td>
     	</tr>
     </table>
		<input type="hidden" name="title" value="<%=title%>" />
		<button type="submit" class="btn btn-primary btn-medium" value = "search">UPDATE</button>
		
	</form>
    <a href = "index.jsp">back to front page</a>
  </body>
</html>

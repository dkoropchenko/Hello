<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit content</title>
</head>
<body>
<form action="hello">
Edit content. Type content:
<input type="hidden" name="action" value="edit" >
<input type="hidden" name="id" value=<%= request.getParameter("id") %>>
<input type="text" name="content" value=<%= request.getParameter("content") %>>
<input type="submit" value="Edit">
</form>
<a href="hello?action=child&parent_id=<%=request.getParameter("parent") %>">Cancel</a>
</body>
</html>
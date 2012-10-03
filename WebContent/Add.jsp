<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new content</title>
</head>
<body>
<form action="hello">
Add new content. Type content:
<input type="hidden" name="action" value="add" >
<input type="text" name="content" value="content">
<input type="text" name="parent" value="5" >
<input type="submit" value="Add">
</form>
<a href="hello">Home</a>
</body>
</html>
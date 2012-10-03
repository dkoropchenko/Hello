<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.dkoropchenko.hello.model.obj.HelloObj"%>
<%@page import="java.util.List"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Simple Servlet Hello</title>
	</head>
	<body>
        	<table frame="below" width="100%">
  				<tr>
				    <th align="left">Key</th>
				    <th align="left">Value</th>

				</tr>
				<%
				  List<HelloObj> map;
				  Object obj = request.getSession().getAttribute("test");
				  if (obj instanceof List) {
					  map = (List<HelloObj>) obj;
				  }
				  else {
					  map = null;
				  }
				  if (map != null) {
				  	for (HelloObj entry: map) {
				%>
				  <tr>
				    <td width="100"><%=entry.getId()%></td>
				    <td width="100"><%=entry.getName()%></td>
				    <td width="100"><%=entry.getParentId()%></td>
				    <td width="100">
				    	<form action="hello">
				    		<input type="hidden" name="action" value="delete">
				    		<input type="hidden" name="id" value=<%= entry.getId() %>>
				    		<input type="submit" value="Delete">
				    	</form>
				    </td>
				    <td width="100">
				    	<form action="Edit.jsp">
				    		<input type="hidden" name="id" value=<%=  entry.getId() %>>
				    		<input type="hidden" name="content" value=<%=  entry.getName() %>>
				    		<input type="submit" value="Edit">
				    	</form>
				    </td>
				  </tr>
				<%
				  	}
				  }
				%>  
			</table>
			<form action="Add.jsp">
				<input type="submit" value="Add"> 
			</form>
	</body>
</html>
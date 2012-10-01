<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Map"%>
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
				  Map<String,String> map;
				  Object obj = request.getSession().getAttribute("test");
				  if (obj instanceof Map) {
					  map = (Map<String,String>) obj;
				  }
				  else {
					  map = null;
				  }
				  if (map != null) {
				  	for (Map.Entry<String,String> entry: map.entrySet()) {
				%>
				  <tr>
				    <td width="100"><%=entry.getKey()%></td>
				    <td width="100"><%=entry.getValue()%></td>
				    <td width="100">
				    	<form action="hello">
				    		<input type="hidden" name="action" value="delete">
				    		<input type="hidden" name="id" value=<%= entry.getKey() %>>
				    		<input type="submit" value="Delete">
				    	</form>
				    </td>
				    <td width="100">
				    	<form action="Edit.jsp">
				    		<input type="hidden" name="id" value=<%=  entry.getKey() %>>
				    		<input type="hidden" name="content" value=<%=  entry.getValue() %>>
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
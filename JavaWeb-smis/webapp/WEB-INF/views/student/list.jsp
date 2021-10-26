<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Student Info Table</h1>
	<a href="/student?cmd=edit">Add</a>
	<table cellpadding="0" cellspacing="0" width="60%" border="1">
		<tr bgcolor="yellow">
			<th>ID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Action</th>
		</tr>
		<!-- Data formatting
		<tr>
			<td>1</td>
			<td>Bill</td>
			<td>23</td>
			<td><a href="#">Delete</a>|<a href="#">Edit</a></td>
		</tr>-->
		<c:forEach items="${list}" var="item" varStatus="vs">
			<tr bgcolor=${vs.count%2==0?'pink':'white'}>
				<td>${pageScope.item.id}</td>
				<td>${pageScope.item.name}</td>
				<td>${pageScope.item.age}</td>
				<td><a href="/student?cmd=delete&id=${pageScope.item.id}">Delete</a>|<a href="/student?cmd=edit&id=${pageScope.item.id}">Edit</a></td>
			<tr/>
		</c:forEach>
	</table>
</body>
</html>
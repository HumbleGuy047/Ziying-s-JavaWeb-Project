<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${stu==null?"Add":"Change"} Student</h2>
	<form action="/student?cmd=saveOrUpdate" method="post">
		<input type="hidden" name="id" value="${stu.id}">
		Name: <input type="text" name="name" value="${stu.name}"/>
		Age: <input type="number" name="age"  value="${stu.age}"/>
		<input type="submit" value='${stu==null?"Add":"Change"}'/>
	</form>
</body>
</html>
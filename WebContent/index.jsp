<%@page import="dao.DepartmentDAOImpl"%>
<%@page import="java.util.List"%>
<%@page import="dao.EmploymentDAOImpl"%>
<%@page import="models.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Patron DAO</h1>
	<h3>Listado Empleados</h3>
	<ul>
		<jsp:useBean id="empls" class="dao.EmploymentDAOImpl"></jsp:useBean>
		<c:forEach items="${empls.employments}" var="employer">
		<li> 
		<a href="SaveUpdateController?idEmplUpdate=${employer.id}">
		<c:out value="${employer.name}"> </c:out>
		</a>
		</li>
		</c:forEach>	
	</ul>
	
		<form action="SaveUpdateController" method="post">
		<label for="name"> Name:</label>
		<input type="text" id="name" name="name" required>
		<input type="hidden" value="" name="id"> 
		<br>
		<label for="department">Department</label>
		<select id="department" name="departmentId" requiered>
			
			<option>
			</option>
			<jsp:useBean id="dpto" class="dao.DepartmentDAOImpl"></jsp:useBean>
			<c:forEach items="${dpto.departments}" var="department">
			<option value="${department.departmentId}"> <c:out value="${department.departmentName}"> </c:out> </option>
			</c:forEach>
			
		</select>
		<br/>
		<label>Gender</label>
		<br>
		<label for="male"> Male</label>
		<input type="radio" id="male" name="gender" value="MALE" required>
		<label for="female"> Female</label>
		<input type="radio" id="female" name="gender" value="FEMALE" required>
		<br>
		<input type="submit" value="Create Employer" > 
		</form>
</body>
</html>
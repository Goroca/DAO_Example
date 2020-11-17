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
	<% 
		EmploymentDAOImpl daoEmployment = new EmploymentDAOImpl();
		List<Employment> employments = daoEmployment.list();
		
		for (Employment e : employments){
			%>
				<li><%= e.getName() %></li>
			
			<%
		}
		
	
	%>
	</ul>
	
		<form>
		<label for="name"> Name:</label>
		<input type="text" id="name" name="name" required> 
		<br>
		<label for="dpto">Department</label>
		<select id="dpto" name="department">
			
			<jsp:useBean id="dpto" class="dao.DepartmentDAOImpl"></jsp:useBean>
			<option>
			</option>
			
			<c:forEach items="${dpto.departments}" var="department">
			<option>
				<c:out value="${department.departmentName}"> </c:out>
			</option>
			</c:forEach>
			
			
		
		</select>
		<br/>
		<label>Gender</label>
		<br>
		<input type="radio" name="gender" value="MALE">
		<input type="radio" name="gender" value="FEMALE">	
		</form>
</body>
</html>
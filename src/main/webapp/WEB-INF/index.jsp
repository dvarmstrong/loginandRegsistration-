<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="nav">
	
		<h1>Welcome</h1>
		<p>Join our growing community !</p>
		<form:form action="/login" methods="post" modelAttribute="user">
			<p>	
				<form:label path="email">Email</form:label>
				<form:errors path="email"/>
				<form:input path="email"/>
			</p>
			<p>	
				<form:label path="password">Password</form:label>
				<form:errors path="password"/>
				<form:password path="password"/>
			</p>
			<input type="submit" value="Login"/>
		</form:form>
	</div>
			
		
	<div class="container">
		<h1>Register</h1>
		<form:form action="/register" methods="post" modelAttribute="user">
		 	<p>	
				<form:label path="userName">User Name</form:label>
				<form:errors path="userName"/>
				<form:input path="userName"/>
			</p>
			<p>	
				<form:label path="email">Email</form:label>
				<form:errors path="email"/>
				<form:input path="email"/>
			</p>
			<p>	
				<form:label path="password">Password</form:label>
				<form:errors path="password"/>
				<form:password path="password"/>
			</p>
			<p>	
				<form:label path="confirm">Confirm Password</form:label>
				<form:errors path="confirm"/>
				<form:password path="confirm"/>
			</p>
			<input type="submit" value="Submit"/>
		</form:form>
	</div>
	
   
</body>
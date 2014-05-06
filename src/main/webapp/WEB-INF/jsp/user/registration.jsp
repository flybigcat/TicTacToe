<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Registration</title>
</head>

<body>
	<form:form modelAttribute="user">
		<table border='1'>

			<tr>
				<th>Username:</th>
				<td><form:input path="username" /> <span style="color: red;"><form:errors
							path="username" /></span></td>
			</tr>

			<tr>
				<th>Password:</th>
				<td><form:password path="password" /> <span style="color: red;"><form:errors
							path="password" /></span></td>
			</tr>

			<tr>
				<th>Email:</th>
				<td><form:input path="email" /> <span style="color: red;"><form:errors
							path="email" /></span></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" name="register"
					value="register" /></td>
			</tr>

		</table>
	</form:form>
	 <p>
	<a href="login.html">Login</a>
	</p>
</body>
</html>
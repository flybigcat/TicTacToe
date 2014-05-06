<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Tic Tac Toe</title>
</head>
<body>

	<h3>Tic Tac Toe</h3>

	<p>
		<c:if test="${empty end}">
Please make your move:
</c:if>

		<c:if test="${not empty end}">
			<c:if test="${end eq 1}">
You won!
</c:if>
			<c:if test="${end eq 0}">
Game tied!
</c:if>
			<c:if test="${end eq 2}">
I won!
</c:if>
		</c:if>
	</p>

	<table border='1'>

		<c:forEach begin="0" end="2" step="1" var="row">
			<tr>
				<c:forEach begin="0" end="2" step="1" var="column">

					<c:if test="${empty end}">
						<c:if test="${tttGame.board[3*row+column] eq 0}">
							<td><a href="TicTacToeGame.html?i=${3*row+column}">_</a></td>
						</c:if>
					</c:if>

					<c:if test="${not empty end}">
						<c:if test="${tttGame.board[3*row+column] eq 0}">
							<td>_</td>
						</c:if>
					</c:if>

					<c:if test="${tttGame.board[3*row+column] eq 1}">
						<td style="color: rgb(0, 0, 255)">X</td>
					</c:if>

					<c:if test="${tttGame.board[3*row+column] eq 2}">
						<td style="color: rgb(255, 0, 0)">O</td>
					</c:if>

				</c:forEach>

			</tr>
		</c:forEach>


	</table>


	<p>
		<a href="TicTacToeGame.html?newGame=1">New Game</a>
	</p>

	<p>
		<a href="/ttt/user/logout.html">Logout</a>
	</p>

</body>
</html>
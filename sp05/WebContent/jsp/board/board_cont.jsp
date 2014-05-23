<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mybatis 내용보기</title>
</head>
<body>
	<table align="center" border="1">
		<tr>
			<th colspan="2">mybatis 내용보기</th>
		</tr>
		<tr>
			<th>이름</th>
			<td>${bc.name}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${bc.addr}</td>
		</tr>
		<tr>
			<th>자기소개</th>
			<td>${cont}</td>
		</tr>
		<tr>
			<th colspan="2">
			<input type="button" value="수정" onclick="location='board_cont.do?no=${bc.no}&state=edit'" /> 
			<input type="button" value="삭제" onclick="location='board_cont.do?no=${bc.no}&state=del'" /> 
			<input type="button" value="수정" onclick="location='board_lsit.do'" />
			</th>
		</tr>
	</table>
</body>
</html>
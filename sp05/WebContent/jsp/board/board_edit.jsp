<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- JSTL 태그라이브러리 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링&jdbc&mybatis</title>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/bbs.js"></script>
</head>
<body>
	<form method="post" action="board_write_ok.do"
		onsubmit="return check();">
		<input type="hidden" name="no" value="${bc.no}" />
		<table align="center" border="1">
			<tr>
				<th colspan="2">spring@jdbc&mybatis</th>
			</tr>
			<tr>
				<th>이름</th>
				<td><input name="name" id="name" size="14" value="${bc.name}"/></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input name="addr" id="addr" size="36" value="${bc.addr}"/></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pass" id="pass" size="14" /></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="gender" value="남" 
				    <c:if test="${bc.gender=='남'}"> ${'checked'}</c:if> />남자 
				    <input type="radio" name="gender" value="여"
				    <c:if test="${bc.gender=='여'}"> ${'checked'}</c:if> />여자 
				    <%-- radio는 단 하나만 선택가능하다. checked속성을 사용하면 미리 체크됨. --%>
				</td>
			</tr>
			<tr>
				<th>사는 지역</th>
				<td>
				<select name="city">
						<c:forEach var="city" items="${c}">
						   <option value="${city}"
						   <c:if test="${bc.city == city}" > ${'selected'}</c:if>> 
							${city}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td><textarea name="cont" id="cont" rows="8" cols="34">${bc.cont}</textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
				<input type="submit" value="수정" /> 
				<input type="reset" value="취소" onclick="$('#name').focus();" />
				</th>
			</tr>
		</table>
</body>
</html>
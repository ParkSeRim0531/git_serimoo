<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시큐리티 테스트</title>
</head>
<body>
	<h1>Admin 권한을 가진 사용자를 위한 화면입니다.</h1>
	<h2>
		로그인 id : 
		<sec:authentication property="principal.users.id" /><br>
		<sec:authorize access="!isAuthenticated()">
		Login <br>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		Logout<br>
		</sec:authorize>
		권한<br>
		<sec:authentication property="principal.users.role" /><br>
	</h2>
	<a href="/loginSuccess">뒤로 가기</a>
</body>
</html>
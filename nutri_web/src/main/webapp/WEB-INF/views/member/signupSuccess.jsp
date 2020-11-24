<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="..//includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>
<div style="margin: 0 auto;">
   <div style="margin-top: 10%; margin-left: 40%;">
      <a><img src="${contextPath}/resources/images/DRSLUMP.jpg"   style="height: 200px; margin-left: 10px;" /></a> <br>
      <br>
      <h3>안녕하세요, ${param.nickname} 님</h3>
      <br>
      <p>환영합니다!</p>
      <br>
      <p>회원가입이 정상적으로 이루어 졌습니다.</p>
      <br>
      <p>로그인 하시면 홈페이지 내의 모든 서비스를 이용하실 수 있습니다.</p>
      <br> <a href="${contextPath}/member/login">로그인 페이지로 이동하기</a>
   </div>
</div>

</body>
</html>
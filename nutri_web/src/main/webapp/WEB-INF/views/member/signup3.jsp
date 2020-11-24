<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath }" scope="application"/>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12 text-center">
				<div class="col-sm-3"></div>

				<div class="col-sm-6">
					<h2>회원가입</h2>
					<form action="${contextPath }/member/signup" method="post">
						<table class="table table-boardered">
							<tr>
								<th>아이디</th>
								<td><input type="text" class="form-control" name="email"
									placeholder="email로 적으세요"></td>
							</tr>
							<tr>
								<th>패스워드</th>
								<td><input type="password" class="form-control"
									name="passwd" placeholder="비밀번호는 영문만 넣어주세요"></td>
							</tr>

							<tr>
								<th>패스워드확인</th>
								<td><input type="password" class="form-control"
									name="passwd2"></td>
							</tr>

							<tr>
								<th>닉네임</th>
								<td><input type="text" class="form-control" name="nickname"></td>
							</tr>

							<tr>
								<th>출생년도</th>
								<td><input type="text" class="form-control"
									name="dateofbirth"></td>
							</tr>

							<tr>
								<th>당신의 성별은</th>
								<td><input type="radio" name="gender" value="0">남자	&nbsp;&nbsp; 
								<input type="radio" name="gender" value="1">여자	&nbsp;&nbsp;</td>
							</tr>

							<tr>
								<td colspan="2">
									<input type="submit" class="btn btn-primary" value="전송"> 
									<input type="reset" class="btn btn-danger" value="취소">
								</td>
							</tr>


						</table>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="..//includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
		
	//모든 공백 체크 정규식
	var empJ = /\s/g;
	// 비밀번호 정규식 A~Z, a~z, 0~9로 시작하는 4~12자리 비밀번호	
	var idJ = /^[a-z0-9]{4,12}$/;
	
	//닉네임 정규식 닉네임 체크 - 특수문자 제외 모든 문자 2자 ~ 15자
	var nickJ = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,15}$/;	
	
	 $(function(){
		if($("#pwForm").submit(function(){
			if($("#passwd").val() !== $("#passwd2").val()){
				alert("비밀번호가 다릅니다.");
				$("#passwd").val("").focus();
				$("#passwd2").val("");
				return false;
			} else if ($("#passwd").val().length < 4) {
				alert("비밀번호는 4자 이상으로 설정해야 합니다.");
				$("#passwd").val("").focus();
				return false;
			} else if($.trim($("#passwd").val()) !== $("#passwd").val()){
				alert("공백은 입력이 불가능합니다.");
				return false;
			}
		}));
		if($("#wdForm").submit(funtion(){
			if(!confirm("정말 탈퇴할까요?")){
				return false;
			}
			
		}));
	}) 
</script>
<title>마이페이지</title>
</head>
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath }" scope="application"/>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>내 정보</h3>
			</div>
			<div>
				<form id="myForm" action="${contextPath }/member/update_mypage" method="post">
					<p>
						<label>Email</label> 
						<input class="w3-input" type="text" id="email" name="email"  value="${login.email }" readonly="readonly" > 
					</p>	
					<p>
						<label>닉네임</label> 
						<input class="w3-input" type="text" id="nickname" name="nickname" value="${login.nickname }"  > 
					</p>									
					<p>
						<label>성별</label> 
						<input class="w3-radio" type="radio" name="gender" value="0">남자	&nbsp;&nbsp; 
						<input class="w3-radio" type="radio" name="gender" value="1">여자	&nbsp;&nbsp;
					</p>
					<p>
						<label>출생년도</label> 
						<input class="w3-input" type="text" id="dateofbirth" name="dateofbirth" value="${ member.dateofbirth }" > 
					</p>
					<p class="w3-center">
						<button type="submit" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">회원정보 변경</button>
					</p>
					 <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
				</form>
				<br />
				<form id="pwForm" action="${contextPath }/member/update_pw" method="post">	
					<!-- email은 가져오는 값으로  hidden으로 설정해야함.  -->
					<input type="hidden" name="email" value="${login.email }">
					<p>
						<label>기존  비밀번호</label>
						<input class="w3-input" id="old_pw" name="old_pw" type="password" required>
					</p>
					<p>
						<label>새  비밀번호</label> 
						<input class="w3-input" id="passwd" name="passwd" type="password" required>
					</p>
					<p>
						<label>비밀번호 확인 </label>
						<input class="w3-input" type="password" id="passwd2" type="password" required>
					</p>
					<p class="w3-center">
						<button type="submit" id="joinBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">비밀번호 변경</button>
					</p>
					 <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
				</form>
				<br />
				<form id="wdForm" action="${contextPath }/member/delmember" method="post">
					<input type="hidden" name="email" readonly="readonly" value="${login.email }">
					<p>
						<label>비 밀 번 호</label>
						<input class="w3-input" type="password" name="passwd" required="required">
					</p>
					<p class="w3-center">
						<button type="submit" id="withdrawalBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">회원탈퇴</button>
					</p>
					<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
				</form>
			</div>
		</div>
	</div>
</body>
</html>
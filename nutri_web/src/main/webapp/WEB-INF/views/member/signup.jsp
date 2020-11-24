	<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="..//includes/header.jsp" %>
<body class="bg-gradient-primary">
  <div class="container" style="max-width: 900px;">

    <div class="card o-hidden border-0 shadow-lg my-5">
      <div class="card-body p-0">
        <!-- Nested Row within Card Body -->
        <div class="row">
          
          <div class="col-lg-12">
            <div class="p-5">
              <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">회 원 가 입</h1>
              </div>
              <form class="user" action="${contextPath }/member/signup" method="post">
	                <div class="form-group">
	                  <input type="text" class="form-control form-control-user" id="email" name="email" placeholder="Email">
	                  <div class="check_font" id="email_check"></div>	                 
	                </div>	
	                <div class="form-group">
	                   <input type="password" class="form-control form-control-user" id="passwd" name="passwd" placeholder="Password">
	                </div>
	                <div class="form-group">
	                  <input type="password" class="form-control form-control-user" id="repasswd" name="repasswd" placeholder="Repeat Password">
	                </div>
	                <div class="form-group">
	                  <input type="text" class="form-control form-control-user" id="nickname" name="nickname" placeholder="닉네임">
	                  <div class="check_font" id="nickname_check"></div>
	                </div>
	                <div class="form-group">
	                   <input type="number" class="form-control form-control-user" id="dateofbirth" name="dateofbirth" placeholder="출생년도">
	                   <div class="check_font" id="birth_check"></div>	
	                </div>	               
	                <div class="form-group">
	                   	<h4>성별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                   	<input type="radio" id="man" name="gender" value="0" ><label for="man" style="font-size: 0.7em">남성</label>&nbsp;&nbsp; 
							<input type="radio" id="woman" name="gender" value="1" ><label for="woman" style="font-size: 0.7em">여성</label>&nbsp;&nbsp;
						</h4>
	                </div>	               
	                <button type="submit" id="reg_submit" class="btn btn-primary btn-user btn-block">
	                 	등  록
	                </button>
	               	 <a class="btn btn-primary btn-user btn-block" type="button" href="${contextPath }" style="background-color: red; border-color: red;">
		                                 취 소
	                 </a>
	                 <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
             	</form>
              <hr>
              <div class="text-center">
                <a class="small" href="${contextPath }/member/pwFind">비밀번호를 잊으셧나요?</a>
              </div>
              <div class="text-center">
                <a class="small" href="${contextPath }/member/login">회원이신가요? 로그인! </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
  

</body>
<script>
//모든 공백 체크 정규식
var empJ = /\s/g;
//아이디 정규식 a~z, 0~9 로 시작하는 4~12자리 아이디
var idJ = /^[a-z0-9]{4,12}$/;
// 비밀번호 정규식 A~Z, a~z, 0~9로 시작하는 4~12자리 비밀번호
var pwJ = /^[A-Za-z0-9]{4,12}$/; 
// 이름 정규식
var nameJ = /^[가-힣]{2,6}$/;
// 이메일 검사 정규식
var mailJ = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;


// 휴대폰 번호 정규식
var phoneJ = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;

//닉네임 정규식 닉네임 체크 - 특수문자 제외 모든 문자 2자 ~ 15자
var nickJ = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,15}$/;


//이메일 유효성 검사(1 = 중복 / 0 != 중복)
$("#email").blur(function() {
	
	var email = $('#email').val();
	
	$.ajax({
		url : '${contextPath}/member/emailCheck?email='+ email,
		type : 'get',
		success : function(data) {
			console.log("1 = 중복o / 0 = 중복x : 이메일=> "+ data);							
			
			if (data == 1) {
					// 1 : 이메일 중복되는 문구
					$("#email_check").text("사용중인 이메일입니다. ");
					$("#email_check").css("color", "red");
					$("#reg_submit").attr("disabled", true);
				} else {
					
					if(mailJ.test(email)){
						// 0 : 이메일 형식/공백  검사
						$("#email_check").text("");
						$("#reg_submit").attr("disabled", false);
			
					} else if(!(empJ.test(email))){							
						$('#email_check').text('이메일을 입력해주세요 ');
						$('#email_check').css('color', 'red');
						$("#reg_submit").attr("disabled", true);						
					} else {							
						$('#email_check').text("잘못된 형식의 이메일입니다.");
						$('#email_check').css('color', 'red');
						$("#reg_submit").attr("disabled", true);
					}
					
				}
			}, error : function() {
					console.log("실패");
			}
		});
	});


// 닉네임 유효성 검사(1 = 중복 / 0 != 중복)
	$("#nickname").blur(function() {
		
		var nickname = $('#nickname').val();
		
		$.ajax({
			url : '${contextPath}/member/nickCheck?nickname='+ nickname,
			type : 'get',
			success : function(data) {
				console.log("1 = 중복o / 0 = 중복x : 닉네임=> "+ data);							
				
				if (data == 1) {
						// 1 : 닉네임이 중복되는 문구
						$("#nickname_check").text("사용중인 닉네임입니다. ");
						$("#nickname_check").css("color", "red");
						$("#reg_submit").attr("disabled", true);
					} else {
						
						if(nickJ.test(nickname)){
							// 0 : 닉네임 길이 / 문자열 검사
							$("#nickname_check").text("");
							$("#reg_submit").attr("disabled", false);
				
						} else if(!(empJ.test(nickname))){							
							$('#nickname_check').text('닉네임을 입력해주세요.');
							$('#nickname_check').css('color', 'red');
							$("#reg_submit").attr("disabled", true);				
							
						} else {							
							$('#nickname_check').text("닉네임은 특수문자를 제외한  2~15자리만 가능합니다. ");
							$('#nickname_check').css('color', 'red');
							$("#reg_submit").attr("disabled", true);
						}
						
					}
				}, error : function() {
						console.log("실패");
				}
			});
		});
		
		// 생일 유효성 검사
		var birthJ = false;
		
		// 생년월일	birthJ 유효성 검사
		$('#dateofbirth').blur(function(){
			var dateStr = $(this).val();
			var today = new Date();
		    var yearNow = today.getFullYear(); // 올해 연도 가져옴
			
		    if (dateStr.length <= 4) {
					// 연도의 경우 1900 보다 작거나 yearNow 보다 크다면 false를 반환합니다.
				    if (dateStr == ""){
				    	$('#birth_check').text('출생연도를 입력하세요 ');
						$('#birth_check').css('color', 'red');
				    	
				    } else if(1900 > dateStr || dateStr > yearNow){
				    	$('#birth_check').text('생년월일을 확인해주세요 ');
						$('#birth_check').css('color', 'red');
				    } else{			    	
				    	$('#birth_check').text(''); 
						birthJ = true;
					}//end of if	
				
		    } else {
				//1.입력된 생년월일이 4자 초과할때 :  auth:false
				console.log(dateStr);
				$('#birth_check').text('출생연도는 4자리를 초과할 수 없습니다. ');
				$('#birth_check').css('color', 'red');  
				}
			}); //End of method /*
		
		// 가입하기 실행 버튼 유효성 검사!
		var inval_Arr = new Array(5).fill(false);
		$('#reg_submit').click(function(){
			
			
			// 이메일 정규식
			if (mailJ.test($('#email').val())){
				console.log(mailJ.test($('#email').val()));
				inval_Arr[0] = true;
			} else {
				inval_Arr[0] = false;
				alert('이메일을 확인하세요');
				return false;
			}
			
			
			// 비밀번호가 같은 경우 && 비밀번호 정규식
			if (($('#passwd').val() == ($('#repasswd').val()))
					&& pwJ.test($('#passwd').val())) {
				inval_Arr[1] = true;
			} else {
				inval_Arr[1] = false;
				alert('비밀번호를 확인하세요');
				return false;
			}
			
			// 닉네임 정규식
			if (nickJ.test($('#nickname').val())) {
				inval_Arr[2] = true;	
			} else {
				inval_Arr[2] = false;
				alert('닉네임을 확인하세요');
				return false;
			}
			
			// 출생연도 유효성
			if (birthJ) {
				console.log(birthJ);
				inval_Arr[3] = true;
			} else {
				inval_Arr[3] = false;
				alert('출생연도를 확인하세요');
				return false;
			}
		
			// 성별 체크 확인 
			if ($('input:radio[name=gender]').is(':checked')) {
				inval_Arr[4] = true;
			} else {
				inval_Arr[4] = false;
				alert('성별을 선택하세요');
				return false;
			}
			
			
			var validAll = true;
			for(var i = 0; i < inval_Arr.length; i++){
				
				if(inval_Arr[i] == false){
					validAll = false;
				}
			}
			
			if(validAll){ // 유효성 모두 통과
				alert('인증메일이 발송되었으니 확인 후 로그인 바랍니다.');			
			} else{
				alert('입력한 정보들을 다시 한번 확인해주세요! ')
				return false;
			}
		});		
		
</script>
</html>

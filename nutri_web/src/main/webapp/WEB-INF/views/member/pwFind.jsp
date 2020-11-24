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
                <h1 class="h4 text-gray-900 mb-4">비 밀 번 호 찾 기</h1>
              </div>
              <form class="user" action="${contextPath }/member/pwFindPost" method="post">
	                <div class="form-group">
	                  <input type="text" class="form-control form-control-user" id="email" name="email" placeholder="가입한 Email을 입력해 주세요">
	                  <div class="check_font" id="email_check"></div>	                 
	                </div>	
	                <button type="submit" id="reg_submit" class="btn btn-primary btn-user btn-block">
	                 		 확인
	                </button>
	               	 <a class="btn btn-primary btn-user btn-block" type="button" href="${contextPath }" style="background-color: red; border-color: red;">
		                      	취소
	                 </a>
	                  <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
             	</form>
              <hr>              
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
// 이메일 검사 정규식
var mailJ = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
$('#reg_submit').click(function(){
		//이메일 정규식
		if (mailJ.test($('#email').val())){
			$("#email_check").text("");
			//return true;	
		} else if(!(empJ.test(email))) {
			$('#email_check').text('이메일을 입력해주세요 ');
			$('#email_check').css('color', 'red');
			return false;		
		} else {	
			$('#email_check').text("잘못된 형식의 이메일입니다.");
			$('#email_check').css('color', 'red');
			return false;		
		}

});	
</script>
</html>

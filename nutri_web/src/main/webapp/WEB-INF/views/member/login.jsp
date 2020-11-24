<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="..//includes/header.jsp" %>
<!-- 외부 슬라이더  -->
  <link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<style type="text/css">
 /*로그인 슬라이더  */
	.banner-bg img{width:100%; height: 100%; padding-top: 5%;}
	.visual {position: relative; top:5%; left: 5%}
	.visual button{
		position: absolute;
		top: 50%; 
		border: none;
		}
	.visual button.slick-next{right: -10px; font-size: 0;}
	.visual button.slick-prev{left: -10px; font-size: 0;}
 /*슬라이더 끝  */
  </style>
<body class="bg-gradient-primary">
  <div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">
      <div class="col-xl-10 col-lg-12 col-md-9">
        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block ">
              		<div class="banner-bg">
                    	<section class="visual">
							<div><a href="https://www.mfds.go.kr/brd/m_99/view.do?seq=44032&srchFr=&srchTo=&srchWord=&srchTp=&itm_seq_1=0&itm_seq_2=0&multi_itm_seq=0&company_cd=&company_nm=&page=1"><img src="${food_info0 }" alt="이미지1"></a></div>
							<div><a href="https://www.mfds.go.kr/brd/m_99/view.do?seq=44014&srchFr=&srchTo=&srchWord=&srchTp=&itm_seq_1=0&itm_seq_2=0&multi_itm_seq=0&company_cd=&company_nm=&page=1"><img src="${food_info1 }" alt="이미지2"></a></div>
							<div><a href="https://nedrug.mfds.go.kr/bbs/23/828"><img src="${food_info2 }" alt="이미지3"></a></div>
							<div><a href="http://blog.naver.com/kfdazzang/221839489769"><img src="${food_info3 }" alt="이미지4"></a></div>
							<div><a href="https://www.mfds.go.kr/brd/m_715/list.do"><img src="${food_info4 }" alt="이미지5"></a></div>
							<div><a href="http://www.foodsafetykorea.go.kr/fcdb/"><img src="${food_info5 }" alt="이미지6"></a></div>
						</section>
                    </div>
              </div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                  </div>
                  <form class="user" action="${contextPath }/member/loginPost" method="post" >
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="email" name="email" value="${param.email }" aria-describedby="emailHelp" placeholder="Email ">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="passwd" name="passwd" value="${param.passwd }" placeholder="Password">
                    </div>
                    <button class="btn btn-primary btn-user btn-block" type="submit">
	                      	로 그 인
                    </button>
                    <a class="btn btn-primary btn-user btn-block" type="button" href="${contextPath }" style="background-color: red; border-color: red;">
	                      	취 소
                    </a>
                    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="big" href="${contextPath }/member/pwFind">비밀번호를 잊으셧나요?</a>
                  </div>
                  <div class="text-center">
                    	아직 회원이 아니신가요? <a class="big" href="${contextPath }/member/signup">회 원 가 입!</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	$('.visual').slick({
			  autoplay: true,
			  autoplaySpeed: 3000
	});
	
	
</script>
</html>



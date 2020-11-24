<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    

      <title>누트리</title>
      
    <!-- CSS Files -->
    <link rel="stylesheet" href="/bteam/resources//assets/css/animate-3.7.0.css">
    <link rel="stylesheet" href="/bteam/resources//assets/css/font-awesome-4.7.0.min.css">
    <link rel="stylesheet" href="/bteam/resources//assets/fonts/flat-icon/flaticon.css">
    <link rel="stylesheet" href="/bteam/resources//assets/css/bootstrap-4.1.3.min.css">
    <link rel="stylesheet" href="/bteam/resources//assets/css/owl-carousel.min.css">
    <link rel="stylesheet" href="/bteam/resources//assets/css/nice-select.css">
    <link rel="stylesheet" href="/bteam/resources//assets/css/style.css">

    <!-- Bootstrap Core CSS -->
    <link href="/bteam/resources//vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/bteam/resources//vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/bteam/resources//vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/bteam/resources//vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/bteam/resources//dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/bteam/resources//vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
     <!-- Favicon -->
    <link rel="shortcut icon" href="/bteam/resources//assets/images/logo/favicon.png" type="image/x-icon">
    
   
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

    <!-- Header Area Starts -->
	<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath }" scope="application"/>
	<header class="header-area main-header">
        <div class="container">
            <div class="row2">
                <div class="col-lg-2">
                    <div class="logo-area">
                        <a href="${contextPath }"><img style="width: 140px; margin-left: 15px;" src="/bteam/resources//assets/images/logo4.png" alt="logo"></a>
                    </div>
                </div>
                <div class="col-lg-10">
                    <div class="custom-navbar">
                        <span></span>
                        <span></span>
                        <span></span>
                    </div>  
                    <div class="main-menu">
                        <ul>
                            <li class="active"><a href="${contextPath }">HOME</a></li>
                            <li><a>기능식품 원료 정보</a>
	                            <ul class="sub-menu" style="border-radius: 10px;">
                                    <li><a href="${contextPath }/hff_board/iList" >식약처 인정</a></li>
                                    <li><a href="${contextPath }/hff_board/mList">개별 기업 인정</a></li>                                    
                                </ul>
                            </li>
                            <li><a href="${contextPath }/video/list">동영상게시판</a></li>
                            <li><a href="${contextPath }/market_board/list">홍보/장터</a>
                            <li><a	>나의건강정보</a>
                                <ul class="sub-menu" style="border-radius: 10px;">
                                    <li><a href="${contextPath }/myhealth/nutrient">일일 영양소 섭취량</a></li>
                                    <li><a href="${contextPath }/myhealth/sDiary">나의 운동량</a></li>                                    
                                </ul>
                            </li>
                            <li><a href="${contextPath }/notice_board/list">공지사항</a></li>
                            <li class="menu-btn">
                            <c:choose>
                            	<c:when test="${not empty login }">                            		
									<li><a style="color: #007bff">${login.nickname }님</a>
										<ul class="sub-menu" style="border-radius: 10px;">
		                                    <li><a href="${contextPath }/member/mypage">내 정보</a></li>
											<li><a href="${contextPath }/mail/mailForm">1:1문의</a></li>
		                                </ul>
                            		<a href="${contextPath }/member/logout" class="logout">Log out</a>
                            		</li>
                            	</c:when>
                            	<c:otherwise>
	                                <a href="${contextPath }/member/login" class="login">Log in</a>
	                                <a href="${contextPath }/member/signup" class="template-btn">Sign Up</a>
                            	</c:otherwise>
                            </c:choose>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Header Area End -->
	
    <div id="wrapper">
<script type="text/javascript">
	 $(function(){
			if(${msg ne null}){
				alert('${msg}');
			};		
			
		}) 
</script>        
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="..//views/includes/header.jsp" %>

<style>
 .overflow-ellipsis {  
	 overflow: hidden;
	 text-overflow: ellipsis;
	 white-space: nowrap;
  	 width: 250px;
  	 color: #343a40;
 }
 p{
 	margin: 5px 0;
 }
 .imgMove {
 		display: grid;
	    grid-gap: 5px;
	    grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
	    grid-template-rows: repeat(2, 100px);
	    overflow: hidden;
	 	text-overflow: ellipsis;
	 	height: 265px;
 }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

    <!-- Banner Area Starts -->
    <section class="banner-area">
        <div class="container-fluid">
            <div class="row2">
                <div class="col-lg-6 px-0">
                    <div class="banner-bg"></div>
                </div>
                <div class="col-lg-6 px-0" style="padding: 30px 50px 10px 30px;">
                    <div class="banner-text"    style="padding: 25% 30% 0 5%;">
                        <h1>NUTRI는 여러분의 <span>건강</span>을 위한 정보를 제공해 드립니다.</h1>
                        <p >NUTRI provides information for your <span>health</span>.</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Area End -->

    <!-- Search Area Starts -->
    <div class="search-area">
        <div class="search-bg">
            <div class="container">
                <div class="row2">
                    <div class="col-lg-12">
                        <form id="searchForm" action="#" class="d-md-flex justify-content-between">
                            <select>
                                <option value="" <c:out value="${pageMaker.cri.type == null ? 'selected':''}"/>>인증기관선택</option>
                                <option value="1">식약처</option>
                                <option value="2">개별 기업 인정</option>                                                                
                            </select>
                            <input type="text" name="keyword" placeholder="Search Keyword" value="${pageMaker.cri.keyword }" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Search Keyword'" required>
                            <button type="submit" class="template-btn">find</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Search Area End -->

    <!-- Feature Area Starts -->
    <section class="feature-area section-padding2">
        <div class="container">
            <div class="row2">
                <div class="col-lg-4">
                    <div class="single-feature mb-4 mb-lg-0">
                    	<div style="border-bottom: 1px solid;">
	                        <h4 style="font: bold;">공 지 사 항</h4>
                    	</div>
                       	<c:forEach items="${noticeList}" var="noticeDto" begin="0" end="5">
                       	  <div>	
                       		<a class="nMove" href="${noticeDto.nbno}" title="${noticeDto.title }"><p class="overflow-ellipsis">	
                       		 <img style="width: 10px; height: 10px;" src="/bteam/resources//images/img1.gif"> ${noticeDto.title }</p></a>
                       	  </div>                       		
                       	</c:forEach>                   		
                       <a href="${contextPath }/notice_board/list" class="secondary-btn">바로가기<span class="flaticon-next"></span></a>
                    </div>
                </div>
               <div class="col-lg-4">
                    <div class="single-feature mb-4 mb-lg-0">
                    	<div style="border-bottom: 1px solid;">
	                        <h4>동영상 게시판</h4>
                    	</div>
                    	<div class="imgMove">
	                        <c:forEach items="${streamList}" var="streamDto" begin="0" end="3">
	                           <a class="stMove" href="${streamDto.mvno }" style="text-align: center;">
	                              <img class="img1" style="width: 90%; height: 90%; padding: 5%" src="https://img.youtube.com/vi/${streamDto.content }/0.jpg"
	                               	 onmouseover ="this.src='https://img.youtube.com/vi/${streamDto.content }/1.jpg'"
	                               	 onmouseout="this.src='https://img.youtube.com/vi/${streamDto.content }/0.jpg'"/>
                           			${streamDto.title } <b>[${streamDto.replycnt }]</b>
                           		</a>                        
                            </c:forEach>
                    	</div>
                        <a href="${contextPath }/video/list" class="secondary-btn">바로가기<span class="flaticon-next"></span></a>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="single-feature">
                    	<div style="border-bottom: 1px solid;">
	                        <h4>홍보 장터</h4>
                    	</div>
                       	<c:forEach items="${marketList}" var="marketDto" begin="0" end="5">
                       	  <div>	
                       		<a class="mMove" href="${marketDto.m_no}" title="${marketDto.m_title }" ><p class="overflow-ellipsis">
                       		  <img style="width: 10px; height: 10px;" src="/bteam/resources//images/img1.gif"> ${marketDto.m_title }</p></a>
                       	  </div>                       		
                       	</c:forEach>
                        <a href="${contextPath }/market_board/list" class="secondary-btn">바로가기<span class="flaticon-next"></span></a>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
    </section>
    
      <form id="nActionForm" action="${contextPath }notice_board/list" method="get">
	       <input type="hidden" name="pageNum" value="${nPageMaker.cri.pageNum }">
	       <input type="hidden" name="amount" value="${nPageMaker.cri.amount }">
	       <input type="hidden" name="type" value="${nPageMaker.cri.type }">
	       <input type="hidden" name="keyword" value="${nPageMaker.cri.keyword }">
      </form>
      <form id="stActionForm" action="${contextPath }video/list" method="get">
	       <input type="hidden" name="pageNum" value="${stPageMaker.cri.pageNum }">
	       <input type="hidden" name="amount" value="${stPageMaker.cri.amount }">
	       <input type="hidden" name="type" value="${stPageMaker.cri.type }">
	       <input type="hidden" name="keyword" value="${stPageMaker.cri.keyword }">
      </form>
      <form id="mActionForm" action="${contextPath }market_board/list" method="get">
	       <input type="hidden" name="pageNum" value="${mPageMaker.cri.pageNum }">
	       <input type="hidden" name="amount" value="${mPageMaker.cri.amount }">
	       <input type="hidden" name="type" value="${mPageMaker.cri.type }">
	       <input type="hidden" name="keyword" value="${mPageMaker.cri.keyword }">
      </form>
      
       
    
    <!-- Feature Area End -->
<script type="text/javascript">
	$('.visual').slick({
			  autoplay: true,
			  autoplaySpeed: 2000
	});
</script>
<script>
	
	$(document).ready(function() {		
		
		history.replaceState({}, null, null);			
		
		var nActionForm = $("#nActionForm");
		
		
		 $(".nMove").on("click", function(e) {
		    	e.preventDefault();
		    	
		    	nActionForm.append("<input type='hidden' name='nbno' value='"
		    			+$(this).attr("href")
		    			+"'>");
		    	nActionForm.attr("action","${contextPath }/notice_board/read");
		    	nActionForm.submit();
		    });
		 
		 var stActionForm = $("#stActionForm");
			
		 $(".stMove").on("click", function(e) {
		    	e.preventDefault();
		    	
		    	stActionForm.append("<input type='hidden' name='mvno' value='"
		    			+$(this).attr("href")
		    			+"'>");
		    	stActionForm.attr("action","${contextPath }/video/read");
		    	stActionForm.submit();
		    });
		 
		 
		 var mActionForm = $("#mActionForm");
			
		 $(".mMove").on("click", function(e) {
		    	e.preventDefault();
		    	
		    	mActionForm.append("<input type='hidden' name='m_no' value='"
		    			+$(this).attr("href")
		    			+"'>");
		    	mActionForm.attr("action","${contextPath }/market_board/read");
		    	mActionForm.submit();
		    });
		 
		
		      
		
		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e) {
			if (!searchForm.find("option:selected").val()) {
				alert("인증기관을 선택하세요");
				return false;
			}
			
			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드를 입력하세요");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			
			e.preventDefault();
			
			if (searchForm.find("option:selected").val() == "1") {
				searchForm.append($('<input/>', {type: 'hidden', name: 'type', value:'NSF' }));
				searchForm.attr("action","${contextPath}/hff_board/iList");
				searchForm.submit();
			} else if (searchForm.find("option:selected").val() == "2") {
				searchForm.append($('<input/>', {type: 'hidden', name: 'type', value:'SFI' }));
				searchForm.attr("action","${contextPath}/hff_board/mList");
				searchForm.submit();
			}
			
		});
	});
</script>    
<%@ include file="..//views/includes/footer.jsp" %>

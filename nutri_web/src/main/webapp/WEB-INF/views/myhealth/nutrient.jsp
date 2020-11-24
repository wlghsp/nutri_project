<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp" %>

<!-- 카카오 맵 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=87f60356c787d8dad8a88bcc4ae30f49&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=87f60356c787d8dad8a88bcc4ae30f49"></script>
<link rel="stylesheet" href="/bteam/resources//css/map.css">
<script src="/bteam/resources//js/map.js"></script>

            <div class="row">
            <c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath}" scope="application"/>
                <div class="col-lg-12">
                    <h1 class="page-header">일일 영양소 섭취량 </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           	성별 :${nutrient.get(0).gender } 나이:${nutrient.get(0).age }
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th style="text-align: center;">영양소</th>
                                        <th style="text-align: center;">평균섭취량</th>
                                        <th style="text-align: center;">권장섭취량</th>
                                        <th style="text-align: center;">충분섭취량</th>
                                        <th>상한섭취량</th>
                                        <!-- <th>조회수</th> -->
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${nutrient }" var="list">	
                                		<tr style="text-align: center;">
                                			<td>${list.nutrient }</td>
                                			<td>${list.average_intake }</td>
                                			<td>${list.recommended_intake }</td>
                                			<td>${list.sufficient_intake }</td>
                                			<td>${list.maximum_intake }</td>
                                			<%-- <td><span class="badge bg-ref">${market_board.m_viewcnt }</span></td> --%>
                                		</tr>
                                </c:forEach>
                                </tbody>
                            </table>
                         
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="panel panel-default" >
            	<!-- 지도 -->
            	<div class="panel-body">
	            <h3>주변 약국</h3>
		            <div class="map_wrap table table-striped table-bordered ">
					   <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
					    <ul id="category">
					        <li id="PM9" data-order="2"> 
						           <span class="category_bg pharmacy"></span>
						            약국
					   	    </li>  
					    </ul>
					</div>
				<!--/지도  -->
            	</div>
            </div>
           	<br>
           	<br>
<script>
	$(document).ready(function() {
		var result = "${result}";
		
		checkModal(result);
		
		history.replaceState({}, null, null);
		
		function checkModal(result) {
			if (result == "" || history.state) {
				return;
			}
			
			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function() {
			self.location = "${contextPath }/market_board/register";
		});
		
		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click", function(e) {
			e.preventDefault();
			
			console.log("click");
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		$(".move").on("click", function(e) {
			e.preventDefault();
			
			actionForm.append("<input type='hidden' name='m_no' value='"
					+ $(this).attr("href")
					+ "'>");
			actionForm.attr("action", "${contextPath }/market_board/read");
			actionForm.submit();
		});
		
		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e) {
			if (!searchForm.find("option:selected").val()) {
				alert("검색종류를 선택하세요");
				
				return false;
			}
			
			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드를 입력하세요");
				
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			
			e.preventDefault();
			
			searchForm.submit();
		});
	});
</script>
<%@include file="../includes/footer.jsp"%>

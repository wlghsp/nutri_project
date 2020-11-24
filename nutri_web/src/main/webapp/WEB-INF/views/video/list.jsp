<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp" %>
<style>
	.imgMove {
	    display: grid;
	    grid-gap: 5px;
	    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
	    grid-template-rows: repeat(2, 300px);
	}
</style>
            <div class="row">
            <c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath}" scope="application"/>
                <div class="col-lg-12">
                    <h1 class="page-header">동영상</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading" style="height: 40px;">
                            
                           <sec:authorize access="isAuthenticated()">
                            	<button id="regBtn" type="button" class="btn btn-xs pull-right">Register new Board</button>
                            </sec:authorize>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <div class="imgMove">
                                	<c:forEach items="${list }" var="streamDto">
                           		
                           					<a class="move" href="${streamDto.mvno }" style="text-align: center;">
	                               				<img class="img1" style="width: 90%; height: 90%; padding-left: 5%" src="https://img.youtube.com/vi/${streamDto.content }/0.jpg"
	                               				 onmouseover ="this.src='https://img.youtube.com/vi/${streamDto.content }/1.jpg'"
	                               				 onmouseout="this.src='https://img.youtube.com/vi/${streamDto.content }/0.jpg'"/>
	                               				 <br>
                           						${streamDto.title } <b>[${streamDto.replycnt }]</b>
                           					</a>
                               	  			<%-- <td><span class="badge bg-ref">${market_board.m_viewcnt }</span></td> --%>
                                	</c:forEach>
                                 </div>
                            </table>
                            
                            
                            <div class="pull-right">
                            	<ul class="pagination">
                            		<c:if test="${pageMaker.prev }">
                            			<li class="paginate_button previous">
                            				<a href="${pageMaker.startPage - 1 }">Previous</a>
                            			</li>
                            		</c:if>
                            		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="num">
                            				<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":"" }">
                            					<a href="${num }">${num }</a>
                            				</li>
                            			</c:forEach>
                            		<c:if test="${pageMaker.next }">
                            			<li class="paginate_button next">
                            				<a href="${pageMaker.endPage + 1 }">Next</a>
                            			</li>
                            		</c:if>
                            	</ul>
                            </div>
                            
                            <form id="actionForm" action="${contextPath }/video/list" method="get">
                            	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                            	<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                            	<input type="hidden" name="type" value="${pageMaker.cri.type }">
                            	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
                            </form>
                            <!-- Modal 추가 -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            	<div class="modal-dialog">
                            		<div class="modal-content">
                            			<div class="modal-header">
                            				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            			</div>
                            			<div class="modal-body">
                            				처리가 완료 되었습니다.
                            			</div>
                            			<div class="modal-footer">
                            				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            				<button type="button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
                            			</div>
                            		</div>
                            	</div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
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
			self.location = "${contextPath }/video/register";
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
			
			actionForm.append("<input type='hidden' name='mvno' value='"
					+ $(this).attr("href")
					+ "'>");
			actionForm.attr("action", "${contextPath }/video/read");
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



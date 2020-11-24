<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/adminHeader.jsp" %>

            <div class="row">
            <c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '':pageContext.request.contextPath}" scope="application"/>
                <div class="col-lg-12">
                    <h1 class="page-header">Admin Only</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                         	   회원 리스트
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>닉네임</th>
                                        <th>이메일</th>
                                        <th>등록일</th>
                                        <th>출생연도</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list }" var="member">
                                		<tr>
	                               			<td>${member.meno }</td>
	                               			<td>${member.nickname }</td>
	                               			<td>${member.email }</td>
	                               			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${member.indate }"/></td>
	                               			<td><span class="badge bg-ref"> ${member.dateofbirth }</span></td>
	                               			<td>
	                               				<a class="eMove" href="${member.email }"><button data-oper="email" class="btn btn-info">이메일</button></a>
	                               				<a class="move" href="${member.meno }"><button data-oper="ban" class="btn btn-danger">강제 탈퇴</button></a>
	                               			</td>
                                		</tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                             <div class="row">
                            	<div class="col-lg-12">
                            		<form id="searchForm" action="${contextPath }/admin/index" method="post">
                            			<select name="type">
                            				<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected':''}"/>>-----</option>
                            				<option value="T" <c:out value="${pageMaker.cri.type eq 'T' ? 'selected':''}"/>>이메일</option>
                            				<option value="C" <c:out value="${pageMaker.cri.type eq 'C' ? 'selected':''}"/>>닉네임</option>
                            				<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC' ? 'selected':''}"/>>이메일 or 닉네임</option>
                            			</select>
                            			<input class="searchlangs" type="text" name="keyword" placeholder="검색어" value="${pageMaker.cri.keyword }">
                            			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                            			<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                            			<button class="btn btn-default">Search</button>
                            			<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
                            		</form>
                            	</div>
                            </div>
                            
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
                            
                            <form id="actionForm" action="${contextPath }/admin/index" method="post">
                            	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
                            	<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
                            	<input type="hidden" name="type" value="${pageMaker.cri.type }">
                            	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
                            	<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
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
$(function(){
	if(${msg ne null}){
		alert('${msg}');
	};		
	
}) 


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
			
			actionForm.append("<input type='hidden' name='meno' value='"
					+ $(this).attr("href")
					+ "'>");
			actionForm.attr("action", "${contextPath }/admin/remove");
			actionForm.submit();
		});
		
		$(".eMove").on("click", function(e) {
			e.preventDefault();
			
			actionForm.append("<input type='hidden' name='email' value='"
					+ $(this).attr("href")
					+ "'>");
			actionForm.attr("action", "${contextPath }/admin/mailForm");
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
<%@include file="../includes/adminFooter.jsp"%>
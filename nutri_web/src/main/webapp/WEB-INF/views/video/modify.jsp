<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
  <div class="col-lg-12">
    <h1 class="page-header">동영상 게시판 수정</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">동영상 게시판 수정 페이지 </div>
      <!-- /.panel-heading -->
      <div class="panel-body">

	  <form action="${contextPath}/video/modify" method="post" role="form">
	  	 <input type="hidden" name="pageNum" value="${cri.pageNum}">
	  	 <input type="hidden" name="amount" value="${cri.amount}">
		 <input type="hidden" name="type" value="${cri.type}">
		 <input type="hidden" name="keyword" value="${cri.keyword}">
         <div class="form-group">
           <label>번호</label> <input class="form-control" name="mvno" value="${video.mvno }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>제목</label> <input class="form-control" name="title" value="${video.title }">
         </div>

         <div class="form-group">
           <label>내용</label>
           <textarea class="form-control" rows="3" name="content">${video.content }</textarea>
         </div>

         <div class="form-group">
           <label>작성자</label> <input class="form-control" name="writer" value="${video.writer }" readonly="readonly">
         </div>
         <!-- html의 data- 속성 이용 -->
         <button type="submit" data-oper="modify" class="btn btn-default">수정</button>
          <button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
          <button type="submit" data-oper="list" class="btn btn-info">목록</button>
         <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }" >
	  </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
$(document).ready(function() {
	var formObj = $("form");
	
	$("button").on("click", function(e) {
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if (operation === "remove") {
			formObj.attr("action", "${contextPath}/video/remove");
		} else if (operation === "list") {
			formObj.attr("action", "${contextPath}/video/list")
			       .attr("method", "get");
			
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			var typeTag = $("input[name='type']").clone();
			
			formObj.empty();
			
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
			//self.location = "${contextPath}/board/list";
			
			//return;
		}
		formObj.submit();
	});
});
</script>
<%@include file="../includes/footer.jsp"%>